package org.pzy.archetypesystem.base.module.comm.vo;

import org.pzy.opensource.mybatisplus.model.entity.LogicDelBaseEntity;

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
 * comm_dictionary_item 表VO类: 用于服务端返回客户端数据
 *
 * @author pan
 * @since 2020-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class CommDictionaryItemVO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "所属字典id")
    private Long dictionaryId;
    @ApiModelProperty(value = "字典项标识(在同一个字典中必须唯一)")
    private String code;
    @ApiModelProperty(value = "字典项名称")
    private String name;
    @ApiModelProperty(value = "显示优先级. 值越大显示优先级越高")
    private Integer viewPriority;


    @ApiModelProperty(value = "主键")
    private Long id;

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
