package org.pzy.archetypesystem.base.module.comm.dao;

import org.pzy.archetypesystem.base.module.comm.entity.CommLog;
import org.pzy.opensource.mybatisplus.basemapper.WinterBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * comm_log 表DAO接口
 *
 * @author pan
 * @since 2020-04-06
 */
@Repository
public interface CommLogDAO extends WinterBaseMapper<CommLog> {

}
