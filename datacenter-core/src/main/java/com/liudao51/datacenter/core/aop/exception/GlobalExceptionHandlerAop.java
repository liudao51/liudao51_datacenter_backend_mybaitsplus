package com.liudao51.datacenter.core.aop.exception;

import com.liudao51.datacenter.core.exception.AppException;
import com.liudao51.datacenter.core.exception.SystemException;
import com.liudao51.datacenter.core.response.ApiResponse;
import com.liudao51.datacenter.core.response.ApiResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 加强版Controller: 全局异常处理类
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAop {
    /**
     * 自定义异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(AppException.class)
    public ApiResponseBody exceptionHandle(HttpServletRequest request, AppException ex) throws Exception {
        ex.setIp(request.getRemoteAddr());
        ex.setUrl(request.getRequestURL().toString());
        ex.setParams(request.getParameterMap());

        return new ApiResponse().fail(ex);
    }

    /**
     * 系统异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ApiResponseBody exceptionHandle(HttpServletRequest request, Exception ex) {
        SystemException ex2 = new SystemException(ex); //把Exception转换为SystemException
        ex2.setIp(request.getRemoteAddr());
        ex2.setUrl(request.getRequestURL().toString());
        ex2.setParams(request.getParameterMap());

        return new ApiResponse().fail(ex2);
    }
}
