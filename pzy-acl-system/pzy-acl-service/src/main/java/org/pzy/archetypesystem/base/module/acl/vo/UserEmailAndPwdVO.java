/**
 * Copyright (C): 恒大集团版权所有 Evergrande Group
 */
package org.pzy.archetypesystem.base.module.acl.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * UserEmailAndPwdDTO
 *
 * @author pan
 * @date 2020/4/6 14:07
 */
@Data
@Accessors(chain = true)
public class UserEmailAndPwdVO implements Serializable {

    @ApiModelProperty(value = "邮箱", required = true)
    private String email;

    @ApiModelProperty(value = "密码", required = true)
    private String pwd;
}
