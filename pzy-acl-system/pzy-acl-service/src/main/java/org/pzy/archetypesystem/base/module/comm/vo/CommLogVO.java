package org.pzy.archetypesystem.base.module.comm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * comm_log 表VO类: 用于服务端返回客户端数据
 *
 * @author pan
 * @since 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class CommLogVO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "日志类型. 1.操作日志 2.登录日志")
    private Integer type;
    @ApiModelProperty(value = "创建人id")
    private Long creatorId;
    @ApiModelProperty(value = "创建人姓名")
    private String creatorName;
    @ApiModelProperty(value = "功能编码")
    private String funCode;
    @ApiModelProperty(value = "功能名")
    private String funName;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "操作结果. 1.成功 2.异常")
    private Integer optResult;
    @ApiModelProperty(value = "异常信息")
    private String expInfo;
    @ApiModelProperty(value = "操作用时. 单位: 毫秒")
    private Long useTime;
    @ApiModelProperty(value = "执行方法的全名")
    private String methodFullName;
    @ApiModelProperty(value = "输入参数的json格式")
    private String inputParamJson;
    @ApiModelProperty(value = "客户端ip")
    private String clientIp;

    @ApiModelProperty(value = "主键")
    private Long id;

}
