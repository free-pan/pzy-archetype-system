package org.pzy.archetypesystem.base.module.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * comm_online_user 表DTO类: 用于新增操作时接收客户端参数
 *
 * @author pan
 * @since 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class CommOnlineUserAddDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "会话id")
    private String sessionId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "登录时间")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户真实姓名")
    private String name;

}
