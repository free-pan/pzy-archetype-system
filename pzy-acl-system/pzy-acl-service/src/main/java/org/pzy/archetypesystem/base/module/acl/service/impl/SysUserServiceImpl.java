package org.pzy.archetypesystem.base.module.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.pzy.opensource.domain.vo.PageVO;
import org.pzy.opensource.domain.PageT;

import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.base.module.acl.entity.*;
import org.pzy.archetypesystem.base.module.acl.dao.*;
import org.pzy.archetypesystem.base.module.acl.vo.*;
import org.pzy.archetypesystem.base.module.acl.dto.*;
import org.pzy.archetypesystem.base.module.acl.mapstruct.*;
import org.pzy.archetypesystem.base.module.acl.service.SysUserService;
import org.pzy.opensource.mybatisplus.service.ServiceTemplate;
import org.pzy.opensource.mybatisplus.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.cache.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * sys_user 表相关服务实现类
 *
 * @author pan
 * @since 2020-04-05
 */
@Slf4j
@Service
@Validated
@CacheConfig(cacheNames = SysUserServiceImpl.CACHE_NAME)
public class SysUserServiceImpl extends ServiceTemplate<SysUserDAO, SysUser> implements SysUserService {

    public static final String CACHE_NAME = "SysUserServiceImpl";

    @Autowired
    private SysUserMapStruct mapStruct;

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public void clearCache() {
        if (log.isDebugEnabled()) {
            log.debug(String.format("清除[%s]服务类缓存!", this.getClass().getName()));
        }
    }

    @Cacheable(sync = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly=true)
    @Override
    public PageT<SysUserVO> pageAndCache(SysUserSearchDTO dto){
        // 系统的分页条件转换为mybatis plus的分页条件
        IPage<SysUser> mybatisPlusPageCondition = toMybatisPlusPage(dto.getPg());
        // 构建mybatis plus查询条件
        QueryWrapper<SysUser> queryWrapper = buildQueryWrapper();
        // mybatis plus分页查询
        IPage<SysUser> mybatisPlusPageResult = super.page(mybatisPlusPageCondition, queryWrapper);
        // mybatis plus分页结果, 转系统分页结果
        List<SysUserVO> voList = this.mapStruct.entityToDTO(mybatisPlusPageResult.getRecords());
        return PageUtil.mybatisPlusPage2PageT(mybatisPlusPageResult, voList);
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public Long saveAndClearCache(@Valid @NotNull SysUserAddDTO dto){
        // 对象转换
        SysUser entity = mapStruct.addSourceToEntity(dto);
        // 持久化
        boolean optSuc = super.save(entity);
        return entity.getId();
    }

    @Cacheable(sync = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly=true)
    @Override
    public SysUserVO getByIdAndCache(Serializable id){
        SysUser entity = super.getById(id);
        return this.mapStruct.entityToDTO(entity);
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean updateByIdAndClearCache(@Valid @NotNull SysUserEditDTO dto){
        // 对象转换
        SysUser entity = this.mapStruct.editSourceToEntity(dto);
        return super.updateById(entity);
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean removeByIdAndClearCache(Serializable id){
        return super.removeById(id);
    }
}
