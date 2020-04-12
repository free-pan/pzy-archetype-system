package org.pzy.archetypesystem.base.module.comm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.base.module.comm.dao.CommDictionaryItemDAO;
import org.pzy.archetypesystem.base.module.comm.dto.CommDictionaryItemAddDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommDictionaryItemEditDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommDictionaryItemSearchDTO;
import org.pzy.archetypesystem.base.module.comm.entity.CommDictionaryItem;
import org.pzy.archetypesystem.base.module.comm.mapstruct.CommDictionaryItemMapStruct;
import org.pzy.archetypesystem.base.module.comm.service.CommDictionaryItemService;
import org.pzy.archetypesystem.base.module.comm.vo.CommDictionaryItemSimpleVO;
import org.pzy.archetypesystem.base.module.comm.vo.CommDictionaryItemVO;
import org.pzy.opensource.domain.enums.DisableStatusEnum;
import org.pzy.opensource.mybatisplus.service.ServiceTemplate;
import org.pzy.opensource.redis.support.springboot.annotation.LockBuilder;
import org.pzy.opensource.redis.support.springboot.annotation.WinterLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * comm_dictionary_item 表相关服务实现类
 *
 * @author pan
 * @since 2020-04-11
 */
@Slf4j
@Service
@Validated
@CacheConfig(cacheNames = CommDictionaryItemServiceImpl.CACHE_NAME)
public class CommDictionaryItemServiceImpl extends ServiceTemplate<CommDictionaryItemDAO, CommDictionaryItem> implements CommDictionaryItemService {

    public static final String CACHE_NAME = "CommDictionaryItemServiceImpl";

    @Autowired
    private CommDictionaryItemMapStruct mapStruct;

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public void clearCache() {
        if (log.isDebugEnabled()) {
            log.debug("清除[{}]服务类缓存!", this.getClass().getName());
        }
    }

    @Cacheable(sync = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<CommDictionaryItemVO> listAndCache(CommDictionaryItemSearchDTO dto) {
        if (null == dto) {
            return Collections.EMPTY_LIST;
        }
        // 查询关键词去空格
        CommDictionaryItemSearchDTO condition = mapStruct.searchDtoToSearchDTO(dto);
        condition.setKw(super.keywordEscape(condition.getKw()));
        List<CommDictionaryItem> listResult = super.baseMapper.selectListByCondition(condition);
        List<CommDictionaryItemVO> voList = this.mapStruct.entityToDTO(listResult);
        // 排序
        Collections.sort(voList, (s1, s2) -> {
            if (s1.getViewPriority() < s2.getViewPriority()) {
                return 1;
            } else {
                return -1;
            }
        });
        return voList;
    }

    @Cacheable(sync = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<CommDictionaryItemSimpleVO> listAndCache(String dictionaryCode, boolean enable) {
        DisableStatusEnum status = enable ? DisableStatusEnum.enable : null;
        List<CommDictionaryItemSimpleVO> list = super.baseMapper.selectByDictionaryCode(dictionaryCode, status);
        // 排序
        sortByViewPriority(list);
        return list;
    }

    @Override
    public Map<String, List<CommDictionaryItemSimpleVO>> mapAndCache(HashSet<String> dictionaryCodeColl, boolean enable) {
        DisableStatusEnum status = enable ? DisableStatusEnum.enable : null;
        List<CommDictionaryItemSimpleVO> list = super.baseMapper.selectByDictionaryCodeColl(dictionaryCodeColl, status);
        // 排序
        sortByViewPriority(list);
        Map<String, List<CommDictionaryItemSimpleVO>> resultMap = list.stream().collect(Collectors.groupingBy(CommDictionaryItemSimpleVO::getDictionaryCode));
        return resultMap;
    }

    /**
     * 按照显示优先级降序
     *
     * @param list
     */
    private void sortByViewPriority(List<CommDictionaryItemSimpleVO> list) {
        Collections.sort(list, (s1, s2) -> {
            if (s1.getViewPriority() < s2.getViewPriority()) {
                return 1;
            } else {
                return -1;
            }
        });
    }

    @Override
    public boolean validateCodeAllExists(String dictionaryCode, HashSet<String> itemCodeColl, boolean enable) {
        DisableStatusEnum status = enable ? DisableStatusEnum.enable : null;
        int count = super.baseMapper.selectCountByCondition(dictionaryCode, itemCodeColl, status);
        return count == itemCodeColl.size();
    }

    @CacheEvict(allEntries = true)
    @WinterLock(lockBuilder = @LockBuilder(condition = "null!=[0].code", key = "[0].dictionaryId + '-' +[0].code"))
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public Long saveAndClearCache(@Valid @NotNull CommDictionaryItemAddDTO dto) {
        // 对象转换
        CommDictionaryItem entity = mapStruct.addSourceToEntity(dto);
        // 持久化
        boolean optSuc = super.save(entity);
        return entity.getId();
    }

    @Cacheable(sync = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public CommDictionaryItemVO getByIdAndCache(Serializable id) {
        if (null == id) {
            return null;
        }
        CommDictionaryItem entity = super.getById(id);
        return this.mapStruct.entityToDTO(entity);
    }

    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean updateByIdAndClearCache(@Valid @NotNull CommDictionaryItemEditDTO dto) {
        // 对象转换
        CommDictionaryItem entity = this.mapStruct.editSourceToEntity(dto);
        return super.updateById(entity);
    }

    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean removeByIdAndClearCache(Serializable id) {
        if (null == id) {
            return false;
        }
        // 如果表和实体符合系统的逻辑删除规范, 请调用 super.baseMapper.logicDeleteById(id) 方法进行逻辑删除
        return super.removeById(id);
    }
}
