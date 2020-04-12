package org.pzy.archetypesystem.base.module.comm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.pzy.opensource.domain.dto.KeywordSearchDTO;
import org.pzy.opensource.domain.enums.DisableStatusEnum;
import org.pzy.opensource.domain.vo.PageVO;

/**
 * CommDictionary查询条件
 *
 * @author pan
 * @date 2020-04-11
 */
@Data
public class CommDictionarySearchDTO extends KeywordSearchDTO {

    @ApiModelProperty(value = "分页条件")
    private PageVO pg;

    @ApiModelProperty(value = "启用禁用状态")
    private DisableStatusEnum status;

    public CommDictionarySearchDTO() {
        this.pg = new PageVO();
    }
}
