package org.pzy.archetypesystem.acl.sysrole.vo;

import org.pzy.opensource.mybatisplus.model.entity.LogicDelBaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.*;

/**
 * sys_role 表VO类: 用于服务端返回客户端数据
 *
 * @author pan
 * @since 2020-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class SysRoleVO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色名")
    private String name;
    @ApiModelProperty(value = "角色编码. 必须唯一")
    private String code;
    @ApiModelProperty(value = "备注信息")
    private String remark;


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
