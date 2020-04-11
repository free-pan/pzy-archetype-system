package org.pzy.archetypesystem.base.module.comm.entity;

import org.pzy.opensource.mybatisplus.model.entity.LogicDelBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.time.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * comm_dictionary 表实体类:字典
 *
 * @author pan
 * @since 2020-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("comm_dictionary")
@ApiModel
public class CommDictionary extends LogicDelBaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "字典唯一编码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "字典名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "备注信息")
    @TableField("remark")
    private String remark;

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String REMARK = "remark";

}
