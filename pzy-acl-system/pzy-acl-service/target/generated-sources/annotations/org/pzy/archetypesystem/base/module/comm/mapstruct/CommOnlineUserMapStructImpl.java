package org.pzy.archetypesystem.base.module.comm.mapstruct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.pzy.archetypesystem.base.module.comm.dto.CommOnlineUserAddDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommOnlineUserEditDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommOnlineUserSearchDTO;
import org.pzy.archetypesystem.base.module.comm.entity.CommOnlineUser;
import org.pzy.archetypesystem.base.module.comm.vo.CommOnlineUserVO;
import org.pzy.opensource.comm.mapstruct.StringDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-12T17:45:35+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class CommOnlineUserMapStructImpl implements CommOnlineUserMapStruct {

    @Autowired
    private StringDataMapper stringDataMapper;

    @Override
    public CommOnlineUser addSourceToEntity(CommOnlineUserAddDTO source) {
        if ( source == null ) {
            return null;
        }

        CommOnlineUser commOnlineUser = new CommOnlineUser();

        commOnlineUser.setSessionId( stringDataMapper.stringMapper( source.getSessionId() ) );
        commOnlineUser.setUserId( source.getUserId() );
        commOnlineUser.setLoginTime( source.getLoginTime() );
        commOnlineUser.setEmail( stringDataMapper.stringMapper( source.getEmail() ) );
        commOnlineUser.setName( stringDataMapper.stringMapper( source.getName() ) );
        commOnlineUser.setClientIp( stringDataMapper.stringMapper( source.getClientIp() ) );
        commOnlineUser.setClientBrowser( stringDataMapper.stringMapper( source.getClientBrowser() ) );
        commOnlineUser.setClientOs( stringDataMapper.stringMapper( source.getClientOs() ) );

        return commOnlineUser;
    }

    @Override
    public List<CommOnlineUser> addSourceToEntity(Collection<CommOnlineUserAddDTO> sources) {
        if ( sources == null ) {
            return null;
        }

        List<CommOnlineUser> list = new ArrayList<CommOnlineUser>( sources.size() );
        for ( CommOnlineUserAddDTO commOnlineUserAddDTO : sources ) {
            list.add( addSourceToEntity( commOnlineUserAddDTO ) );
        }

        return list;
    }

    @Override
    public CommOnlineUser editSourceToEntity(CommOnlineUserEditDTO source) {
        if ( source == null ) {
            return null;
        }

        CommOnlineUser commOnlineUser = new CommOnlineUser();

        commOnlineUser.setId( source.getId() );
        commOnlineUser.setSessionId( stringDataMapper.stringMapper( source.getSessionId() ) );
        commOnlineUser.setUserId( source.getUserId() );
        commOnlineUser.setLoginTime( source.getLoginTime() );
        commOnlineUser.setEmail( stringDataMapper.stringMapper( source.getEmail() ) );
        commOnlineUser.setName( stringDataMapper.stringMapper( source.getName() ) );

        return commOnlineUser;
    }

    @Override
    public List<CommOnlineUser> editSourceToEntity(Collection<CommOnlineUserEditDTO> sources) {
        if ( sources == null ) {
            return null;
        }

        List<CommOnlineUser> list = new ArrayList<CommOnlineUser>( sources.size() );
        for ( CommOnlineUserEditDTO commOnlineUserEditDTO : sources ) {
            list.add( editSourceToEntity( commOnlineUserEditDTO ) );
        }

        return list;
    }

    @Override
    public CommOnlineUserVO entityToDTO(CommOnlineUser source) {
        if ( source == null ) {
            return null;
        }

        CommOnlineUserVO commOnlineUserVO = new CommOnlineUserVO();

        commOnlineUserVO.setSessionId( stringDataMapper.stringMapper( source.getSessionId() ) );
        commOnlineUserVO.setUserId( source.getUserId() );
        commOnlineUserVO.setLoginTime( source.getLoginTime() );
        commOnlineUserVO.setEmail( stringDataMapper.stringMapper( source.getEmail() ) );
        commOnlineUserVO.setName( stringDataMapper.stringMapper( source.getName() ) );
        commOnlineUserVO.setClientIp( stringDataMapper.stringMapper( source.getClientIp() ) );
        commOnlineUserVO.setClientBrowser( stringDataMapper.stringMapper( source.getClientBrowser() ) );
        commOnlineUserVO.setClientOs( stringDataMapper.stringMapper( source.getClientOs() ) );
        commOnlineUserVO.setId( source.getId() );

        return commOnlineUserVO;
    }

    @Override
    public List<CommOnlineUserVO> entityToDTO(Collection<CommOnlineUser> sources) {
        if ( sources == null ) {
            return null;
        }

        List<CommOnlineUserVO> list = new ArrayList<CommOnlineUserVO>( sources.size() );
        for ( CommOnlineUser commOnlineUser : sources ) {
            list.add( entityToDTO( commOnlineUser ) );
        }

        return list;
    }

    @Override
    public CommOnlineUserSearchDTO searchDtoToSearchDTO(CommOnlineUserSearchDTO searchDTO) {
        if ( searchDTO == null ) {
            return null;
        }

        CommOnlineUserSearchDTO commOnlineUserSearchDTO = new CommOnlineUserSearchDTO();

        commOnlineUserSearchDTO.setBeginDate( searchDTO.getBeginDate() );
        commOnlineUserSearchDTO.setEndDate( searchDTO.getEndDate() );
        commOnlineUserSearchDTO.setTargetFieldIsDatetime( searchDTO.getTargetFieldIsDatetime() );
        commOnlineUserSearchDTO.setKw( stringDataMapper.stringMapper( searchDTO.getKw() ) );
        commOnlineUserSearchDTO.setPg( searchDTO.getPg() );

        return commOnlineUserSearchDTO;
    }
}
