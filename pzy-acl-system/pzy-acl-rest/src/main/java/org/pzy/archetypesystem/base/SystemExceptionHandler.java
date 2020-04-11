package org.pzy.archetypesystem.base;

import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.base.module.comm.dto.CommLogAddDTO;
import org.pzy.archetypesystem.base.module.comm.enums.FunCodeEnum;
import org.pzy.archetypesystem.base.module.comm.enums.OptResultEnum;
import org.pzy.archetypesystem.base.module.comm.enums.WinterLogType;
import org.pzy.archetypesystem.base.module.comm.service.CommLogService;
import org.pzy.opensource.comm.util.ExceptionUtil;
import org.pzy.opensource.currentuser.ThreadCurrentUser;
import org.pzy.opensource.springboot.errorhandler.DefaultWinterExceptionHandlerImpl;
import org.pzy.opensource.web.util.HttpClientInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * SystemExceptionHandler
 *
 * @author pan
 * @date 4/11/20
 */
@Slf4j
@RestControllerAdvice
public class SystemExceptionHandler extends DefaultWinterExceptionHandlerImpl {

    @Autowired
    private CommLogService commLogService;

    @Override
    public void customExtendsExceptoinHandler(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception{
        if (e instanceof HttpMessageNotReadableException) {
            String expTraceInfo = ExceptionUtil.extractExceptionStackTrace(e);
            CommLogAddDTO dto = new CommLogAddDTO();
            dto.setExpInfo(expTraceInfo);
            dto.setCreateTime(LocalDateTime.now());
            dto.setCreatorId(ThreadCurrentUser.getUserId(null));
            dto.setCreatorName(ThreadCurrentUser.getRealName(null));
            dto.setFunCode(FunCodeEnum.Spring.name());
            dto.setFunName(FunCodeEnum.Spring.getCode());
            dto.setClientIp(HttpClientInfoUtil.getClientIp());
            dto.setType(WinterLogType.FrameworkExp.getCode());
            dto.setOptResult(OptResultEnum.EXP.getCode());
            commLogService.saveAndClearCache(dto);
        }
    }
}
