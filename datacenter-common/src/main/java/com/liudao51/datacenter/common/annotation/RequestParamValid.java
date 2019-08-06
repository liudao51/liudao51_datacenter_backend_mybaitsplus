package com.liudao51.datacenter.common.annotation;

import java.lang.annotation.*;

/**
 * 拦截验证的注解<br/>
 * (注解@RequestParamValid表示的方法的参数对象必须实现ParamValidInterface接口)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParamValid {

}
