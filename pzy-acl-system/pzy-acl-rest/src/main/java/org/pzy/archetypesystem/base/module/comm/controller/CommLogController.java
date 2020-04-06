package org.pzy.archetypesystem.base.module.comm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.pzy.archetypesystem.base.module.comm.dto.CommLogAddDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommLogEditDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommLogSearchDTO;
import org.pzy.archetypesystem.base.module.comm.service.CommLogService;
import org.pzy.archetypesystem.base.module.comm.vo.CommLogVO;
import org.pzy.opensource.domain.PageT;
import org.pzy.opensource.domain.ResultT;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CommLog 的rest服务
 *
 * @author pan
 * @since 2020-04-06
 */
@RestController
@RequestMapping("/comm/comm-log")
@Api(tags = "CommLog的rest服务")
public class CommLogController {

    @Autowired
    private CommLogService service;

    @DeleteMapping("clear-cache")
    @ApiOperation(value = "CommLog清除缓存")
    public ResultT clearCache() {
        service.clearCache();
        return ResultT.success();
    }

    @GetMapping
    @ApiOperation(value = "CommLog分页查找", notes = "未找到匹配数据,结果数据为空集合")
    public ResultT<PageT<CommLogVO>> searchPage(CommLogSearchDTO dto) {
        PageT<CommLogVO> result = service.pageAndCache(dto);
        return ResultT.success(result);
    }

    @PostMapping
    @ApiOperation(value = "CommLog新增", notes = "结果数据,为新增数据的id")
    public ResultT<Long> add(@RequestBody CommLogAddDTO dto) {
        Long id = service.saveAndClearCache(dto);
        return ResultT.success(id);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "CommLog详细查找", notes = "未找到匹配数据,结果数据为null")
    @ApiImplicitParam(name = "id", value = "唯一标识", dataType = "Long", paramType = "path", required = true, example = "1")
    public ResultT<CommLogVO> searchById(@PathVariable("id") Long id) {
        CommLogVO result = service.getByIdAndCache(id);
        return ResultT.success(result);
    }

    @PutMapping
    @ApiOperation(value = "CommLog编辑", notes = "结果数据,为实际的业务逻辑,是否执行成功")
    public ResultT<Boolean> edit(@RequestBody CommLogEditDTO dto) {
        boolean optSuc = service.updateByIdAndClearCache(dto);
        return ResultT.success(optSuc);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "CommLog删除", notes = "结果数据,为实际的业务逻辑,是否执行成功")
    @ApiImplicitParam(name = "id", value = "唯一标识", dataType = "Long", paramType = "path", required = true, example = "1")
    public ResultT<Boolean> removeById(@PathVariable("id") Long id) {
        boolean optSuc = service.removeByIdAndClearCache(id);
        return ResultT.success(optSuc);
    }

}