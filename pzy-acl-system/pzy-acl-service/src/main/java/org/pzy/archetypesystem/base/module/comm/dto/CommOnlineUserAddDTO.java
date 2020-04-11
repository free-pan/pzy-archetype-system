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

    private static final long serialVersionUID = 1L;

    /**
     * 自定义配置:单个用户允许的最大session数量
     */
    private Integer singleUserMaxSession;
    /**
     * 自定义配置:当单个用户的session数量超过singleUserMaxSession时,是踢出后登录的用户还是先登录的用户. true表示系统后登录的用户,false表示踢出先登录的用户
     */
    private Boolean kickoutAfter;

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

}
