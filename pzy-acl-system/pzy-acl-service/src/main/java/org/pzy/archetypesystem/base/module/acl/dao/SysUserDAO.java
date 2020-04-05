package org.pzy.archetypesystem.base.module.acl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.pzy.archetypesystem.base.module.acl.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * sys_user 表DAO接口
 *
 * @author pan
 * @since 2020-04-05
 */
@Repository
public interface SysUserDAO extends BaseMapper<SysUser> {

}
