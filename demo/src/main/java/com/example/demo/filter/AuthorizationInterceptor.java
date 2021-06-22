package com.example.demo.filter;

import com.example.demo.JwtHelper;
import com.example.demo.dto.UserDto;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true; // 通过所有OPTION请求
        }
        //ResourceHttpRequestHandler hand=(ResourceHttpRequestHandler)handler;
        if (((HandlerMethod) handler).getMethod().isAnnotationPresent(AllowAnonymous.class)) {
            return true;
        }
        String token = request.getHeader("Authorization");
        var user = JwtHelper.verifyToken(token);
        if (user == null) {
            return false;
        }
        return true;
    }
}
