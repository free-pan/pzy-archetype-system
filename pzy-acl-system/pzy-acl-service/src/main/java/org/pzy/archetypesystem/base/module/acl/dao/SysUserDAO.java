package org.pzy.archetypesystem.base.module.acl.dao;

import org.apache.ibatis.annotations.Param;
import org.pzy.archetypesystem.base.module.acl.entity.SysUser;
import org.pzy.opensource.mybatisplus.basemapper.WinterBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * sys_user 表DAO接口
 *
 * @author pan
 * @since 2020-04-05
 */
@Repository
public interface SysUserDAO extends WinterBaseMapper<SysUser> {

    /**
     * 获取邮箱数量
     *
     * @param email     邮箱
     * @param excludeId 排除的id[可选]
     * @return 匹配的邮箱数量
     */
    Integer getEmailCount(@Param("email") String email, @Param("excludeId") Long excludeId);
}
