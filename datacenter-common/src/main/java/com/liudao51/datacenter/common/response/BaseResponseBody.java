package com.liudao51.datacenter.common.response;

import com.liudao51.datacenter.common.constant.ErrorCode;
import lombok.Data;

@Data
public abstract class BaseResponseBody {

    // 状态码
    protected int code;
    // 消息内容
    protected String message;
    // 时间戮
    protected long timestamp;
    // 数据内容
    protected Object data;

    // 默认返回处理成功(不带数据data)
    public BaseResponseBody() {
        this.code = ErrorCode.SUCCESS.toCode();
        this.message = ErrorCode.SUCCESS.toValue();
        this.timestamp = System.currentTimeMillis();
        this.data = null;
    }

    // 默认返回处理成功(带数据data)
    public BaseResponseBody(Object data) {
        this.code = ErrorCode.SUCCESS.toCode();
        this.message = ErrorCode.SUCCESS.toValue();
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }

    public BaseResponseBody(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }
}