package org.pzy.archetypesystem.base.module.comm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import org.pzy.archetypesystem.base.module.comm.service.CommDictionaryItemService;
import org.pzy.archetypesystem.base.module.comm.dto.*;
import org.pzy.archetypesystem.base.module.comm.vo.*;
import org.pzy.opensource.domain.PageT;
import org.pzy.opensource.domain.ResultT;
import org.pzy.opensource.domain.vo.PageVO;

/**
 * CommDictionaryItem 的rest服务
 *
 * @author pan
 * @since 2020-04-11
 */
@RestController
@RequestMapping("/comm/comm-dictionary-item")
@Api(tags = "CommDictionaryItem的rest服务")
public class CommDictionaryItemController {

    @Autowired
    private CommDictionaryItemService service;

    @DeleteMapping("clear-cache")
    @ApiOperation(value = "CommDictionaryItem清除缓存")
    public ResultT clearCache() {
        service.clearCache();
        return ResultT.success();
    }

    @GetMapping
    @ApiOperation(value = "CommDictionaryItem分页查找", notes = "未找到匹配数据,结果数据为空集合")
    public ResultT<PageT<CommDictionaryItemVO>> searchPage(CommDictionaryItemSearchDTO dto) {
        PageT<CommDictionaryItemVO> result = service.pageAndCache(dto);
        return ResultT.success(result);
    }

    @PostMapping
    @ApiOperation(value = "CommDictionaryItem新增", notes = "结果数据,为新增数据的id")
    public ResultT<Long> add(@RequestBody CommDictionaryItemAddDTO dto) {
        Long id = service.saveAndClearCache(dto);
        return ResultT.success(id);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "CommDictionaryItem详细查找", notes = "未找到匹配数据,结果数据为null")
    @ApiImplicitParam(name = "id", value = "唯一标识", dataType = "Long", paramType = "path", required = true, example = "1")
    public ResultT<CommDictionaryItemVO> searchById(@PathVariable("id") Long id) {
        CommDictionaryItemVO result = service.getByIdAndCache(id);
        return ResultT.success(result);
    }

    @PutMapping
    @ApiOperation(value = "CommDictionaryItem编辑", notes = "结果数据,为实际的业务逻辑,是否执行成功")
    public ResultT<Boolean> edit(@RequestBody CommDictionaryItemEditDTO dto) {
        boolean optSuc = service.updateByIdAndClearCache(dto);
        return ResultT.success(optSuc);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "CommDictionaryItem删除", notes = "结果数据,为实际的业务逻辑,是否执行成功")
    @ApiImplicitParam(name = "id", value = "唯一标识", dataType = "Long", paramType = "path", required = true, example = "1")
    public ResultT<Boolean> removeById(@PathVariable("id") Long id) {
        boolean optSuc = service.removeByIdAndClearCache(id);
        return ResultT.success(optSuc);
    }

}