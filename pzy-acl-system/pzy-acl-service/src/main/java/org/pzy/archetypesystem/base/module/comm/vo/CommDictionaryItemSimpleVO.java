package org.pzy.archetypesystem.base.module.comm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class CommDictionaryItemSimpleVO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "所属字典编码")
    private String dictionaryCode;
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
}
