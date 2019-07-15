package com.liudao51.datacenter.core.response;


import com.liudao51.datacenter.common.constant.ErrorCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Api接口返回对象
 */
@Slf4j
public class ApiResponse {
    /**
     * 成功返回对象
     */
    public ApiResponseBody success() {
        return new ApiResponseBody();
    }

    public ApiResponseBody success(Object data) {
        return new ApiResponseBody(data);
    }

    /**
     * 失败返回对象
     */
    public ApiResponseBody fail(int code, String message) {
        return new ApiResponseBody(code, message, null);
    }

    public ApiResponseBody fail(Exception e) {
        int code = ErrorCode.SYSTEM_UNKNOWN_ERROR.toCode();
        String message = e.getMessage();

        //记录日志
        log.error("ERROR_CODE={},ERROR_NAME={}", code, message);
        if (e.getStackTrace() != null && e.getStackTrace().length > 0) {
            log.error("ERROR INFO:", e);
        }

        return new ApiResponseBody(code, message, null);
    }
}
