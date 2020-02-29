package com.dj.ssm.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 权限拦截器
 */

@Component
public class AuthInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前请求是否有权限
        //当前请求是否在当前登陆用户的资源列表中
        HttpSession session = request.getSession();

        //没权限
        response.sendRedirect(request.getContextPath()+"/403.jsp");
        return false;
    }
}
