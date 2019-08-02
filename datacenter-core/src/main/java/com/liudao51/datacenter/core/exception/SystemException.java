package com.liudao51.datacenter.core.exception;

import com.liudao51.datacenter.common.exception.impl.BaseRuntimeException;

public class SystemException extends BaseRuntimeException {
    private static final long serialVersionUID = -7958719093860400634L;

    // 原有的重写父类的构造函数
    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Integer code) {
        super(code);
    }

    public SystemException(String message, Integer code) {
        super(message, code);
    }

    public SystemException(String message, Integer code, Throwable cause) {
        super(message, code, cause);
    }
}
