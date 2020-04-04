package org.pzy.archetypesystem.acl.sysuser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.pzy.archetypesystem.acl.sysuser.entity.SysUser;
import org.pzy.archetypesystem.acl.sysuser.service.SysUserService;
import org.pzy.archetypesystem.acl.sysuser.vo.SysUserVO;
import org.pzy.opensource.domain.PageT;
import org.pzy.opensource.domain.ResultT;
import org.pzy.opensource.domain.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * SysUser 的rest服务
 *
 * @author pan
 * @since 2020-04-04
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = "SysUser的rest服务")
public class SysUserController {

    @Autowired
    private SysUserService service;

    @GetMapping
    @ApiOperation(value = "SysUser分页查找")
    public ResultT<PageT<SysUserVO>> searchPage(PageVO page) {
        PageT<SysUserVO> pageResult = service.searchPageAndCache(page, null);
        return ResultT.success(pageResult);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "SysUser详细查找")
    @ApiImplicitParam(name = "id", value = "唯一标识", dataType = "Long", paramType = "path", required = true)
    public ResultT<SysUser> searchById(@PathVariable("id") Long id) {
        SysUser result = service.getById(id);
        return ResultT.success(result);
    }

    @PostMapping
    @ApiOperation(value = "SysUser新增")
    public ResultT<Boolean> add(@RequestBody SysUser dto) {
        service.addAndClearCache(dto);
        return ResultT.success(true);
    }

    @PutMapping
    @ApiOperation(value = "SysUser编辑")
    public ResultT<Boolean> edit(@RequestBody SysUser dto) {
        service.editAndClearCache(dto);
        return ResultT.success();
    }

}