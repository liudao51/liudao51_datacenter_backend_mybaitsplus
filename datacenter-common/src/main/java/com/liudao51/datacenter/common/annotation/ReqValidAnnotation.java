package com.liudao51.datacenter.common.annotation;

import java.lang.annotation.*;

/**
 * 拦截验证的注解<br/>
 * (注解表示的方法参数必须实现Validable接口)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReqValidAnnotation {

}
