package org.pzy.archetypesystem.base.module.comm.dao;

import org.apache.ibatis.annotations.Param;
import org.pzy.archetypesystem.base.module.comm.dto.CommDictionaryItemSearchDTO;
import org.pzy.archetypesystem.base.module.comm.entity.CommDictionaryItem;
import org.pzy.archetypesystem.base.module.comm.vo.CommDictionaryItemSimpleVO;
import org.pzy.opensource.domain.enums.DisableStatusEnum;
import org.pzy.opensource.mybatisplus.basemapper.WinterBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * comm_dictionary_item 表DAO接口
 *
 * @author pan
 * @since 2020-04-11
 */
@Repository
public interface CommDictionaryItemDAO extends WinterBaseMapper<CommDictionaryItem> {

    /**
     * 按条件查找所有
     *
     * @param condition 查询条件
     * @return 匹配的记录
     */
    List<CommDictionaryItem> selectListByCondition(CommDictionaryItemSearchDTO condition);

    /**
     * 查找字典编码下所有的字典项编码中有多少个与itemCodeColl的编码匹配, status表示对那种状态的字典项数据进行比较
     *
     * @param dictionaryCode 字典编码
     * @param itemCodeColl   字典项集合
     * @param status         表示对那种状态的字典项数据进行比较, 为null时, 表示对所有状态的字典项进行比较
     * @return 匹配的字典项数量
     */
    int selectCountByCondition(@Param("dictionaryCode") String dictionaryCode, @Param("itemCodeColl") Collection<String> itemCodeColl, @Param("status") DisableStatusEnum status);

    /**
     * 通过字典编码查找指定状态的字典项目
     *
     * @param dictionaryCode 字典编码
     * @param status         字典项状态. 如果为null, 表示查找素有状态的字典项
     * @return 匹配的记录
     */
    List<CommDictionaryItemSimpleVO> selectByDictionaryCode(@Param("dictionaryCode") String dictionaryCode, @Param("status") DisableStatusEnum status);

    /**
     * 通过字典编码查找指定状态的字典项目
     *
     * @param dictionaryCodeColl 字典编码
     * @param status             字典项状态. 如果为null, 表示查找素有状态的字典项
     * @return 匹配的记录
     */
    List<CommDictionaryItemSimpleVO> selectByDictionaryCodeColl(@Param("dictionaryCodeColl") Collection<String> dictionaryCodeColl, @Param("status") DisableStatusEnum status);
}
