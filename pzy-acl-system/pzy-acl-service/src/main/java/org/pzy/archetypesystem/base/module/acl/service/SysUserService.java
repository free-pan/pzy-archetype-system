package org.pzy.archetypesystem.base.module.acl.service;

import org.pzy.archetypesystem.base.module.acl.entity.SysUser;
import org.pzy.archetypesystem.base.module.acl.dto.*;
import org.pzy.archetypesystem.base.module.acl.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.pzy.opensource.domain.PageT;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * sys_user 表相关服务接口
 *
 * @author pan
 * @since 2020-04-05
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 清除该服务相关的缓存
     */
    void clearCache() ;

    /**
     * 分页查询并将结果缓存
     *
     * @param dto 查询条件
     * @return 查询结果
     */
    PageT<SysUserVO> pageAndCache(SysUserSearchDTO dto);

    /**
    * 新增, 并清除缓存
    *
    * @param dto 待新增数据
    * @return 新增数据id
    */
    Long saveAndClearCache(@Valid @NotNull SysUserAddDTO dto);

    /**
     * 根据id查询, 并缓存
     *
     * @param id 主键ID
     */
    SysUserVO getByIdAndCache(Serializable id);

    /**
    * 根据id更新, 并清除缓存
    *
    * @param dto 待更新对象
    * @return 是否更新成功
    */
    boolean updateByIdAndClearCache(@Valid @NotNull SysUserEditDTO dto);

    /**
     * 根据id删除, 并清除缓存
     *
     * @param id 主键ID
     */
    boolean removeByIdAndClearCache(Serializable id);
}
