package org.pzy.archetypesystem.base.module.comm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.pzy.archetypesystem.base.module.comm.dto.CommOnlineUserAddDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommOnlineUserEditDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommOnlineUserSearchDTO;
import org.pzy.archetypesystem.base.module.comm.service.CommOnlineUserService;
import org.pzy.archetypesystem.base.module.comm.vo.CommOnlineUserVO;
import org.pzy.opensource.domain.PageT;
import org.pzy.opensource.domain.ResultT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * CommOnlineUser 的rest服务
 *
 * @author pan
 * @since 2020-04-06
 */
@RestController
@RequestMapping("/comm/comm-online-user")
@Api(tags = "CommOnlineUser的rest服务")
public class CommOnlineUserController {

    @Autowired
    private CommOnlineUserService service;

    @DeleteMapping("clear-cache")
    @ApiOperation(value = "CommOnlineUser清除缓存")
    public ResultT clearCache() {
        service.clearCache();
        return ResultT.success();
    }

    @GetMapping
    @ApiOperation(value = "CommOnlineUser分页查找", notes = "未找到匹配数据,结果数据为空集合")
    public ResultT<PageT<CommOnlineUserVO>> searchPage(CommOnlineUserSearchDTO dto) {
        PageT<CommOnlineUserVO> result = service.pageAndCache(dto);
        return ResultT.success(result);
    }

    @PostMapping
    @ApiOperation(value = "CommOnlineUser新增", notes = "结果数据,为新增数据的id")
    public ResultT<Long> add(@RequestBody CommOnlineUserAddDTO dto) {
        Long id = service.saveAndClearCache(dto);
        return ResultT.success(id);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "CommOnlineUser详细查找", notes = "未找到匹配数据,结果数据为null")
    @ApiImplicitParam(name = "id", value = "唯一标识", dataType = "Long", paramType = "path", required = true, example = "1")
    public ResultT<CommOnlineUserVO> searchById(@PathVariable("id") Long id) {
        CommOnlineUserVO result = service.getByIdAndCache(id);
        return ResultT.success(result);
    }

    @PutMapping
    @ApiOperation(value = "CommOnlineUser编辑", notes = "结果数据,为实际的业务逻辑,是否执行成功")
    public ResultT<Boolean> edit(@RequestBody CommOnlineUserEditDTO dto) {
        boolean optSuc = service.updateByIdAndClearCache(dto);
        return ResultT.success(optSuc);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "CommOnlineUser删除", notes = "结果数据,为实际的业务逻辑,是否执行成功")
    @ApiImplicitParam(name = "id", value = "唯一标识", dataType = "Long", paramType = "path", required = true, example = "1")
    public ResultT<Boolean> removeById(@PathVariable("id") Long id) {
        boolean optSuc = service.removeByIdAndClearCache(id);
        return ResultT.success(optSuc);
    }

}