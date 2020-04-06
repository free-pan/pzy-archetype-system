package org.pzy.archetypesystem.base.module.pub.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.pzy.archetypesystem.base.module.acl.dto.ResetPasswordDTO;
import org.pzy.archetypesystem.base.module.acl.service.SysUserService;
import org.pzy.archetypesystem.base.support.shiro.LoginParamsDTO;
import org.pzy.archetypesystem.base.support.shiro.ShiroMapStruct;
import org.pzy.opensource.domain.ResultT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        } else {
            // 登录失败
            log.debug("登录失败!");
        }
        return ResultT.success();
    }

    @PostMapping("logout")
    @ApiOperation(value = "登出")
    public ResultT logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultT.success();
    }

    @RequestMapping("forbidden")
    @ApiOperation(value = "shiro检测到权限不足时转入该接口")
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ResultT forbidden() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultT.success().setMsgList(Arrays.asList("权限不足禁止访问!"));
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
