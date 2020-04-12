package org.pzy.archetypesystem.base.module.comm.mapstruct;

import org.pzy.archetypesystem.base.module.comm.dto.CommDictionaryAddDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommDictionaryEditDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommDictionarySearchDTO;
import org.pzy.archetypesystem.base.module.comm.entity.CommDictionary;
import org.pzy.archetypesystem.base.module.comm.vo.CommDictionaryVO;
import org.pzy.opensource.comm.mapstruct.StringDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-11T22:42:12+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class CommDictionaryMapStructImpl implements CommDictionaryMapStruct {

    @Autowired
    private StringDataMapper stringDataMapper;

    @Override
    public CommDictionary addSourceToEntity(CommDictionaryAddDTO source) {
        if ( source == null ) {
            return null;
        }

        CommDictionary commDictionary = new CommDictionary();

        commDictionary.setCode( stringDataMapper.stringMapper( source.getCode() ) );
        commDictionary.setName( stringDataMapper.stringMapper( source.getName() ) );
        commDictionary.setRemark( stringDataMapper.stringMapper( source.getRemark() ) );

        return commDictionary;
    }

    @Override
    public List<CommDictionary> addSourceToEntity(Collection<CommDictionaryAddDTO> sources) {
        if ( sources == null ) {
            return null;
        }

        List<CommDictionary> list = new ArrayList<CommDictionary>( sources.size() );
        for ( CommDictionaryAddDTO commDictionaryAddDTO : sources ) {
            list.add( addSourceToEntity( commDictionaryAddDTO ) );
        }

        return list;
    }

    @Override
    public CommDictionary editSourceToEntity(CommDictionaryEditDTO source) {
        if ( source == null ) {
            return null;
        }

        CommDictionary commDictionary = new CommDictionary();

        commDictionary.setId( source.getId() );
        commDictionary.setCode( stringDataMapper.stringMapper( source.getCode() ) );
        commDictionary.setName( stringDataMapper.stringMapper( source.getName() ) );
        commDictionary.setRemark( stringDataMapper.stringMapper( source.getRemark() ) );

        return commDictionary;
    }

    @Override
    public List<CommDictionary> editSourceToEntity(Collection<CommDictionaryEditDTO> sources) {
        if ( sources == null ) {
            return null;
        }

        List<CommDictionary> list = new ArrayList<CommDictionary>( sources.size() );
        for ( CommDictionaryEditDTO commDictionaryEditDTO : sources ) {
            list.add( editSourceToEntity( commDictionaryEditDTO ) );
        }

        return list;
    }

    @Override
    public CommDictionaryVO entityToDTO(CommDictionary source) {
        if ( source == null ) {
            return null;
        }

        CommDictionaryVO commDictionaryVO = new CommDictionaryVO();

        commDictionaryVO.setCode( stringDataMapper.stringMapper( source.getCode() ) );
        commDictionaryVO.setName( stringDataMapper.stringMapper( source.getName() ) );
        commDictionaryVO.setRemark( stringDataMapper.stringMapper( source.getRemark() ) );
        commDictionaryVO.setId( source.getId() );
        if ( source.getDisabled() != null ) {
            commDictionaryVO.setDisabled( source.getDisabled().intValue() );
        }
        commDictionaryVO.setDisabledTime( source.getDisabledTime() );
        commDictionaryVO.setCreateTime( source.getCreateTime() );
        commDictionaryVO.setEditTime( source.getEditTime() );
        commDictionaryVO.setCreatorId( source.getCreatorId() );
        commDictionaryVO.setEditorId( source.getEditorId() );
        commDictionaryVO.setCreatorName( stringDataMapper.stringMapper( source.getCreatorName() ) );
        commDictionaryVO.setEditorName( stringDataMapper.stringMapper( source.getEditorName() ) );
        commDictionaryVO.setDisabledOptId( source.getDisabledOptId() );
        commDictionaryVO.setDisabledOptName( stringDataMapper.stringMapper( source.getDisabledOptName() ) );

        return commDictionaryVO;
    }

    @Override
    public List<CommDictionaryVO> entityToDTO(Collection<CommDictionary> sources) {
        if ( sources == null ) {
            return null;
        }

        List<CommDictionaryVO> list = new ArrayList<CommDictionaryVO>( sources.size() );
        for ( CommDictionary commDictionary : sources ) {
            list.add( entityToDTO( commDictionary ) );
        }

        return list;
    }

    @Override
    public CommDictionarySearchDTO searchDtoToSearchDTO(CommDictionarySearchDTO searchDTO) {
        if ( searchDTO == null ) {
            return null;
        }

        CommDictionarySearchDTO commDictionarySearchDTO = new CommDictionarySearchDTO();

        commDictionarySearchDTO.setBeginDate( searchDTO.getBeginDate() );
        commDictionarySearchDTO.setEndDate( searchDTO.getEndDate() );
        commDictionarySearchDTO.setTargetFieldIsDatetime( searchDTO.getTargetFieldIsDatetime() );
        commDictionarySearchDTO.setKw( stringDataMapper.stringMapper( searchDTO.getKw() ) );
        commDictionarySearchDTO.setPg( searchDTO.getPg() );

        return commDictionarySearchDTO;
    }
}
