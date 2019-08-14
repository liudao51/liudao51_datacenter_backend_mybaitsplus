package com.liudao51.datacenter.core.response;


import com.liudao51.datacenter.common.constant.ErrorCode;
import com.liudao51.datacenter.common.util.CollectionX;
import com.liudao51.datacenter.core.exception.AppException;
import com.liudao51.datacenter.core.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

/**
 * Api接口返回对象
 */
@Slf4j
public class ApiResponse<T> {
    /**
     * 成功返回对象
     */
    public ApiResponseBody success() {
        return new ApiResponseBody<T>();
    }

    public ApiResponseBody success(T data) {
        return new ApiResponseBody<>(data);
    }

    /*
     * 失败返回对象：记录可预知异常到日志文件A
     */
    public ApiResponseBody fail(String message, int code) {
        //不记录请求URL
        log.warn("普通异常：ERROR_CODE={}, ERROR_MESSAGE={}", code, message);

        return new ApiResponseBody<T>(code, message, null);
    }

    public ApiResponseBody fail(AppException ex) {
        //记录请求URL
        log.warn("程序异常：IP={}, URL={}, PARAMS={}, ERROR_CODE={}, ERROR_MESSAGE={}",
                ex.getIp(), ex.getUrl(), CollectionX.mapToJson(ex.getParams()).toString(), ex.getCode(), ex.getMessage());
        if (ex.getStackTrace() != null && ex.getStackTrace().length > 0) {
            log.warn("ERROR_INFO:", ex);
        }

        return new ApiResponseBody<T>(ex.getCode(), ex.getMessage(), null);
    }

    /*
     * 失败返回对象：记录不可预知异常到日志文件B
     */
    public ApiResponseBody fail(Exception ex) {
        //不记录请求URL
        log.error("未知异常：ERROR_CODE={},ERROR_MESSAGE={}", ErrorCode.SYSTEM_UNKNOWN_ERROR.toCode(), ex.getMessage());
        if (ex.getStackTrace() != null && ex.getStackTrace().length > 0) {
            log.error("ERROR_INFO:", ex);
        }

        return new ApiResponseBody<T>(ErrorCode.SYSTEM_UNKNOWN_ERROR.toCode(), ErrorCode.SYSTEM_UNKNOWN_ERROR.toValue(), null);
    }

    public ApiResponseBody fail(SystemException ex) {
        //记录请求URL
        log.error("系统异常：IP={}, URL={}, PARAMS={}, ERROR_CODE={}, ERROR_MESSAGE={}",
                ex.getIp(), ex.getUrl(), CollectionX.mapToJson(ex.getParams()).toString(), ErrorCode.SYSTEM_UNKNOWN_ERROR.toCode(), ex.getMessage());
        if (ex.getStackTrace() != null && ex.getStackTrace().length > 0) {
            log.error("ERROR_INFO:", ex);
        }

        return new ApiResponseBody<T>(ErrorCode.SYSTEM_UNKNOWN_ERROR.toCode(), ErrorCode.SYSTEM_UNKNOWN_ERROR.toValue(), null);
    }
}
