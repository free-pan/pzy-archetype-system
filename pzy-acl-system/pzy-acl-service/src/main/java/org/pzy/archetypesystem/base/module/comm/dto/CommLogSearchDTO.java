package org.pzy.archetypesystem.base.module.comm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.pzy.opensource.domain.dto.KeywordDateRangeSearchDTO;
import org.pzy.opensource.domain.vo.PageVO;

/**
 * CommLog查询条件
 *
 * @author pan
 * @date 2020-04-06
 */
@Data
public class CommLogSearchDTO extends KeywordDateRangeSearchDTO {

    @ApiModelProperty(value = "分页条件")
    private PageVO pg;

    @ApiModelProperty(value = "用时大于等于. 单位:毫秒")
    private Long useTime;

    public CommLogSearchDTO() {
        this.pg = new PageVO();
    }
}
