package org.pzy.archetypesystem.acl.sysuser.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用于通过邮箱重置密码
 *
 * @author pan
 * @date 2020/3/26
 */
@Data
@Accessors(chain = true)
public class ForgetPasswordDTO extends AbstractChangePwdDTO {

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码", required = true)
    private String code;
}
