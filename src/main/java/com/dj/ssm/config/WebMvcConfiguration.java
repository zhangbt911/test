package com.dj.ssm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author zhang_bt on 2020/2/2 10:56
 */
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(myInterceptor);
        //拦截请求
        interceptorRegistration.addPathPatterns("/**");
        interceptorRegistration.addPathPatterns("/user/toShow");

        //放过的请求
        //interceptorRegistration.excludePathPatterns("/toShow");
    }
}
