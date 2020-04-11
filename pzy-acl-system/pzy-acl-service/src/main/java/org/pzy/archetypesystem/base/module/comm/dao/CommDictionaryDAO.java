package org.pzy.archetypesystem.base.module.comm.dao;

import org.pzy.archetypesystem.base.module.comm.entity.CommDictionary;
import org.pzy.opensource.mybatisplus.basemapper.WinterBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * comm_dictionary 表DAO接口
 *
 * @author pan
 * @since 2020-04-11
 */
@Repository
public interface CommDictionaryDAO extends WinterBaseMapper<CommDictionary> {

}
