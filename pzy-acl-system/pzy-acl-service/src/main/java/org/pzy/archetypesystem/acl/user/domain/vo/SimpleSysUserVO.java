package org.pzy.archetypesystem.acl.user.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* SysUserVO: 查询结果, server --> client 数据传输类
*
* @author pan
* @since 2020-03-24 16:44:51
*/
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
public class SimpleSysUserVO implements Serializable {

    @ApiModelProperty
    private Long id;

    /**
    * 姓名
    */
    @ApiModelProperty(value="姓名")
    private String name;

    /**
    * 邮箱
    */
    @ApiModelProperty(value="邮箱")
    private String email;

}