package org.pzy.archetypesystem.base.module.comm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    @ApiModelProperty(value = "客户端ip")
    private String clientIp;
    @ApiModelProperty(value = "客户端浏览器信息")
    private String clientBrowser;
    @ApiModelProperty(value = "客户端操作系统信息")
    private String clientOs;


    @ApiModelProperty(value = "主键")
    private Long id;

}
