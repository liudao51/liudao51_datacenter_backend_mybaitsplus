package com.liudao51.datacenter.common.exception;

import java.util.Map;

/**
 * interface:
 * <p>
 * Created by jewel on 2019/7/15.
 */
public interface IBaseException {
    Integer getCode();

    String getIp();

    void setIp(String ip);

    String getUrl();

    void setUrl(String url);

    Map getParams();

    void setParams(Map params);
}
