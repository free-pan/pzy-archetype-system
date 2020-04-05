package org.pzy.archetypesystem.base.module.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.base.module.acl.dao.*;
import org.pzy.archetypesystem.base.module.acl.dto.*;
import org.pzy.archetypesystem.base.module.acl.entity.*;
import org.pzy.archetypesystem.base.module.acl.mapstruct.*;
import org.pzy.archetypesystem.base.module.acl.service.SysRoleService;
import org.pzy.archetypesystem.base.module.acl.vo.*;
import org.pzy.opensource.domain.PageT;
import org.pzy.opensource.mybatisplus.service.ServiceTemplate;
import org.pzy.opensource.mybatisplus.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * sys_role 表相关服务实现类
 *
 * @author pan
 * @since 2020-04-05
 */
@Slf4j
@Service
@Validated
@CacheConfig(cacheNames = SysRoleServiceImpl.CACHE_NAME)
public class SysRoleServiceImpl extends ServiceTemplate<SysRoleDAO, SysRole> implements SysRoleService {

    public static final String CACHE_NAME = "SysRoleServiceImpl";

    @Autowired
    private SysRoleMapStruct mapStruct;

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
    public PageT<SysRoleVO> pageAndCache(SysRoleSearchDTO dto){
        // 系统的分页条件转换为mybatis plus的分页条件
        IPage<SysRole> mybatisPlusPageCondition = toMybatisPlusPage(dto.getPg());
        // 构建mybatis plus查询条件
        QueryWrapper<SysRole> queryWrapper = buildQueryWrapper();
        // mybatis plus分页查询
        IPage<SysRole> mybatisPlusPageResult = super.page(mybatisPlusPageCondition, queryWrapper);
        // mybatis plus分页结果, 转系统分页结果
        List<SysRoleVO> voList = this.mapStruct.entityToDTO(mybatisPlusPageResult.getRecords());
        return PageUtil.mybatisPlusPage2PageT(mybatisPlusPageResult, voList);
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public Long saveAndClearCache(@Valid @NotNull SysRoleAddDTO dto){
        // 对象转换
        SysRole entity = mapStruct.addSourceToEntity(dto);
        // 持久化
        boolean optSuc = super.save(entity);
        return entity.getId();
    }

    @Cacheable(sync = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly=true)
    @Override
    public SysRoleVO getByIdAndCache(Serializable id){
        if (null == id) {
            return null;
        }
        SysRole entity = super.getById(id);
        return this.mapStruct.entityToDTO(entity);
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean updateByIdAndClearCache(@Valid @NotNull SysRoleEditDTO dto){
        // 对象转换
        SysRole entity = this.mapStruct.editSourceToEntity(dto);
        return super.updateById(entity);
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean removeByIdAndClearCache(Serializable id){
        return super.removeById(id);
    }
}
