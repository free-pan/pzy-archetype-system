/**
 * Copyright (C): 恒大集团版权所有 Evergrande Group
 */
package org.pzy.archetypesystem.base.module.acl.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * AbstractModifyPasswordDTO
 *
 * @author pan
 * @date 2020/4/6 13:16
 */
@Data
public abstract class AbstractModifyPasswordDTO implements Serializable {


    @ApiModelProperty(value = "新密码", required = true)
    @NotBlank(message = "请输入新密码!")
    @Length(min = 6, max = 10, message = "请输入{min}-{max}位的新密码!")
    private String newPwd;

    @ApiModelProperty(value = "确认密码", required = true)
    @NotBlank(message = "请输入确认密码!")
    @Length(min = 6, max = 10, message = "请输入{min}-{max}位的确认密码!")
    private String confirmPwd;
}
