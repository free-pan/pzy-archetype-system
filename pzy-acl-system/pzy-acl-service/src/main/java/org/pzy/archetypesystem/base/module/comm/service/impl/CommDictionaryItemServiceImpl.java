package org.pzy.archetypesystem.base.module.comm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.pzy.opensource.domain.vo.PageVO;
import org.pzy.opensource.domain.PageT;

import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.base.module.comm.entity.*;
import org.pzy.archetypesystem.base.module.comm.dao.*;
import org.pzy.archetypesystem.base.module.comm.vo.*;
import org.pzy.archetypesystem.base.module.comm.dto.*;
import org.pzy.archetypesystem.base.module.comm.mapstruct.*;
import org.pzy.archetypesystem.base.module.comm.service.CommDictionaryItemService;
import org.pzy.opensource.mybatisplus.service.ServiceTemplate;
import org.pzy.opensource.mybatisplus.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * comm_dictionary_item 表相关服务实现类
 *
 * @author pan
 * @since 2020-04-11
 */
@Slf4j
@Service
@Validated
@CacheConfig(cacheNames = CommDictionaryItemServiceImpl.CACHE_NAME)
public class CommDictionaryItemServiceImpl extends ServiceTemplate<CommDictionaryItemDAO, CommDictionaryItem> implements CommDictionaryItemService {

    public static final String CACHE_NAME = "CommDictionaryItemServiceImpl";

    @Autowired
    private CommDictionaryItemMapStruct mapStruct;

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public void clearCache() {
        if (log.isDebugEnabled()) {
            log.debug("清除[{}]服务类缓存!", this.getClass().getName());
        }
    }

    @Cacheable(sync = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly=true)
    @Override
    public PageT<CommDictionaryItemVO> pageAndCache(CommDictionaryItemSearchDTO dto){
        if(null==dto){
            return PageT.EMPTY();
        }
        // 查询关键词去空格
        CommDictionaryItemSearchDTO condition = mapStruct.searchDtoToSearchDTO(dto);
        // 系统的分页条件转换为mybatis plus的分页条件
        IPage<CommDictionaryItem> mybatisPlusPageCondition = toMybatisPlusPage(condition.getPg());
        // 构建mybatis plus查询条件
        QueryWrapper<CommDictionaryItem> queryWrapper = buildQueryWrapper();
        // mybatis plus分页查询
        IPage<CommDictionaryItem> mybatisPlusPageResult = super.page(mybatisPlusPageCondition, queryWrapper);
        // mybatis plus分页结果, 转系统分页结果
        List<CommDictionaryItemVO> voList = this.mapStruct.entityToDTO(mybatisPlusPageResult.getRecords());
        return PageUtil.mybatisPlusPage2PageT(mybatisPlusPageResult, voList);
    }

    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public Long saveAndClearCache(@Valid @NotNull CommDictionaryItemAddDTO dto){
        // 对象转换
        CommDictionaryItem entity = mapStruct.addSourceToEntity(dto);
        // 持久化
        boolean optSuc = super.save(entity);
        return entity.getId();
    }

    @Cacheable(sync = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly=true)
    @Override
    public CommDictionaryItemVO getByIdAndCache(Serializable id){
        if (null == id) {
            return null;
        }
        CommDictionaryItem entity = super.getById(id);
        return this.mapStruct.entityToDTO(entity);
    }

    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean updateByIdAndClearCache(@Valid @NotNull CommDictionaryItemEditDTO dto){
        // 对象转换
        CommDictionaryItem entity = this.mapStruct.editSourceToEntity(dto);
        return super.updateById(entity);
    }

    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean removeByIdAndClearCache(Serializable id){
        if(null==id){
            return false;
        }
        // 如果表和实体符合系统的逻辑删除规范, 请调用 super.baseMapper.logicDeleteById(id) 方法进行逻辑删除
        return super.removeById(id);
    }
}
