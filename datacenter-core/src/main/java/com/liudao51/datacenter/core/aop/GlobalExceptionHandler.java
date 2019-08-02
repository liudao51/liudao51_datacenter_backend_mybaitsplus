package com.liudao51.datacenter.core.aop;

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
public class GlobalExceptionHandler {
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

    public String receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException {

        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        // 将资料解码
        String reqBody = sb.toString();
        return URLDecoder.decode(reqBody, "UTF-8");
    }

    private static String getPostData(HttpServletRequest request) {
        StringBuffer data = new StringBuffer();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine()))
                data.append(line);
        } catch (IOException e) {
        }
        return data.toString();
    }
}
