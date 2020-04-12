package org.pzy.archetypesystem.base.module.comm.mapstruct;

import org.pzy.archetypesystem.base.module.comm.dto.CommDictionaryItemAddDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommDictionaryItemEditDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommDictionaryItemSearchDTO;
import org.pzy.archetypesystem.base.module.comm.entity.CommDictionaryItem;
import org.pzy.archetypesystem.base.module.comm.vo.CommDictionaryItemVO;
import org.pzy.opensource.comm.mapstruct.StringDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-12T17:45:35+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class CommDictionaryItemMapStructImpl implements CommDictionaryItemMapStruct {

    @Autowired
    private StringDataMapper stringDataMapper;

    @Override
    public CommDictionaryItem addSourceToEntity(CommDictionaryItemAddDTO source) {
        if ( source == null ) {
            return null;
        }

        CommDictionaryItem commDictionaryItem = new CommDictionaryItem();

        commDictionaryItem.setDictionaryId( source.getDictionaryId() );
        commDictionaryItem.setCode( stringDataMapper.stringMapper( source.getCode() ) );
        commDictionaryItem.setName( stringDataMapper.stringMapper( source.getName() ) );
        commDictionaryItem.setViewPriority( source.getViewPriority() );

        return commDictionaryItem;
    }

    @Override
    public List<CommDictionaryItem> addSourceToEntity(Collection<CommDictionaryItemAddDTO> sources) {
        if ( sources == null ) {
            return null;
        }

        List<CommDictionaryItem> list = new ArrayList<CommDictionaryItem>( sources.size() );
        for ( CommDictionaryItemAddDTO commDictionaryItemAddDTO : sources ) {
            list.add( addSourceToEntity( commDictionaryItemAddDTO ) );
        }

        return list;
    }

    @Override
    public CommDictionaryItem editSourceToEntity(CommDictionaryItemEditDTO source) {
        if ( source == null ) {
            return null;
        }

        CommDictionaryItem commDictionaryItem = new CommDictionaryItem();

        commDictionaryItem.setId( source.getId() );
        commDictionaryItem.setDictionaryId( source.getDictionaryId() );
        commDictionaryItem.setCode( stringDataMapper.stringMapper( source.getCode() ) );
        commDictionaryItem.setName( stringDataMapper.stringMapper( source.getName() ) );
        commDictionaryItem.setViewPriority( source.getViewPriority() );

        return commDictionaryItem;
    }

    @Override
    public List<CommDictionaryItem> editSourceToEntity(Collection<CommDictionaryItemEditDTO> sources) {
        if ( sources == null ) {
            return null;
        }

        List<CommDictionaryItem> list = new ArrayList<CommDictionaryItem>( sources.size() );
        for ( CommDictionaryItemEditDTO commDictionaryItemEditDTO : sources ) {
            list.add( editSourceToEntity( commDictionaryItemEditDTO ) );
        }

        return list;
    }

    @Override
    public CommDictionaryItemVO entityToDTO(CommDictionaryItem source) {
        if ( source == null ) {
            return null;
        }

        CommDictionaryItemVO commDictionaryItemVO = new CommDictionaryItemVO();

        commDictionaryItemVO.setDictionaryId( source.getDictionaryId() );
        commDictionaryItemVO.setCode( stringDataMapper.stringMapper( source.getCode() ) );
        commDictionaryItemVO.setName( stringDataMapper.stringMapper( source.getName() ) );
        commDictionaryItemVO.setViewPriority( source.getViewPriority() );
        commDictionaryItemVO.setId( source.getId() );
        if ( source.getDisabled() != null ) {
            commDictionaryItemVO.setDisabled( source.getDisabled().intValue() );
        }
        commDictionaryItemVO.setDisabledTime( source.getDisabledTime() );
        commDictionaryItemVO.setCreateTime( source.getCreateTime() );
        commDictionaryItemVO.setEditTime( source.getEditTime() );
        commDictionaryItemVO.setCreatorId( source.getCreatorId() );
        commDictionaryItemVO.setEditorId( source.getEditorId() );
        commDictionaryItemVO.setCreatorName( stringDataMapper.stringMapper( source.getCreatorName() ) );
        commDictionaryItemVO.setEditorName( stringDataMapper.stringMapper( source.getEditorName() ) );
        commDictionaryItemVO.setDisabledOptId( source.getDisabledOptId() );
        commDictionaryItemVO.setDisabledOptName( stringDataMapper.stringMapper( source.getDisabledOptName() ) );

        return commDictionaryItemVO;
    }

    @Override
    public List<CommDictionaryItemVO> entityToDTO(Collection<CommDictionaryItem> sources) {
        if ( sources == null ) {
            return null;
        }

        List<CommDictionaryItemVO> list = new ArrayList<CommDictionaryItemVO>( sources.size() );
        for ( CommDictionaryItem commDictionaryItem : sources ) {
            list.add( entityToDTO( commDictionaryItem ) );
        }

        return list;
    }

    @Override
    public CommDictionaryItemSearchDTO searchDtoToSearchDTO(CommDictionaryItemSearchDTO searchDTO) {
        if ( searchDTO == null ) {
            return null;
        }

        CommDictionaryItemSearchDTO commDictionaryItemSearchDTO = new CommDictionaryItemSearchDTO();

        commDictionaryItemSearchDTO.setKw( stringDataMapper.stringMapper( searchDTO.getKw() ) );
        commDictionaryItemSearchDTO.setDictionaryId( searchDTO.getDictionaryId() );
        commDictionaryItemSearchDTO.setStatus( searchDTO.getStatus() );

        return commDictionaryItemSearchDTO;
    }
}
