package org.pzy.archetypesystem.base.module.acl.dao;

import org.pzy.archetypesystem.base.module.acl.entity.SysRole;
import org.pzy.opensource.mybatisplus.basemapper.WinterBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * sys_role 表DAO接口
 *
 * @author pan
 * @since 2020-04-05
 */
@Repository
public interface SysRoleDAO extends WinterBaseMapper<SysRole> {

}
