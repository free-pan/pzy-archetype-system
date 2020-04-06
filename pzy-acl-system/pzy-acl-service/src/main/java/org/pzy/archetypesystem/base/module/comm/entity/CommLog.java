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
 * comm_log 表实体类:通用日志
 *
 * @author pan
 * @since 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("comm_log")
@ApiModel
public class CommLog extends SimpleBaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "日志类型. 1.操作日志 2.登录日志")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "创建人id")
    @TableField("creator_id")
    private Long creatorId;

    @ApiModelProperty(value = "创建人姓名")
    @TableField("creator_name")
    private String creatorName;

    @ApiModelProperty(value = "功能编码")
    @TableField("fun_code")
    private String funCode;

    @ApiModelProperty(value = "功能名")
    @TableField("fun_name")
    private String funName;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "操作结果. 1.成功 2.异常")
    @TableField("opt_result")
    private Integer optResult;

    @ApiModelProperty(value = "异常信息")
    @TableField("exp_info")
    private String expInfo;

    @ApiModelProperty(value = "操作用时. 单位: 毫秒")
    @TableField("use_time")
    private Float useTime;

    public static final String TYPE = "type";

    public static final String CREATOR_ID = "creator_id";

    public static final String CREATOR_NAME = "creator_name";

    public static final String FUN_CODE = "fun_code";

    public static final String FUN_NAME = "fun_name";

    public static final String CREATE_TIME = "create_time";

    public static final String OPT_RESULT = "opt_result";

    public static final String EXP_INFO = "exp_info";

    public static final String USE_TIME = "use_time";

}
