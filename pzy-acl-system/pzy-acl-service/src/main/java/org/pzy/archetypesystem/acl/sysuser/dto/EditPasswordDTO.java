package org.pzy.archetypesystem.acl.sysuser.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用于根据id修改密码
 *
 * @author pan
 * @date 2020/3/26
 */
@Data
@Accessors(chain = true)
public class EditPasswordDTO extends AbstractChangePwdDTO {

    @ApiModelProperty(hidden = true)
    @NotNull
    private Long id;

    /**
     * 原始密码
     */
    @ApiModelProperty(value = "原始密码", required = true)
    @NotBlank(message = "原始密码不能为空!")
    private String oldPwd;

}
