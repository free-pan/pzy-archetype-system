package org.pzy.archetypesystem.base.module.comm.vo;

import java.time.LocalDateTime;
import org.pzy.opensource.mybatisplus.model.entity.SimpleBaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import java.io.Serializable;
import java.time.*;

/**
 * comm_online_user 表VO类: 用于服务端返回客户端数据
 *
 * @author pan
 * @since 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class CommOnlineUserVO implements Serializable {

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


    @ApiModelProperty(value = "主键")
    private Long id;

}
