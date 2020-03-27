package org.pzy.archetypesystem.acl.user.mapstruct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.pzy.archetypesystem.acl.user.domain.entity.SysUser;
import org.pzy.archetypesystem.acl.user.domain.vo.SimpleSysUserVO;
import org.pzy.opensource.comm.mapstruct.StringDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-26T19:28:32+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class SimpleSysUserMapStructImpl implements SimpleSysUserMapStruct {

    @Autowired
    private StringDataMapper stringDataMapper;

    @Override
    public SimpleSysUserVO sourceToTarget(SysUser source) {
        if ( source == null ) {
            return null;
        }

        SimpleSysUserVO simpleSysUserVO = new SimpleSysUserVO();

        simpleSysUserVO.setId( source.getId() );
        simpleSysUserVO.setName( stringDataMapper.stringMapper( source.getName() ) );
        simpleSysUserVO.setEmail( stringDataMapper.stringMapper( source.getEmail() ) );

        return simpleSysUserVO;
    }

    @Override
    public List<SimpleSysUserVO> sourceToTarget(Collection<SysUser> sources) {
        if ( sources == null ) {
            return null;
        }

        List<SimpleSysUserVO> list = new ArrayList<SimpleSysUserVO>( sources.size() );
        for ( SysUser sysUser : sources ) {
            list.add( sourceToTarget( sysUser ) );
        }

        return list;
    }

    @Override
    public SysUser targetToSource(SimpleSysUserVO target) {
        if ( target == null ) {
            return null;
        }

        SysUser sysUser = new SysUser();

        sysUser.setId( target.getId() );
        sysUser.setName( stringDataMapper.stringMapper( target.getName() ) );
        sysUser.setEmail( stringDataMapper.stringMapper( target.getEmail() ) );

        return sysUser;
    }

    @Override
    public List<SysUser> targetToSource(Collection<SimpleSysUserVO> targets) {
        if ( targets == null ) {
            return null;
        }

        List<SysUser> list = new ArrayList<SysUser>( targets.size() );
        for ( SimpleSysUserVO simpleSysUserVO : targets ) {
            list.add( targetToSource( simpleSysUserVO ) );
        }

        return list;
    }
}
