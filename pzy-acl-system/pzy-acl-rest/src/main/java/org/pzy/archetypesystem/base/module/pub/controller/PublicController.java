package org.pzy.archetypesystem.base.module.pub.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.pzy.archetypesystem.base.module.acl.dto.ResetPasswordDTO;
import org.pzy.archetypesystem.base.module.acl.service.SysUserService;
import org.pzy.archetypesystem.base.module.acl.vo.SysUserVO;
import org.pzy.archetypesystem.base.module.comm.dto.CommOnlineUserAddDTO;
import org.pzy.archetypesystem.base.module.comm.service.CommOnlineUserService;
import org.pzy.archetypesystem.base.support.shiro.LoginParamsDTO;
import org.pzy.archetypesystem.base.support.shiro.ShiroMapStruct;
import org.pzy.opensource.domain.GlobalConstant;
import org.pzy.opensource.domain.ResultT;
import org.pzy.opensource.domain.enums.GlobalSystemErrorCodeEnum;
import org.pzy.opensource.security.domain.bo.SimpleShiroUserBO;
import org.pzy.opensource.security.properties.WinterShiroProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.session.FindByIndexNameSessionRepository;
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

    @PostMapping("login")
    @ApiOperation(value = "登录")
    public ResultT<Integer> login(@RequestBody @Validated LoginParamsDTO dto) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            // 尚未登录, 执行登录逻辑
            if (log.isDebugEnabled()) {
                log.debug("尚未登录,执行shiro登录...");
            }
            subject.login(shiroMapStruct.loginParamsDTO2ShiroLoginToken(dto));
        }
        if (subject.isAuthenticated()) {
            // 登录成功
            log.debug("登录成功,登录凭证:[{}]", subject.getPrincipal());
            try {
                saveOnlineUserInfo(subject);
            } catch (Exception e) {
                subject.logout();
                log.error("保存在线用户时发生异常!", e);
                throw e;
            }
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
     * 保存在线用户信息
     *
     * @param subject
     */
    private void saveOnlineUserInfo(Subject subject) {
        SimpleShiroUserBO shiroUserBO = (SimpleShiroUserBO) subject.getPrincipal();
        Long id = Long.parseLong(shiroUserBO.getUkFlag());
        SysUserVO sysUser = sysUserService.getByIdAndCache(id);

        CommOnlineUserAddDTO onlineUserAddDTO = new CommOnlineUserAddDTO();
        onlineUserAddDTO.setSingleUserMaxSession(this.winterShiroProperties.getSingleUserMaxSession());
        onlineUserAddDTO.setKickoutAfter(this.winterShiroProperties.getKickoutAfter());
        onlineUserAddDTO.setEmail(sysUser.getEmail());
        onlineUserAddDTO.setLoginTime(LocalDateTime.now());
        onlineUserAddDTO.setUserId(id);
        onlineUserAddDTO.setName(sysUser.getName());
        onlineUserAddDTO.setSessionId(SecurityUtils.getSubject().getSession(true).getId().toString());
        onlineUserService.saveAndClearCache(onlineUserAddDTO);
        SecurityUtils.getSubject().getSession().setAttribute(GlobalConstant.SESSION_LOGIN_USER_ID_KEY, id);
        SecurityUtils.getSubject().getSession().setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, shiroUserBO.getUkFlag());
    }

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
        return ResultT.success().setMsgList(Arrays.asList("您尚未登录或登录已过期!")).setCode(GlobalSystemErrorCodeEnum.SECURITY_UNAUTHORIZED_EXCEPTION.getErrorCode());
    }

    @PostMapping(value = "send-reset-pwd-verify-code", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "发送重置密码需要的验证码")
    public ResultT sendResetPwdVerifyCode(String email) {
        sysUserService.sendResetPasswordValidCode(email);
        return ResultT.success();
    }

    @PutMapping(value = "reset-pwd")
    @ApiOperation(value = "重置密码")
    public ResultT resetPassword(@RequestBody ResetPasswordDTO dto) {
        sysUserService.resetPasswordAndClearCache(dto);
        return ResultT.success();
    }
}
