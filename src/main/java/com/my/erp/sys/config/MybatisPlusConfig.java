package com.my.erp.sys.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatisPlusF分页插件导入
 */
@Configuration
@ConditionalOnClass(PaginationInterceptor.class)
public class MybatisPlusConfig {
    @Bean PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
