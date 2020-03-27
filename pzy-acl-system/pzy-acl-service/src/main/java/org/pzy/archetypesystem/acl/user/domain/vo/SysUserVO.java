package org.pzy.archetypesystem.acl.user.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
* SysUserVO: 查询结果, server --> client 数据传输类
*
* @author pan
* @since 2020-03-24 16:44:51
*/
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
public class SysUserVO extends SimpleSysUserVO {

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
    * 创建人id.为0时表示是系统初始化时自动创建
    */
    @ApiModelProperty(value="创建人id.为0时表示是系统初始化时自动创建")
    private Long creatorId;

    /**
    * 创建人姓名
    */
    @ApiModelProperty(value="创建人姓名")
    private String creatorName;

    /**
    * 编辑时间
    */
    @ApiModelProperty(value="编辑时间")
    private Date editTime;

    /**
    * 编辑人id
    */
    @ApiModelProperty(value="编辑人id")
    private Long editorId;

    /**
    * 编辑人姓名
    */
    @ApiModelProperty(value="编辑人姓名")
    private String editorName;

    /**
    * 备注信息
    */
    @ApiModelProperty(value="备注信息")
    private String remark;

}