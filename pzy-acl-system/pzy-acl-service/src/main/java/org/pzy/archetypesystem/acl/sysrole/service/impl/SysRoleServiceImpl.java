package org.pzy.archetypesystem.acl.sysrole.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.acl.sysrole.dao.SysRoleDAO;
import org.pzy.archetypesystem.acl.sysrole.entity.SysRole;
import org.pzy.archetypesystem.acl.sysrole.service.SysRoleService;
import org.pzy.opensource.mybatisplus.service.ServiceTemplateImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * sys_role 表相关服务实现类
 *
 * @author pan
 * @since 2020-04-03
 */
@Slf4j
@Service
@Validated
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
@CacheConfig(cacheNames = SysRoleServiceImpl.CACHE_NAME)
public class SysRoleServiceImpl extends ServiceTemplateImpl<SysRoleDAO, SysRole> implements SysRoleService {

    public static final String CACHE_NAME = "ACL";

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public void clearCache() {
        if (log.isDebugEnabled()) {
            log.debug(String.format("清除[%s]服务类缓存!", this.getClass().getName()));
        }
    }
}
