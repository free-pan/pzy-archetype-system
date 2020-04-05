package org.pzy.archetypesystem.acl.sysrole.mapstruct;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.pzy.archetypesystem.acl.sysrole.dto.SysRoleAddDTO;
import org.pzy.archetypesystem.acl.sysrole.dto.SysRoleEditDTO;
import org.pzy.archetypesystem.acl.sysrole.dto.SysRoleSearchDTO;
import org.pzy.archetypesystem.acl.sysrole.entity.SysRole;
import org.pzy.archetypesystem.acl.sysrole.vo.SysRoleVO;
import org.pzy.opensource.comm.mapstruct.StringDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-04T23:49:35+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class SysRoleMapStructImpl implements SysRoleMapStruct {

    @Autowired
    private StringDataMapper stringDataMapper;

    @Override
    public SysRole addSourceToEntity(SysRoleAddDTO source) {
        if ( source == null ) {
            return null;
        }

        SysRole sysRole = new SysRole();

        sysRole.setName( stringDataMapper.stringMapper( source.getName() ) );
        sysRole.setCode( stringDataMapper.stringMapper( source.getCode() ) );
        sysRole.setRemark( stringDataMapper.stringMapper( source.getRemark() ) );

        return sysRole;
    }

    @Override
    public List<SysRole> addSourceToEntity(Collection<SysRoleAddDTO> sources) {
        if ( sources == null ) {
            return null;
        }

        List<SysRole> list = new ArrayList<SysRole>( sources.size() );
        for ( SysRoleAddDTO sysRoleAddDTO : sources ) {
            list.add( addSourceToEntity( sysRoleAddDTO ) );
        }

        return list;
    }

    @Override
    public SysRole editSourceToEntity(SysRoleEditDTO source) {
        if ( source == null ) {
            return null;
        }

        SysRole sysRole = new SysRole();

        sysRole.setId( source.getId() );
        sysRole.setName( stringDataMapper.stringMapper( source.getName() ) );
        sysRole.setCode( stringDataMapper.stringMapper( source.getCode() ) );
        sysRole.setRemark( stringDataMapper.stringMapper( source.getRemark() ) );

        return sysRole;
    }

    @Override
    public List<SysRole> editSourceToEntity(Collection<SysRoleEditDTO> sources) {
        if ( sources == null ) {
            return null;
        }

        List<SysRole> list = new ArrayList<SysRole>( sources.size() );
        for ( SysRoleEditDTO sysRoleEditDTO : sources ) {
            list.add( editSourceToEntity( sysRoleEditDTO ) );
        }

        return list;
    }

    @Override
    public SysRoleVO entityToDTO(SysRole source) {
        if ( source == null ) {
            return null;
        }

        SysRoleVO sysRoleVO = new SysRoleVO();

        sysRoleVO.setName( stringDataMapper.stringMapper( source.getName() ) );
        sysRoleVO.setCode( stringDataMapper.stringMapper( source.getCode() ) );
        sysRoleVO.setRemark( stringDataMapper.stringMapper( source.getRemark() ) );
        sysRoleVO.setId( source.getId() );
        if ( source.getDisabled() != null ) {
            sysRoleVO.setDisabled( source.getDisabled().intValue() );
        }
        if ( source.getDisabledTime() != null ) {
            sysRoleVO.setDisabledTime( LocalDateTime.ofInstant( source.getDisabledTime().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        if ( source.getCreateTime() != null ) {
            sysRoleVO.setCreateTime( LocalDateTime.ofInstant( source.getCreateTime().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        if ( source.getEditTime() != null ) {
            sysRoleVO.setEditTime( LocalDateTime.ofInstant( source.getEditTime().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        sysRoleVO.setCreatorId( source.getCreatorId() );
        sysRoleVO.setEditorId( source.getEditorId() );
        sysRoleVO.setCreatorName( stringDataMapper.stringMapper( source.getCreatorName() ) );
        sysRoleVO.setEditorName( stringDataMapper.stringMapper( source.getEditorName() ) );
        sysRoleVO.setDisabledOptId( source.getDisabledOptId() );
        sysRoleVO.setDisabledOptName( stringDataMapper.stringMapper( source.getDisabledOptName() ) );

        return sysRoleVO;
    }

    @Override
    public List<SysRoleVO> entityToDTO(Collection<SysRole> sources) {
        if ( sources == null ) {
            return null;
        }

        List<SysRoleVO> list = new ArrayList<SysRoleVO>( sources.size() );
        for ( SysRole sysRole : sources ) {
            list.add( entityToDTO( sysRole ) );
        }

        return list;
    }

    @Override
    public SysRoleSearchDTO searchDtoToSearchDTO(SysRoleSearchDTO searchDTO) {
        if ( searchDTO == null ) {
            return null;
        }

        SysRoleSearchDTO sysRoleSearchDTO = new SysRoleSearchDTO();

        sysRoleSearchDTO.setBeginDate( searchDTO.getBeginDate() );
        sysRoleSearchDTO.setEndDate( searchDTO.getEndDate() );
        sysRoleSearchDTO.setKw( stringDataMapper.stringMapper( searchDTO.getKw() ) );
        sysRoleSearchDTO.setPg( searchDTO.getPg() );

        return sysRoleSearchDTO;
    }
}
