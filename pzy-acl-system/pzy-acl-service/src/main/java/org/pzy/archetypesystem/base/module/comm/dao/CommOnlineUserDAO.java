package org.pzy.archetypesystem.base.module.comm.dao;

import org.pzy.archetypesystem.base.module.comm.entity.CommOnlineUser;
import org.pzy.opensource.mybatisplus.basemapper.WinterBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * comm_online_user 表DAO接口
 *
 * @author pan
 * @since 2020-04-06
 */
@Repository
public interface CommOnlineUserDAO extends WinterBaseMapper<CommOnlineUser> {

}
