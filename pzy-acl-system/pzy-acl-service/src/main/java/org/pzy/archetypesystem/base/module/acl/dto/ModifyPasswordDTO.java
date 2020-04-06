package org.pzy.archetypesystem.base.module.acl.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * ModifyPasswordDTO
 *
 * @author pan
 * @date 2020/4/6 13:16
 */
@Data
public class ModifyPasswordDTO extends AbstractModifyPasswordDTO {

    @ApiModelProperty(hidden = true, value = "用户id,由后台自动填充")
    private Long id;

    @ApiModelProperty(value = "原始密码", required = true)
    @NotBlank(message = "请输入原始密码!")
    @Length(min = 6, max = 10, message = "密码错误!")
    private String oldPwd;
}
