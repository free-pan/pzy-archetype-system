package org.pzy.archetypesystem.base.module.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * comm_dictionary_item 表DTO类: 用于新增操作时接收客户端参数
 *
 * @author pan
 * @since 2020-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class CommDictionaryItemAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "所属字典id")
    @NotNull(message = "请指定所属字典!")
    private Long dictionaryId;

    @ApiModelProperty(value = "字典项标识(在同一个字典中必须唯一)")
    @NotBlank(message = "请字典项标识!")
    private String code;

    @ApiModelProperty(value = "字典项名称")
    @NotBlank(message = "请字典项名称!")
    private String name;

    @ApiModelProperty(value = "显示优先级. 值越大显示优先级越高")
    @NotNull(message = "请指定显示优先级!")
    @Min(value = 0, message = "显示优先级最小为0!")
    private Integer viewPriority;

}
