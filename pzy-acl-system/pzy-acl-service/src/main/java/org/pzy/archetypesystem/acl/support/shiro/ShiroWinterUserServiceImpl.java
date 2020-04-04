package org.pzy.archetypesystem.acl.support.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.pzy.archetypesystem.acl.sysuser.entity.SysUser;
import org.pzy.archetypesystem.acl.sysuser.service.SysUserService;
import org.pzy.opensource.domain.GlobalConstant;
import org.pzy.opensource.security.domain.bo.PermissionInfoBO;
import org.pzy.opensource.security.domain.bo.ShiroUserBO;
import org.pzy.opensource.security.service.ShiroWinterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author pan
 * @date 2020/3/26
 */
@Component
public class ShiroWinterUserServiceImpl implements ShiroWinterUserService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public ShiroUserBO loadUserInfoByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SysUser.EMAIL, username);
        SysUser sysUser = sysUserService.getOne(queryWrapper);
        ShiroUserBO shiroUserBO = null;
        if (null != sysUser) {
            shiroUserBO = new ShiroUserBO();
            shiroUserBO.setUkFlag(String.valueOf(sysUser.getId()));
            shiroUserBO.setUsername(username);
            shiroUserBO.setPassword(sysUser.getPassword());
            shiroUserBO.setEnabled(sysUser.getActive() == GlobalConstant.LOGIC_DEL_YES);
        }
        return shiroUserBO;
    }

    @Override
    public List<String> loadRoleByUsername(ShiroUserBO shiroUser) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<PermissionInfoBO> loadPermissionByUsername(ShiroUserBO shiroUser) {
        return Collections.EMPTY_LIST;
    }
}
