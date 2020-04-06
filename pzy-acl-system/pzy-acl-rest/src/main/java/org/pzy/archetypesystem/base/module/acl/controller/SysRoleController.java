package org.pzy.archetypesystem.base.module.acl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.pzy.archetypesystem.base.module.acl.dto.SysRoleAddDTO;
import org.pzy.archetypesystem.base.module.acl.dto.SysRoleEditDTO;
import org.pzy.archetypesystem.base.module.acl.dto.SysRoleSearchDTO;
import org.pzy.archetypesystem.base.module.acl.service.SysRoleService;
import org.pzy.archetypesystem.base.module.acl.vo.SysRoleVO;
import org.pzy.opensource.domain.PageT;
import org.pzy.opensource.domain.ResultT;
import org.pzy.opensource.redis.support.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SysRole 的rest服务
 *
 * @author pan
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/acl/sys-role")
@Api(tags = "SysRole的rest服务")
public class SysRoleController {

    @Autowired
    private SysRoleService service;

    @DeleteMapping("clear-cache")
    @ApiOperation(value = "SysRole清除缓存")
    public ResultT clearCache() {
        service.clearCache();
        System.err.println(RedisUtil.removeByKeyPrefix("winter-cache*"));
        return ResultT.success();
    }

    @GetMapping
    @ApiOperation(value = "SysRole分页查找", notes = "未找到匹配数据,结果数据为空集合")
    public ResultT<PageT<SysRoleVO>> searchPage(SysRoleSearchDTO dto) {
        PageT<SysRoleVO> result = service.pageAndCache(dto);
        return ResultT.success(result);
    }

    @PostMapping
    @ApiOperation(value = "SysRole新增", notes = "结果数据,为新增数据的id")
    public ResultT<Long> add(@RequestBody SysRoleAddDTO dto) {
        Long id = service.saveAndClearCache(dto);
        return ResultT.success(id);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "SysRole详细查找", notes = "未找到匹配数据,结果数据为null")
    @ApiImplicitParam(name = "id", value = "唯一标识", dataType = "Long", paramType = "path", required = true, example = "1")
    public ResultT<SysRoleVO> searchById(@PathVariable("id") Long id) {
        SysRoleVO result = service.getByIdAndCache(id);
        return ResultT.success(result);
    }

    @PutMapping
    @ApiOperation(value = "SysRole编辑", notes = "结果数据,为实际的业务逻辑,是否执行成功")
    public ResultT<Boolean> edit(@RequestBody SysRoleEditDTO dto) {
        boolean optSuc = service.updateByIdAndClearCache(dto);
        return ResultT.success(optSuc);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "SysRole删除", notes = "结果数据,为实际的业务逻辑,是否执行成功")
    @ApiImplicitParam(name = "id", value = "唯一标识", dataType = "Long", paramType = "path", required = true, example = "1")
    public ResultT<Boolean> removeById(@PathVariable("id") Long id) {
        boolean optSuc = service.removeByIdAndClearCache(id);
        return ResultT.success(optSuc);
    }

}