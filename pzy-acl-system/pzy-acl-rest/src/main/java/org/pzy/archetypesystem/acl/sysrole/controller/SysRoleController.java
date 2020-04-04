package org.pzy.archetypesystem.acl.sysrole.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.pzy.archetypesystem.acl.sysrole.entity.SysRole;
import org.pzy.archetypesystem.acl.sysrole.service.SysRoleService;
import org.pzy.opensource.domain.PageT;
import org.pzy.opensource.domain.ResultT;
import org.pzy.opensource.domain.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * SysRole 的rest服务
 *
 * @author pan
 * @since 2020-04-04
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "SysRole的rest服务")
public class SysRoleController {

    @Autowired
    private SysRoleService service;

    @GetMapping
    @ApiOperation(value = "SysRole分页查找")
    public ResultT<PageT<SysRole>> searchPage(PageVO page) {
        PageT<SysRole> pageResult = service.searchPageAndCache(page, null);
        return ResultT.success(pageResult);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "SysRole详细查找")
    @ApiImplicitParam(name = "id", value = "唯一标识", dataType = "Long", paramType = "path", required = true)
    public ResultT<SysRole> searchById(@PathVariable("id") Long id) {
        SysRole result = service.getById(id);
        return ResultT.success(result);
    }

    @PostMapping
    @ApiOperation(value = "SysRole新增")
    public ResultT<Boolean> add(@RequestBody SysRole dto) {
        service.addAndClearCache(dto);
        return ResultT.success(true);
    }

    @PutMapping
    @ApiOperation(value = "SysRole编辑")
    public ResultT<Boolean> edit(@RequestBody SysRole dto) {
        service.editAndClearCache(dto);
        return ResultT.success();
    }

}