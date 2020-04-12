package org.pzy.archetypesystem.base.module.comm.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.pzy.archetypesystem.base.module.comm.dto.CommDictionarySearchDTO;
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

    /**
     * 按条件查找
     *
     * @param page 分页条件
     * @param dto  查询条件
     * @return 匹配的记录
     */
    IPage<CommDictionary> selectByCondition(IPage<CommDictionary> page, @Param("dto") CommDictionarySearchDTO dto);
}
