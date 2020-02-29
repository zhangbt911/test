package com.dj.ssm.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhang_bt on 2020/2/2 10:54
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

  /*  @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截");
        return true;
    }*/
}
