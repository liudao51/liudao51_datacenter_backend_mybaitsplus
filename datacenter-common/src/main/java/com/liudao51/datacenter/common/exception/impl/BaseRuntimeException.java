package com.liudao51.datacenter.common.exception.impl;

import com.liudao51.datacenter.common.exception.IBaseException;

public abstract class BaseRuntimeException extends RuntimeException implements IBaseException {
    private static final long serialVersionUID = 5372178118281626525L;

    protected Integer code;

    // 重写父类的构造函数
    public BaseRuntimeException() {
        super();
    }

    public BaseRuntimeException(String message) {
        super(message);
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }

    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRuntimeException(Integer code) {
        super();
        this.code = code;
    }

    public BaseRuntimeException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BaseRuntimeException(String message, Integer code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}
