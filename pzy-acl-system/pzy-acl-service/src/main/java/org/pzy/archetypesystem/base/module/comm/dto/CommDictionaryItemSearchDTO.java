package org.pzy.archetypesystem.base.module.comm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.pzy.opensource.domain.dto.KeywordDateRangeSearchDTO;
import org.pzy.opensource.domain.vo.PageVO;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * CommDictionaryItem查询条件
 *
 * @author pan
 * @date 2020-04-11
 */
@Data
public class CommDictionaryItemSearchDTO extends KeywordDateRangeSearchDTO {

    @ApiModelProperty(value = "分页条件")
    private PageVO pg;

    public CommDictionaryItemSearchDTO() {
        this.pg = new PageVO();
    }
}
