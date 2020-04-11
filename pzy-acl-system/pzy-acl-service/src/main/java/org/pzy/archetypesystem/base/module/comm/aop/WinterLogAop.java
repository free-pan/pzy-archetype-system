package org.pzy.archetypesystem.base.module.comm.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.pzy.archetypesystem.base.module.comm.annotation.WinterLog;
import org.pzy.archetypesystem.base.module.comm.dto.CommLogAddDTO;
import org.pzy.archetypesystem.base.module.comm.enums.OptResultEnum;
import org.pzy.archetypesystem.base.module.comm.enums.WinterLogType;
import org.pzy.archetypesystem.base.module.comm.service.CommLogService;
import org.pzy.opensource.comm.util.JsonUtil;
import org.pzy.opensource.currentuser.ThreadCurrentUser;
import org.pzy.opensource.domain.GlobalConstant;
import org.pzy.opensource.web.util.HttpClientInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * WinterLogAop
 *
 * @author pan
 * @date 4/11/20
 */
@Slf4j
@Component
@Aspect
public class WinterLogAop implements Ordered {

    @Autowired
    private CommLogService logService;

    public WinterLogAop() {
        log.debug("通用日志切面初始化!");
    }

    @Pointcut(value = "@annotation(org.pzy.archetypesystem.base.module.comm.annotation.WinterLog)")
    private void getPointcut() {
    }

    @Around("getPointcut()")
    public Object aroundAopMethod(ProceedingJoinPoint point) throws Throwable {
        Object target = null;
        // 记录开始时间
        LocalDateTime now = LocalDateTime.now();
        Long beginTime = System.currentTimeMillis();
        CommLogAddDTO dto = new CommLogAddDTO();
        try {
            target = point.proceed();
            dto.setOptResult(OptResultEnum.SUCCESS.getCode());
            return target;
        } catch (Exception e) {
            try {
                String expTraceInfo = extractExceptionInfo(e);
                dto.setExpInfo(expTraceInfo);
            } catch (Exception ex) {
                log.error("记录异常日志,提取异常堆栈信息时发生错误!", e);
            }
            dto.setOptResult(OptResultEnum.EXP.getCode());
            throw e;
        } finally {
            // 保存日志
            try {
                saveOperateLog(point, now, beginTime, dto);
            } catch (Exception e) {
                log.error("保存通用操作日志时发生异常!", e);
            }
        }
    }

    /**
     * 保存操作日志
     *
     * @param point     切点信息
     * @param now       方法的开始执行时间
     * @param beginTime 方法的开始执行时间
     * @param dto       待保存的日志信息
     */
    private void saveOperateLog(ProceedingJoinPoint point, LocalDateTime now, Long beginTime, CommLogAddDTO dto) {
        // 记录结束时间
        Long endTime = System.currentTimeMillis();
        // 记录操作用时
        Long useTime = endTime - beginTime;
        dto.setUseTime(useTime);
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        WinterLog winterLog = method.getAnnotation(WinterLog.class);
        String methodFullName = method.getDeclaringClass().getName() + "#" + method.getName();
        if (log.isDebugEnabled()) {
            log.debug("日志切面在方法[{}]上执行!", methodFullName);
        }
        dto.setMethodFullName(methodFullName);
        dto.setCreateTime(now);
        dto.setCreatorId(ThreadCurrentUser.getUserId(null));
        dto.setCreatorName(ThreadCurrentUser.getRealName(null));
        dto.setFunCode(winterLog.code().name());
        dto.setFunName(winterLog.code().getCode());
        dto.setClientIp(HttpClientInfoUtil.getClientIp());

        if (WinterLogType.Login != winterLog.type() && winterLog.saveInputParam()) {
            // 只有非登录日志且要求记录输入参数的, 才记录请求参数
            Object[] paramArr = point.getArgs();
            String paramJson = null;
            if (null != paramArr && paramArr.length > 0) {
                List<Object> paramList = new ArrayList<>(paramArr.length);
                for (Object obj : paramArr) {
                    if (null == obj) {
                        continue;
                    }
                    if ((obj instanceof HttpServletRequest) || (obj instanceof ServletRequest)) {
                        continue;
                    }
                    if ((obj instanceof HttpServletResponse) || (obj instanceof ServletResponse)) {
                        continue;
                    }
                    if (obj instanceof MultipartFile) {
                        continue;
                    }
                    if (obj instanceof File) {
                        continue;
                    }
                    paramList.add(obj);
                }
                if (!CollectionUtils.isEmpty(paramList)) {
                    paramJson = JsonUtil.toJsonString(paramArr);
                }
            }
            dto.setInputParamJson(paramJson);
        }
        dto.setType(winterLog.type().getCode());
        logService.saveAndClearCache(dto);
    }

    /**
     * 提取异常的堆栈信息
     *
     * @param e 异常
     * @return 异常堆栈信息字符串
     */
    private String extractExceptionInfo(Exception e) throws IOException {
        PrintWriter pw = null;
        StringWriter sw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        } finally {
            if (null != pw) {
                pw.close();
            }
            if (null != sw) {
                sw.close();
            }
        }
    }

    @Override
    public int getOrder() {
        return GlobalConstant.AOP_ORDER_LOG;
    }
}
