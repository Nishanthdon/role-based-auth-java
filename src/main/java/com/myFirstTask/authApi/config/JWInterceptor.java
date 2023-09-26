package com.myFirstTask.authApi.config;

import com.myFirstTask.authApi.common.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//@Component
//public class JWInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    JWTUtils jwtUtils;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//System.out.println("preHandle");
//        if(!(request.getRequestURI().contains("login") || request.getRequestURI().contains("register"))){
//            String authHeader = request.getHeader("authorization");
//            jwtUtils.verify(authHeader);
//        }
//
//        return HandlerInterceptor.super.preHandle(request, response, handler);
//    }
//}
