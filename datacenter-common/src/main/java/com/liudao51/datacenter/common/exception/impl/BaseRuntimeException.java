package com.liudao51.datacenter.common.exception.impl;

import com.liudao51.datacenter.common.exception.IBaseException;

import java.util.Map;

public abstract class BaseRuntimeException extends RuntimeException implements IBaseException {
    private static final long serialVersionUID = 5372178118281626525L;

    protected Integer code;
    protected String ip;
    protected String url;
    protected Map params;

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

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map getParams() {
        return this.params;
    }

    public void setParams(Map params) {
        this.params = params;
    }
}
