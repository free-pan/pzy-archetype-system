package org.pzy.archetypesystem.base.module.comm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.pzy.opensource.domain.dto.KeywordDateRangeSearchDTO;
import org.pzy.opensource.domain.vo.PageVO;

/**
 * CommOnlineUser查询条件
 *
 * @author pan
 * @date 2020-04-06
 */
@Data
public class CommOnlineUserSearchDTO extends KeywordDateRangeSearchDTO {

    @ApiModelProperty(value = "分页条件")
    private PageVO pg;

    public CommOnlineUserSearchDTO() {
        this.pg = new PageVO();
    }
}
