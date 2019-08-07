package com.liudao51.datacenter.core.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Description: MybatisPlus配置
 */
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = {"com.liudao51.datacenter.core.mapper"})
public class MybatisPlusConfig {

    /*
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        //设置单页最大限制数量（默认500条，小于0则不受限制）---mybatis-plus-boot-starter version>=3.1.1 下使用
        //paginationInterceptor.setLimit(2000);

        return paginationInterceptor;
    }

    /**
     * SQL执行效率插件
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
}
