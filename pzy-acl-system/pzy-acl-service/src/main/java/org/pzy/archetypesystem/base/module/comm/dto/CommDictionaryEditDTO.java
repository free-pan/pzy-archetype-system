package org.pzy.archetypesystem.base.module.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * comm_dictionary 表DTO类: 用于编辑操作时接收客户端参数
 *
 * @author pan
 * @since 2020-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class CommDictionaryEditDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "字典唯一编码")
    private String code;

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "备注信息")
    private String remark;

    @ApiModelProperty(value = "主键")
    private Long id;

}
