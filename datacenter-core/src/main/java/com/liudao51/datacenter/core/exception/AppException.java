package com.liudao51.datacenter.core.exception;

import com.liudao51.datacenter.common.exception.impl.BaseRuntimeException;

public class AppException extends BaseRuntimeException {
    private static final long serialVersionUID = -7958719093860400633L;

    // 原有的重写父类的构造函数
    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Integer code) {
        super(code);
    }

    public AppException(String message, Integer code) {
        super(message, code);
    }

    public AppException(String message, Integer code, Throwable cause) {
        super(message, code, cause);
    }
}
