package org.pzy.archetypesystem.acl.sysuser.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * SysUserAddDTO: 用于新增操作, client --> server 数据传输类
 *
 * @author pan
 * @since 2020-03-24 16:44:51
 */
@SuppressWarnings("serial")
@Data
public abstract class AbstractSysUserAddEditDTO implements Serializable {

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "请输入姓名!")
    @Size(max = 200, message = "姓名不能超过{max}字符!")
    private String name;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "请输入邮箱!")
    @Size(max = 150, message = "姓名不能超过{max}字符!")
    @Email(message = "邮箱格式错误!")
    private String email;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    @Size(max = 200, message = "姓名不能超过{max}字符!")
    private String remark;

}