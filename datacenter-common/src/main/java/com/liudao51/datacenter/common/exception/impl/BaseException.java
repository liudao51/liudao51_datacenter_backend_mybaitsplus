package com.liudao51.datacenter.common.exception.impl;

import com.liudao51.datacenter.common.exception.IBaseException;

public abstract class BaseException extends Exception implements IBaseException {
    private static final long serialVersionUID = -8995789052017539691L;

    protected Integer code;

    // 重写父类的构造函数
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Integer code) {
        super();
        this.code = code;
    }

    public BaseException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BaseException(String message, Integer code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}
