package org.pzy.archetypesystem.acl.sysrole.service.impl;

import org.pzy.archetypesystem.acl.sysrole.entity.SysRole;
import org.pzy.archetypesystem.acl.sysrole.mapper.SysRoleDAO;
import org.pzy.archetypesystem.acl.sysrole.service.SysRoleService;
import org.pzy.opensource.mybatisplus.service.ServiceTemplateImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.cache.annotation.CacheConfig;

/**
 * sys_role 表相关服务实现类
 *
 * @author pan
 * @since 2020-04-03
 */
@Service
@Validated
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
@CacheConfig(cacheNames = SysRoleServiceImpl.CACHE_NAME)
public class SysRoleServiceImpl extends ServiceTemplateImpl<SysRoleDAO, SysRole> implements SysRoleService {

    public static final String CACHE_NAME = "SysRoleServiceImpl";
}
