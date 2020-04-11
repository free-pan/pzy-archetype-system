package org.pzy.archetypesystem.base.module.comm.dto;

import io.swagger.annotations.ApiModelProperty;
import org.pzy.archetypesystem.base.module.comm.enums.FunCodeEnum;
import org.pzy.archetypesystem.base.module.comm.enums.OptResultEnum;
import org.pzy.archetypesystem.base.module.comm.enums.WinterLogType;
import org.pzy.opensource.domain.dto.KeywordDateRangeSearchDTO;
import org.pzy.opensource.domain.vo.PageVO;

import javax.validation.constraints.NotNull;

/**
 * CommLog查询条件
 *
 * @author pan
 * @date 2020-04-06
 */
public class CommLogSearchDTO extends KeywordDateRangeSearchDTO {

    @ApiModelProperty(value = "分页条件")
    private PageVO pg;

    @ApiModelProperty(value = "操作人id")
    private Long operatorId;

    @ApiModelProperty(value = "用时大于等于. 单位:毫秒")
    private Long useTime;

    @ApiModelProperty(value = "日志类型. Operate:操作日志. Login: 登录日志")
    @NotNull(message = "请指定正确的日志类型!")
    private WinterLogType type;

    @ApiModelProperty(value = "业务方法编码")
    private FunCodeEnum funCode;

    @ApiModelProperty(value = "操作结果类型")
    private OptResultEnum optResult;

    public CommLogSearchDTO() {
        this.pg = new PageVO();
    }

    public PageVO getPg() {
        return pg;
    }

    public void setPg(PageVO pg) {
        this.pg = pg;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    public WinterLogType getType() {
        return type;
    }

    public void setType(WinterLogType type) {
        this.type = type;
    }

    public FunCodeEnum getFunCode() {
        return funCode;
    }

    public void setFunCode(FunCodeEnum funCode) {
        this.funCode = funCode;
    }

    public OptResultEnum getOptResult() {
        return optResult;
    }

    public void setOptResult(OptResultEnum optResult) {
        this.optResult = optResult;
    }
}
