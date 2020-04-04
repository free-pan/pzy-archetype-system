package org.pzy.archetypesystem.acl.sysuser.mapstruct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.pzy.archetypesystem.acl.sysuser.dto.SysUserAddDTO;
import org.pzy.archetypesystem.acl.sysuser.dto.SysUserEditDTO;
import org.pzy.archetypesystem.acl.sysuser.dto.SysUserSearchDTO;
import org.pzy.archetypesystem.acl.sysuser.entity.SysUser;
import org.pzy.archetypesystem.acl.sysuser.vo.SysUserVO;
import org.pzy.opensource.comm.mapstruct.StringDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-04T16:46:49+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class SysUserMapStructImpl implements SysUserMapStruct {

    @Autowired
    private StringDataMapper stringDataMapper;

    @Override
    public SysUser addSourceToEntity(SysUserAddDTO source) {
        if ( source == null ) {
            return null;
        }

        SysUser sysUser = new SysUser();

        sysUser.setName( stringDataMapper.stringMapper( source.getName() ) );
        sysUser.setEmail( stringDataMapper.stringMapper( source.getEmail() ) );

        return sysUser;
    }

    @Override
    public List<SysUser> addSourceToEntity(Collection<SysUserAddDTO> sources) {
        if ( sources == null ) {
            return null;
        }

        List<SysUser> list = new ArrayList<SysUser>( sources.size() );
        for ( SysUserAddDTO sysUserAddDTO : sources ) {
            list.add( addSourceToEntity( sysUserAddDTO ) );
        }

        return list;
    }

    @Override
    public SysUser editSourceToEntity(SysUserEditDTO source) {
        if ( source == null ) {
            return null;
        }

        SysUser sysUser = new SysUser();

        sysUser.setId( source.getId() );
        sysUser.setName( stringDataMapper.stringMapper( source.getName() ) );
        sysUser.setEmail( stringDataMapper.stringMapper( source.getEmail() ) );

        return sysUser;
    }

    @Override
    public List<SysUser> editSourceToEntity(Collection<SysUserEditDTO> sources) {
        if ( sources == null ) {
            return null;
        }

        List<SysUser> list = new ArrayList<SysUser>( sources.size() );
        for ( SysUserEditDTO sysUserEditDTO : sources ) {
            list.add( editSourceToEntity( sysUserEditDTO ) );
        }

        return list;
    }

    @Override
    public SysUserVO entityToDTO(SysUser source) {
        if ( source == null ) {
            return null;
        }

        SysUserVO sysUserVO = new SysUserVO();

        sysUserVO.setId( source.getId() );
        sysUserVO.setName( stringDataMapper.stringMapper( source.getName() ) );
        sysUserVO.setEmail( stringDataMapper.stringMapper( source.getEmail() ) );
        sysUserVO.setCreateTime( source.getCreateTime() );
        sysUserVO.setCreatorId( source.getCreatorId() );
        sysUserVO.setCreatorName( stringDataMapper.stringMapper( source.getCreatorName() ) );
        sysUserVO.setEditTime( source.getEditTime() );
        sysUserVO.setEditorId( source.getEditorId() );
        sysUserVO.setEditorName( stringDataMapper.stringMapper( source.getEditorName() ) );

        return sysUserVO;
    }

    @Override
    public List<SysUserVO> entityToDTO(Collection<SysUser> sources) {
        if ( sources == null ) {
            return null;
        }

        List<SysUserVO> list = new ArrayList<SysUserVO>( sources.size() );
        for ( SysUser sysUser : sources ) {
            list.add( entityToDTO( sysUser ) );
        }

        return list;
    }

    @Override
    public SysUserSearchDTO searchDtoToSearchDTO(SysUserSearchDTO searchDTO) {
        if ( searchDTO == null ) {
            return null;
        }

        SysUserSearchDTO sysUserSearchDTO = new SysUserSearchDTO();

        sysUserSearchDTO.setBeginDate( searchDTO.getBeginDate() );
        sysUserSearchDTO.setEndDate( searchDTO.getEndDate() );
        sysUserSearchDTO.setKw( stringDataMapper.stringMapper( searchDTO.getKw() ) );
        sysUserSearchDTO.setPage( searchDTO.getPage() );

        return sysUserSearchDTO;
    }
}
