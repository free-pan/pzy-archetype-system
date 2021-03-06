package org.pzy.archetypesystem.base.module.pub.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.pzy.archetypesystem.base.module.acl.dto.ResetPasswordDTO;
import org.pzy.archetypesystem.base.module.acl.service.SysUserService;
import org.pzy.archetypesystem.base.module.acl.vo.SysUserVO;
import org.pzy.archetypesystem.base.module.comm.annotation.WinterLog;
import org.pzy.archetypesystem.base.module.comm.dto.CommOnlineUserAddDTO;
import org.pzy.archetypesystem.base.module.comm.enums.FunCodeEnum;
import org.pzy.archetypesystem.base.module.comm.enums.WinterLogType;
import org.pzy.archetypesystem.base.module.comm.service.CommOnlineUserService;
import org.pzy.archetypesystem.base.support.constant.SystemConstant;
import org.pzy.archetypesystem.base.support.shiro.LoginParamsDTO;
import org.pzy.archetypesystem.base.support.shiro.ShiroMapStruct;
import org.pzy.opensource.comm.exception.ValidateException;
import org.pzy.opensource.domain.GlobalConstant;
import org.pzy.opensource.domain.ResultT;
import org.pzy.opensource.domain.enums.GlobalSystemErrorCodeEnum;
import org.pzy.opensource.security.domain.bo.SimpleShiroUserBO;
import org.pzy.opensource.security.properties.WinterShiroProperties;
import org.pzy.opensource.verifycode.domain.constant.VerificationCodeConstant;
import org.pzy.opensource.verifycode.domain.enums.CheckCodeVerifyStatusEnums;
import org.pzy.opensource.verifycode.domain.enums.VerifyCodeValidateFailTypeEnum;
import org.pzy.opensource.verifycode.support.util.VerificationCodeUtil;
import org.pzy.opensource.web.util.CookieUtil;
import org.pzy.opensource.web.util.HttpClientInfoUtil;
import org.pzy.opensource.web.util.HttpRequestUtil;
import org.pzy.opensource.web.util.HttpResponseUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * LoginLogoutController
 *
 * @author pan
 * @date 2020/4/6 15:07
 */
@RestController
@RequestMapping("/pu")
@Api(tags = "公共服务")
@Slf4j
public class PublicController {

    @Autowired
    private ShiroMapStruct shiroMapStruct;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CommOnlineUserService onlineUserService;
    @Autowired
    private WinterShiroProperties winterShiroProperties;

