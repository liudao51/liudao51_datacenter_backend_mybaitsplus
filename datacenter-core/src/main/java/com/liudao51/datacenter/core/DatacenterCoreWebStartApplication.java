/**
 * TODO: 使Spring Boot程序支持外部Tomcat服务器（继承SpringBootServletInitializer类，并重写configure方法）
 */
package com.liudao51.datacenter.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DatacenterCoreWebStartApplication extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder builder)
    {
        // 这里要指向原先用main方法执行的Application启动类（即DatacenterCoreApplication类）
        return builder.sources(DatacenterCoreApplication.class);
    }
}
