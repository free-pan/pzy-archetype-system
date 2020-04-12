package org.pzy.archetypesystem.base.module.comm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * comm_dictionary_item 表VO类: 用于服务端返回客户端数据
 *
 * @author pan
 * @since 2020-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class CommDictionaryItemVO extends CommDictionaryItemSimpleVO {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "是否禁用. 0.已禁用 1.未禁用")
    private Integer disabled;

    @ApiModelProperty(value = "禁用操作的操作时间")
    private LocalDateTime disabledTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "编辑时间")
    private LocalDateTime editTime;

    @ApiModelProperty(value = "创建人id")
    private Long creatorId;

    @ApiModelProperty(value = "编辑人id")
    private Long editorId;

    @ApiModelProperty(value = "创建人姓名")
    private String creatorName;

    @ApiModelProperty(value = "编辑人姓名")
    private String editorName;

    @ApiModelProperty(value = "禁用操作的操作人id")
    private Long disabledOptId;

    @ApiModelProperty(value = "禁用操作的操作人姓名")
    private String disabledOptName;

}
