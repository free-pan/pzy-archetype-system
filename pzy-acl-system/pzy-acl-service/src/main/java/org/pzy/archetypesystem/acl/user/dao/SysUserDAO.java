package org.pzy.archetypesystem.acl.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.pzy.archetypesystem.acl.user.domain.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * (sys_user)表DAO类
 *
 * @author pan
 * @since 2020-03-24 16:49:38
 */
@Repository
public interface SysUserDAO extends BaseMapper<SysUser> {

}