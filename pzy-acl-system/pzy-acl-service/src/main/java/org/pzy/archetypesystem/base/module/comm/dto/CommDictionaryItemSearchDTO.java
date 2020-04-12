package org.pzy.archetypesystem.base.module.comm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.pzy.opensource.domain.dto.KeywordSearchDTO;
import org.pzy.opensource.domain.enums.DisableStatusEnum;

import javax.validation.constraints.NotNull;

/**
 * CommDictionaryItem查询条件
 *
 * @author pan
 * @date 2020-04-11
 */
@Data
public class CommDictionaryItemSearchDTO extends KeywordSearchDTO {

    @ApiModelProperty(value = "所属字典id", example = "1")
    @NotNull(message = "请指定所属字典")
    private Long dictionaryId;

    @ApiModelProperty(value = "启用禁用状态")
    private DisableStatusEnum status;
}
