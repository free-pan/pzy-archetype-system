package org.pzy.archetypesystem.base.module.comm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.pzy.opensource.mybatisplus.model.entity.LogicDelBaseEntity;

/**
 * comm_dictionary_item 表实体类:字典项
 *
 * @author pan
 * @since 2020-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("comm_dictionary_item")
@ApiModel
public class CommDictionaryItem extends LogicDelBaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "所属字典id")
    @TableField("dictionary_id")
    private Long dictionaryId;

    @ApiModelProperty(value = "字典项标识(在同一个字典中必须唯一)")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "字典项名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "显示优先级. 值越大显示优先级越高")
    @TableField("view_priority")
    private Integer viewPriority;

    public static final String DICTIONARY_ID = "dictionary_id";

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String VIEW_PRIORITY = "view_priority";

}
