package org.pzy.archetypesystem.acl.sysuser.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.pzy.archetypesystem.acl.sysuser.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * (sys_user)表DAO类
 *
 * @author pan
 * @since 2020-03-24 16:49:38
 */
@Repository
public interface SysUserDAO extends BaseMapper<SysUser> {

    /**
     * 根据email查找数量
     *
     * @param email 邮箱
     * @return 匹配记录数
     */
    @Select("select count(id) from sys_user where email=#{email}")
    int selectEmailCount(@Param("email") String email);
}