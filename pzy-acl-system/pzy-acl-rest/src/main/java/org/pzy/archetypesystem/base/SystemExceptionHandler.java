package org.pzy.archetypesystem.base;

import lombok.extern.slf4j.Slf4j;
import org.pzy.opensource.springboot.errorhandler.DefaultWinterExceptionHandlerImpl;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SystemExceptionHandler
 *
 * @author pan
 * @date 4/11/20
 */
@Slf4j
@RestControllerAdvice
public class SystemExceptionHandler extends DefaultWinterExceptionHandlerImpl {
    @Override
    public void customExtendsExceptoinHandler(HttpServletRequest req, HttpServletResponse response, Exception e) {
        if (e instanceof HttpMessageNotReadableException) {
            
        }
    }
}
