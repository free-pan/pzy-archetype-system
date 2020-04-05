package org.pzy.archetypesystem.base.module.acl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.pzy.archetypesystem.base.module.acl.dto.SysUserAddDTO;
import org.pzy.archetypesystem.base.module.acl.dto.SysUserEditDTO;
import org.pzy.archetypesystem.base.module.acl.dto.SysUserSearchDTO;
import org.pzy.archetypesystem.base.module.acl.service.SysUserService;
import org.pzy.archetypesystem.base.module.acl.vo.SysUserVO;
import org.pzy.opensource.domain.PageT;
import org.pzy.opensource.domain.ResultT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * SysUser 的rest服务
 *
 * @author pan
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/acl/sys-user")
@Api(tags = "SysUser的rest服务")
public class SysUserController {

    RedisTemplate redisTemplate;
    @Autowired
    private SysUserService service;

    @DeleteMapping("clear-cache")
    @ApiOperation(value = "SysUser清除缓存")
    public ResultT clearCache() {
        service.clearCache();
        return ResultT.success();
    }

    @GetMapping
    @ApiOperation(value = "SysUser分页查找", notes = "未找到匹配数据,结果数据为空集合")
    public ResultT<PageT<SysUserVO>> searchPage(SysUserSearchDTO dto) {
        PageT<SysUserVO> result = service.pageAndCache(dto);
        return ResultT.success(result);
    }

    @PostMapping
    @ApiOperation(value = "SysUser新增", notes = "结果数据,为新增数据的id")
    public ResultT<Long> add(@RequestBody SysUserAddDTO dto) {
        Long id = service.saveAndClearCache(dto);
        return ResultT.success(id);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "SysUser详细查找", notes = "未找到匹配数据,结果数据为null")
    @ApiImplicitParam(name = "id", value = "唯一标识", dataType = "Long", paramType = "path", required = true, example = "1")
    public ResultT<SysUserVO> searchById(@PathVariable("id") Long id) {
        SysUserVO result = service.getByIdAndCache(id);
        return ResultT.success(result);
    }

    @PutMapping
    @ApiOperation(value = "SysUser编辑", notes = "结果数据,为实际的业务逻辑,是否执行成功")
    public ResultT<Boolean> edit(@RequestBody SysUserEditDTO dto) {
        boolean optSuc = service.updateByIdAndClearCache(dto);
        return ResultT.success(optSuc);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "SysUser删除", notes = "结果数据,为实际的业务逻辑,是否执行成功")
    @ApiImplicitParam(name = "id", value = "唯一标识", dataType = "Long", paramType = "path", required = true, example = "1")
    public ResultT<Boolean> removeById(@PathVariable("id") Long id) {
        boolean optSuc = service.removeByIdAndClearCache(id);
        return ResultT.success(optSuc);
    }

}