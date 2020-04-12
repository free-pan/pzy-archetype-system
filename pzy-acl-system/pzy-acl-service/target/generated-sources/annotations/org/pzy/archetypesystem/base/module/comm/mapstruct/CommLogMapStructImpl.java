package org.pzy.archetypesystem.base.module.comm.mapstruct;

import org.pzy.archetypesystem.base.module.comm.dto.CommLogAddDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommLogEditDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommLogSearchDTO;
import org.pzy.archetypesystem.base.module.comm.entity.CommLog;
import org.pzy.archetypesystem.base.module.comm.vo.CommLogVO;
import org.pzy.opensource.comm.mapstruct.StringDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-12T09:45:26+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class CommLogMapStructImpl implements CommLogMapStruct {

    @Autowired
    private StringDataMapper stringDataMapper;

    @Override
    public CommLog addSourceToEntity(CommLogAddDTO source) {
        if ( source == null ) {
            return null;
        }

        CommLog commLog = new CommLog();

        commLog.setClientIp( stringDataMapper.stringMapper( source.getClientIp() ) );
        commLog.setType( source.getType() );
        commLog.setCreatorId( source.getCreatorId() );
        commLog.setCreatorName( stringDataMapper.stringMapper( source.getCreatorName() ) );
        commLog.setFunCode( stringDataMapper.stringMapper( source.getFunCode() ) );
        commLog.setFunName( stringDataMapper.stringMapper( source.getFunName() ) );
        commLog.setCreateTime( source.getCreateTime() );
        commLog.setOptResult( source.getOptResult() );
        commLog.setExpInfo( stringDataMapper.stringMapper( source.getExpInfo() ) );
        commLog.setUseTime( source.getUseTime() );
        commLog.setMethodFullName( stringDataMapper.stringMapper( source.getMethodFullName() ) );
        commLog.setInputParamJson( stringDataMapper.stringMapper( source.getInputParamJson() ) );

        return commLog;
    }

    @Override
    public List<CommLog> addSourceToEntity(Collection<CommLogAddDTO> sources) {
        if ( sources == null ) {
            return null;
        }

        List<CommLog> list = new ArrayList<CommLog>( sources.size() );
        for ( CommLogAddDTO commLogAddDTO : sources ) {
            list.add( addSourceToEntity( commLogAddDTO ) );
        }

        return list;
    }

    @Override
    public CommLog editSourceToEntity(CommLogEditDTO source) {
        if ( source == null ) {
            return null;
        }

        CommLog commLog = new CommLog();

        commLog.setId( source.getId() );
        commLog.setType( source.getType() );
        commLog.setCreatorId( source.getCreatorId() );
        commLog.setCreatorName( stringDataMapper.stringMapper( source.getCreatorName() ) );
        commLog.setFunCode( stringDataMapper.stringMapper( source.getFunCode() ) );
        commLog.setFunName( stringDataMapper.stringMapper( source.getFunName() ) );
        commLog.setCreateTime( source.getCreateTime() );
        commLog.setOptResult( source.getOptResult() );
        commLog.setExpInfo( stringDataMapper.stringMapper( source.getExpInfo() ) );
        if ( source.getUseTime() != null ) {
            commLog.setUseTime( source.getUseTime().longValue() );
        }

        return commLog;
    }

    @Override
    public List<CommLog> editSourceToEntity(Collection<CommLogEditDTO> sources) {
        if ( sources == null ) {
            return null;
        }

        List<CommLog> list = new ArrayList<CommLog>( sources.size() );
        for ( CommLogEditDTO commLogEditDTO : sources ) {
            list.add( editSourceToEntity( commLogEditDTO ) );
        }

        return list;
    }

    @Override
    public CommLogVO entityToDTO(CommLog source) {
        if ( source == null ) {
            return null;
        }

        CommLogVO commLogVO = new CommLogVO();

        commLogVO.setType( source.getType() );
        commLogVO.setCreatorId( source.getCreatorId() );
        commLogVO.setCreatorName( stringDataMapper.stringMapper( source.getCreatorName() ) );
        commLogVO.setFunCode( stringDataMapper.stringMapper( source.getFunCode() ) );
        commLogVO.setFunName( stringDataMapper.stringMapper( source.getFunName() ) );
        commLogVO.setCreateTime( source.getCreateTime() );
        commLogVO.setOptResult( source.getOptResult() );
        commLogVO.setExpInfo( stringDataMapper.stringMapper( source.getExpInfo() ) );
        commLogVO.setUseTime( source.getUseTime() );
        commLogVO.setMethodFullName( stringDataMapper.stringMapper( source.getMethodFullName() ) );
        commLogVO.setInputParamJson( stringDataMapper.stringMapper( source.getInputParamJson() ) );
        commLogVO.setClientIp( stringDataMapper.stringMapper( source.getClientIp() ) );
        commLogVO.setId( source.getId() );

        return commLogVO;
    }

    @Override
    public List<CommLogVO> entityToDTO(Collection<CommLog> sources) {
        if ( sources == null ) {
            return null;
        }

        List<CommLogVO> list = new ArrayList<CommLogVO>( sources.size() );
        for ( CommLog commLog : sources ) {
            list.add( entityToDTO( commLog ) );
        }

        return list;
    }

    @Override
    public CommLogSearchDTO searchDtoToSearchDTO(CommLogSearchDTO searchDTO) {
        if ( searchDTO == null ) {
            return null;
        }

        CommLogSearchDTO commLogSearchDTO = new CommLogSearchDTO();

        commLogSearchDTO.setBeginDate( searchDTO.getBeginDate() );
        commLogSearchDTO.setEndDate( searchDTO.getEndDate() );
        commLogSearchDTO.setTargetFieldIsDatetime( searchDTO.getTargetFieldIsDatetime() );
        commLogSearchDTO.setKw( stringDataMapper.stringMapper( searchDTO.getKw() ) );
        commLogSearchDTO.setPg( searchDTO.getPg() );
        commLogSearchDTO.setOperatorId( searchDTO.getOperatorId() );
        commLogSearchDTO.setUseTime( searchDTO.getUseTime() );
        commLogSearchDTO.setType( searchDTO.getType() );
        commLogSearchDTO.setFunCode( searchDTO.getFunCode() );
        commLogSearchDTO.setOptResult( searchDTO.getOptResult() );

        return commLogSearchDTO;
    }
}
