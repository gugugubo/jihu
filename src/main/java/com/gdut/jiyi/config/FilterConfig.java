package com.gdut.jiyi.config;

import com.gdut.jiyi.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册filter
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilterRegistration() {
        //创建filter
        LoginFilter loginFilter = new LoginFilter();
        //注册过滤器
        FilterRegistrationBean<LoginFilter> registration = new FilterRegistrationBean<>(loginFilter);
        registration.setFilter(loginFilter);
        //添加条件
        registration.addUrlPatterns("/*");
        return registration;
    }
}
