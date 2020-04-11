package org.pzy.archetypesystem.base.module.comm.dto;

import org.pzy.opensource.mybatisplus.model.entity.LogicDelBaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * comm_dictionary 表DTO类: 用于新增操作时接收客户端参数
 *
 * @author pan
 * @since 2020-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class CommDictionaryAddDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "字典唯一编码")
    private String code;

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "备注信息")
    private String remark;

}
