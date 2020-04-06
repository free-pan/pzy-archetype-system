package org.pzy.archetypesystem.base.module.pub.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.pzy.archetypesystem.base.support.shiro.LoginParamsDTO;
import org.pzy.archetypesystem.base.support.shiro.ShiroMapStruct;
import org.pzy.opensource.domain.ResultT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("login")
    @ApiOperation(value = "登录")
    public ResultT<Integer> login(@RequestBody @Validated LoginParamsDTO dto) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            // 尚未登录, 执行登录逻辑
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
    public ResultT<Integer> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultT.success();
    }
}
