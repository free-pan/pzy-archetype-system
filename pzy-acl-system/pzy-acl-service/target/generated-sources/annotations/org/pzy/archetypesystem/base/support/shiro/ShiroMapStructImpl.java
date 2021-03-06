package org.pzy.archetypesystem.base.support.shiro;

import javax.annotation.Generated;
import org.pzy.opensource.comm.mapstruct.StringDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-05T15:07:17+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class ShiroMapStructImpl implements ShiroMapStruct {

    @Autowired
    private StringDataMapper stringDataMapper;

    @Override
    public ShiroLoginToken loginParamsDTO2ShiroLoginToken(LoginParamsDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ShiroLoginToken shiroLoginToken = new ShiroLoginToken();

        shiroLoginToken.setEmail( stringDataMapper.stringMapper( dto.getEmail() ) );
        shiroLoginToken.setPassword( stringDataMapper.stringMapper( dto.getPassword() ) );
        shiroLoginToken.setRememberMe( dto.isRememberMe() );

        return shiroLoginToken;
    }
}
