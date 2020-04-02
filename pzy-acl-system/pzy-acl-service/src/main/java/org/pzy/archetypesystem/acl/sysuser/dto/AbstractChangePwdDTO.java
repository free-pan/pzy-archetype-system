package org.pzy.archetypesystem.acl.sysuser.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 修改密码的公共属性
 *
 * @author pan
 * @date 2020/3/26
 */
@Data
public abstract class AbstractChangePwdDTO implements Serializable {

    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码", required = true)
    @Length(min = 6, max = 8, message = "请输入{min}--{max}位密码!")
    private String newPwd;

    /**
     * 确认新密码
     */
    @ApiModelProperty(value = "确认新密码", required = true)
    @Length(min = 6, max = 8, message = "请输入{min}--{max}位密码!")
    private String newPwdConfirm;
}