    @WinterLog(type = WinterLogType.Login, code = FunCodeEnum.LOGIN)
    @PostMapping("login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = VerificationCodeConstant.CLIENT_ID, value = "客户端id. 当需要进行验证校验时,则需要携带该参数", required = false, paramType = "header", dataType = "string"),
            @ApiImplicitParam(name = VerificationCodeConstant.VERIFICATION_CODE_ID, value = "验证码. 密码错误次数大于3次,则需要输入验证码.", required = false, paramType = "header", dataType = "string")
    })
    @ApiOperation(value = "登录")
    public ResultT<Integer> login(@RequestBody @Validated LoginParamsDTO dto) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            int loginErrorCount = getLoginErrorCountFromCookie();
            // 尚未登录, 执行登录逻辑
            if (log.isDebugEnabled()) {
                log.debug("当前已登录错误次数[{}]次", loginErrorCount);
            }
            if (loginErrorCount > 3) {
                CheckCodeVerifyStatusEnums checkCodeVerifyStatusEnums = VerificationCodeUtil.loadCheckCodeVerifyStatus(HttpRequestUtil.loadHttpServletRequest());
                if (checkCodeVerifyStatusEnums == CheckCodeVerifyStatusEnums.NOT_EXECUTE_VERIFY) {
                    log.warn("登录错误次数超过3次, 应该需要执行验证码校验, 但获取到的状态却是未执行验证码校验, 请确认客户端的请求头中是否拥有客户端id!");
                    throw new ValidateException("验证码校验失败!");
                }
                if (checkCodeVerifyStatusEnums == CheckCodeVerifyStatusEnums.VERIFY_FAIL) {
                    log.warn("登录错误次数超过3次, 验证码过滤器已经检测到客户端传入的验证码是错误的, 实际应该转入验证码错误的uri, 但现在依然进入了登录接口, 请检查验证码过滤器配置或者逻辑是否有误!");
                    throw new ValidateException("验证码错误!");
                }
            }
            if (log.isDebugEnabled()) {
                log.debug("尚未登录,执行shiro登录...");
            }
            try {
                // shiro登录
                subject.login(shiroMapStruct.loginParamsDTO2ShiroLoginToken(dto));
                // 登录成功, 删除登录错误计数器
                CookieUtil.remove(HttpResponseUtl.loadHttpServletResponse(), SystemConstant.LOGIN_ERROR_COUNT_COOKIE_NAME);
            } catch (AuthenticationException e) {
                // 登录错误次数累加
                CookieUtil.addSessionCookie(HttpResponseUtl.loadHttpServletResponse(), SystemConstant.LOGIN_ERROR_COUNT_COOKIE_NAME, String.valueOf(++loginErrorCount), true);
                throw e;
            }
            log.debug("shiro登录成功,登录凭证:[{}], 准备保存在线用户信息!", subject.getPrincipal());
            try {
                saveOnlineUserInfo(subject);
            } catch (Exception e) {
                subject.logout();
                log.error("保存在线用户时发生异常!", e);
                throw e;
            }
        }
        if (subject.isAuthenticated()) {
            // 登录成功
            return ResultT.success();
        } else {
            // 登录失败
            if (log.isDebugEnabled()) {
                log.debug("登录失败!");
            }
            return ResultT.success().setSuccess(false);
        }
    }

    /**
     * 从cookie中获取登录错误次数
     *
     * @return
     */
    private int getLoginErrorCountFromCookie() {
        String tmp = CookieUtil.getValue(HttpRequestUtil.loadHttpServletRequest(), SystemConstant.LOGIN_ERROR_COUNT_COOKIE_NAME);
        int loginErrorCount = StringUtils.isEmpty(tmp) ? 0 : Integer.parseInt(tmp);
        return loginErrorCount;
    }

    /**
     * 保存在线用户信息
     *
     * @param subject
     */
    private void saveOnlineUserInfo(Subject subject) {
        // 获取登录主体信息
        SimpleShiroUserBO shiroUserBO = (SimpleShiroUserBO) subject.getPrincipal();
        // 获取用户id
        Long id = Long.parseLong(shiroUserBO.getUkFlag());
        // 根据id获取用户信息
        SysUserVO sysUser = sysUserService.getByIdAndCache(id);

        CommOnlineUserAddDTO onlineUserAddDTO = new CommOnlineUserAddDTO();
        onlineUserAddDTO.setClientIp(HttpClientInfoUtil.getClientIp());
        onlineUserAddDTO.setClientBrowser(HttpClientInfoUtil.getClientBrowser());
        onlineUserAddDTO.setClientOs(HttpClientInfoUtil.getClientOS());
        // 填充单个用户允许的最大session个数
        onlineUserAddDTO.setSingleUserMaxSession(this.winterShiroProperties.getSingleUserMaxSession());
        // 单个用户session个数超过允许的上限时, 是否优先踢出后登录的那个session
        onlineUserAddDTO.setKickoutAfter(this.winterShiroProperties.getKickoutAfter());
        // 用户email
        onlineUserAddDTO.setEmail(sysUser.getEmail());
        // 登录时间
        onlineUserAddDTO.setLoginTime(LocalDateTime.now());
        // 用户id
        onlineUserAddDTO.setUserId(id);
        // 用户姓名
        onlineUserAddDTO.setName(sysUser.getName());
        // 当前的会话id
        onlineUserAddDTO.setSessionId(SecurityUtils.getSubject().getSession(true).getId().toString());
        // 保存在线用户信息
        onlineUserService.saveAndClearCache(onlineUserAddDTO);
        // 在会话中保存唯一标识
        SecurityUtils.getSubject().getSession().setAttribute(GlobalConstant.SESSION_LOGIN_USER_ID_KEY, id);
        SecurityUtils.getSubject().getSession().setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, shiroUserBO.getUkFlag());
    }

    @WinterLog(code = FunCodeEnum.LOGOUT)
    @PostMapping("logout")
    @ApiOperation(value = "登出")
    public ResultT logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Session session = subject.getSession();
            if (null != session) {
                this.onlineUserService.deleteBySessionIdAndClearCache(session.getId().toString());
            }
            subject.logout();
        }
        return ResultT.success();
    }

    @RequestMapping("unauthorized")
    @ApiOperation(value = "未登录直接访问受保护的资源,则转入此接口")
    public ResultT forbidden() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultT.success().setMsgList(Arrays.asList("您尚未登录或登录已过期!")).setCode(GlobalSystemErrorCodeEnum.SECURITY_UNAUTHORIZED_EXCEPTION.name());
    }

    @RequestMapping("force-kickout")
    @ApiOperation(value = "单用户会话数量超过系统上限,转入此接口")
    public ResultT forceKickout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultT.success().setMsgList(Arrays.asList("您的账号已达登录上限,您被自动退出!")).setCode(GlobalSystemErrorCodeEnum.SECURITY_LOGIN_EXCEPTION.name());
    }

    @RequestMapping("verify-code-validate-error")
    @ApiOperation(value = "验证码验证失败,转入此接口")
    public ResultT verifyCodeError() {
        VerifyCodeValidateFailTypeEnum failTypeEnum = VerificationCodeUtil.loadVerifyCodeValidateFailType(HttpRequestUtil.loadHttpServletRequest());
        return ResultT.success().setMsgList(Arrays.asList("验证码验证失败!")).setCode(failTypeEnum.name());
    }

    @WinterLog(code = FunCodeEnum.SEND_RESET_PWD_VERIFY_CODE)
    @PostMapping(value = "send-reset-pwd-verify-code", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "发送重置密码需要的验证码")
    public ResultT sendResetPwdVerifyCode(String email) {
        sysUserService.sendResetPasswordValidCode(email);
        return ResultT.success();
    }

    @PutMapping(value = "reset-pwd")
    @WinterLog(code = FunCodeEnum.RESET_PWD, saveInputParam = false)
    @ApiOperation(value = "重置密码")
    public ResultT resetPassword(@RequestBody ResetPasswordDTO dto) {
        sysUserService.resetPasswordAndClearCache(dto);
        return ResultT.success();
    }
}
