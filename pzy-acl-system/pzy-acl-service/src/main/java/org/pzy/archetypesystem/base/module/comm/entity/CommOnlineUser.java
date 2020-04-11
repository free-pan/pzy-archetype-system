package org.pzy.archetypesystem.base.module.comm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.pzy.opensource.mybatisplus.model.entity.SimpleBaseEntity;

import java.time.LocalDateTime;

/**
 * comm_online_user 表实体类:在线用户
 *
 * @author pan
 * @since 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("comm_online_user")
@ApiModel
public class CommOnlineUser extends SimpleBaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "会话id")
    @TableField("session_id")
    private String sessionId;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "登录时间")
    @TableField("login_time")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "用户真实姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "客户端ip")
    @TableField("client_ip")
    private String clientIp;

    @ApiModelProperty(value = "客户端浏览器信息")
    @TableField("client_browser")
    private String clientBrowser;

    @ApiModelProperty(value = "客户端操作系统信息")
    @TableField("client_os")
    private String clientOs;

    public static final String SESSION_ID = "session_id";

    public static final String USER_ID = "user_id";

    public static final String LOGIN_TIME = "login_time";

    public static final String EMAIL = "email";

    public static final String NAME = "name";

    public static final String CLIENT_IP = "client_ip";

    public static final String CLIENT_BROWSER = "client_browser";

    public static final String CLIENT_OS = "client_os";

}
