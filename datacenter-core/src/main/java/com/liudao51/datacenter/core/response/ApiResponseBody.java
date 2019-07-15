package com.liudao51.datacenter.core.response;

import com.liudao51.datacenter.common.response.BaseResponseBody;
import lombok.Data;

@Data
public class ApiResponseBody extends BaseResponseBody {

    // 默认返回处理成功(不带数据data)
    public ApiResponseBody() {
        super();
    }

    // 默认返回处理成功(带数据data)
    public ApiResponseBody(Object data) {
        super(data);
    }

    public ApiResponseBody(int code, String message, Object data) {
        super(code, message, data);
    }
}
