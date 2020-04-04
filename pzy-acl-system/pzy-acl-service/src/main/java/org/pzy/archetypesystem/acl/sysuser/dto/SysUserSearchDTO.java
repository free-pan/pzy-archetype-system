/**
 * Copyright (C): 恒大集团版权所有 Evergrande Group
 */
package org.pzy.archetypesystem.acl.sysuser.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户查询条件
 *
 * @author pan
 * @date 2020/4/4 15:35
 */
public class SysUserSearchDTO implements Serializable {

    @ApiModelProperty(value = "查询关键词")
    private String kw;

    private Date createDateBegin;

    private Date createDateEnd;
}
