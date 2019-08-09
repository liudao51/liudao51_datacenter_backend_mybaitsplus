package com.liudao51.datacenter.common.mybatisplus.generator.strategy.impl;

import com.liudao51.datacenter.common.mybatisplus.generator.strategy.IBaseController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * class:
 * <p>
 * Created by jewel on 2019/8/5.
 */
public abstract class BaseControllerImpl implements IBaseController {
    /**
     * 得到request对象
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        return request;
    }
}
