package org.pzy.archetypesystem.base.module.acl.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.pzy.opensource.domain.dto.KeywordDateRangeSearchDTO;
import org.pzy.opensource.domain.vo.PageVO;

/**
 * SysUser查询条件
 *
 * @author pan
 * @date 2020-04-05
 */
@Data
public class SysUserSearchDTO extends KeywordDateRangeSearchDTO {

    @ApiModelProperty(value = "分页条件")
    private PageVO pg;

    public SysUserSearchDTO() {
        this.pg = new PageVO();
    }
}
