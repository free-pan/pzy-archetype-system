package org.pzy.archetypesystem.base.module.comm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.pzy.archetypesystem.base.module.comm.enums.FunCodeEnum;
import org.pzy.archetypesystem.base.module.comm.enums.WinterLogType;
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

    @ApiModelProperty(value = "日志类型. Operate:操作日志. Login: 登录日志")
    private WinterLogType type;

    @ApiModelProperty(value = "业务方法编码")
    private FunCodeEnum funCode;

    public CommLogSearchDTO() {
        this.pg = new PageVO();
    }
}
