package org.pzy.archetypesystem.acl.sysrole.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.pzy.archetypesystem.acl.sysrole.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SysRole 的rest服务
 *
 * @author pan
 * @since 2020-04-03
 */
@RestController
@RequestMapping("/sysrole/sys-role")
@Api(tags = "SysRole的rest服务")
public class SysRoleController {

    @Autowired
    private SysRoleService service;

    @GetMapping
    @ApiOperation(value = "SysRole分页查找")
    public ResultT<PageT<SysUser>> searchPage(PageVO page) {
        PageT<SysUser> pageResult = service.searchPageAndCache(page, null);
        return ResultT.success(pageResult);
    }

    @GetMapping("${id}")
    @ApiOperation(value = "SysRole详细查找")
    @ApiImplicitParam(name = "id", value = "唯一标识", dataType = "Long", paramType = "path", required = true)
    public ResultT<SysUser> searchById(@PathVariable("id") Long id) {
        SysUser result = service.getById(id);
        return ResultT.success(result);
    }

    @PostMapping
    @ApiOperation(value = "SysRole新增")
    public ResultT<Boolean> add(@RequestBody SysUser dto) {
        service.addAndClearCache(dto);
        return ResultT.success(true);
    }

    @PutMapping
    @ApiOperation(value = "SysRole编辑")
    public ResultT<Boolean> edit(@RequestBody SysUser dto) {
        service.editAndClearCache(dto);
        return ResultT.success();
    }

}