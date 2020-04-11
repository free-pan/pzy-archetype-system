package org.pzy.archetypesystem.base.module.comm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pzy.archetypesystem.base.module.comm.dto.*;
import org.pzy.archetypesystem.base.module.comm.entity.CommOnlineUser;
import org.pzy.archetypesystem.base.module.comm.vo.*;
import org.pzy.opensource.domain.PageT;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * comm_online_user 表相关服务接口
 *
 * @author pan
 * @since 2020-04-06
 */
public interface CommOnlineUserService extends IService<CommOnlineUser> {

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
    PageT<CommOnlineUserVO> pageAndCache(CommOnlineUserSearchDTO dto);

    /**
     * 新增, 并清除缓存
     *
     * @param dto 待新增数据
     * @return 新增数据id
     */
    Long saveAndClearCache(@Valid @NotNull CommOnlineUserAddDTO dto);

    /**
     * 根据id查询, 并缓存
     *
     * @param id 主键ID
     */
    CommOnlineUserVO getByIdAndCache(Serializable id);

    /**
     * 根据sessionid删除在线用户, 并清除缓存
     *
     * @param sessionId
     */
    void deleteBySessionIdAndClearCache(String sessionId);
}
