package org.pzy.archetypesystem.base.module.comm.dao;

import org.pzy.archetypesystem.base.module.comm.entity.CommDictionaryItem;
import org.pzy.opensource.mybatisplus.basemapper.WinterBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * comm_dictionary_item 表DAO接口
 *
 * @author pan
 * @since 2020-04-11
 */
@Repository
public interface CommDictionaryItemDAO extends WinterBaseMapper<CommDictionaryItem> {

}
