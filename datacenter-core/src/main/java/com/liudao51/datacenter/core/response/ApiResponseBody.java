package com.liudao51.datacenter.core.response;

import com.liudao51.datacenter.common.response.BaseResponseBody;
import lombok.Data;

@Data
public class ApiResponseBody<T> extends BaseResponseBody {

    // 默认返回处理成功(不带数据data)
    public ApiResponseBody() {
        super();
    }

    // 默认返回处理成功(带数据data)
    public ApiResponseBody(T data) {
        super(data);
    }

    public ApiResponseBody(Integer code, String message, Object data) {
        super(code, message, data);
    }
}
