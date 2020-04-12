package org.pzy.archetypesystem.base.module.comm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pzy.archetypesystem.base.module.comm.dto.CommDictionaryItemAddDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommDictionaryItemEditDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommDictionaryItemSearchDTO;
import org.pzy.archetypesystem.base.module.comm.entity.CommDictionaryItem;
import org.pzy.archetypesystem.base.module.comm.vo.CommDictionaryItemSimpleVO;
import org.pzy.archetypesystem.base.module.comm.vo.CommDictionaryItemVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


/**
 * comm_dictionary_item 表相关服务接口
 *
 * @author pan
 * @since 2020-04-11
 */
public interface CommDictionaryItemService extends IService<CommDictionaryItem> {

    /**
     * 清除该服务相关的缓存
     */
    void clearCache();

    /**
     * 分页查询并将结果缓存
     *
     * @param dto 查询条件
     * @return 查询结果
     */
    List<CommDictionaryItemVO> listAndCache(CommDictionaryItemSearchDTO dto);

    /**
     * 通过字典编码查找所有字典项
     *
     * @param dictionaryCode 字典编码
     * @param enable         是否只包含启用的
     * @return 匹配的字典项
     */
    List<CommDictionaryItemSimpleVO> listAndCache(String dictionaryCode, boolean enable);

    /**
     * 通过字典编码查找所有字典项
     *
     * @param dictionaryCodeColl 字典编码集合
     * @param enable             是否只包含启用的
     * @return 匹配的字典项. key为字典编码
     */
    Map<String, List<CommDictionaryItemSimpleVO>> mapAndCache(HashSet<String> dictionaryCodeColl, boolean enable);

    /**
     * itemCodeColl的所有字典项编码是否都存在于字典下
     *
     * @param dictionaryCode 字典编码
     * @param itemCodeColl   字典编码集合
     * @param enable         是否只验证启用的. 为true,则如果itemCodeColl某项存在, 但该项是被禁用的, 结果也为false
     */
    boolean validateCodeAllExists(String dictionaryCode, HashSet<String> itemCodeColl, boolean enable);

    /**
     * 新增, 并清除缓存
     *
     * @param dto 待新增数据
     * @return 新增数据id
     */
    Long saveAndClearCache(@Valid @NotNull CommDictionaryItemAddDTO dto);

    /**
     * 根据id查询, 并缓存
     *
     * @param id 主键ID
     */
    CommDictionaryItemVO getByIdAndCache(Serializable id);

    /**
     * 根据id更新, 并清除缓存
     *
     * @param dto 待更新对象
     * @return 是否更新成功
     */
    boolean updateByIdAndClearCache(@Valid @NotNull CommDictionaryItemEditDTO dto);

    /**
     * 根据id删除, 并清除缓存
     *
     * @param id 主键ID
     */
    boolean removeByIdAndClearCache(Serializable id);
}
