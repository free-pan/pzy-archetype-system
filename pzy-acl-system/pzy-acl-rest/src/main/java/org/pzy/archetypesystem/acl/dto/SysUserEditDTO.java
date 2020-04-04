package org.pzy.archetypesystem.acl.dto;

import org.pzy.opensource.mybatisplus.model.entity.LogicDelBaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.*;

/**
 * sys_user 表DTO类: 用于编辑操作时接收客户端参数
 *
 * @author pan
 * @since 2020-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class SysUserEditDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "主键")
    private Long id;

}
