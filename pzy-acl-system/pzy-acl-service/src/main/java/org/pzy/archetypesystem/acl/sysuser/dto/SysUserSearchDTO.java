/**
 * Copyright (C): 恒大集团版权所有 Evergrande Group
 */
package org.pzy.archetypesystem.acl.sysuser.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.pzy.opensource.domain.dto.KeywordDateRangeSearchDTO;
import org.pzy.opensource.domain.vo.PageVO;

/**
 * 用户查询条件
 *
 * @author pan
 * @date 2020/4/4 15:35
 */
@Data
public class SysUserSearchDTO extends KeywordDateRangeSearchDTO {

    @ApiModelProperty(value = "分页条件")
    private PageVO page;

    public SysUserSearchDTO() {
        this.page = new PageVO();
    }
}
