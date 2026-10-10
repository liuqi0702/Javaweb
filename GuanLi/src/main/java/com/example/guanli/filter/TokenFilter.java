package com.example.guanli.filter;

import com.example.guanli.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        //1.获取请求路径
        String requestURI = request.getRequestURI();

        //2.判断是否是登录或注册接口，如果是，则放行
        if (requestURI.contains("/login")) {
            log.info("登录或注册接口，放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //3.获取token
        String token = request.getHeader("token");
        //4.判断token是否存在，如果不存在，则返回错误信息（响应401）
        if (token == null || token.isEmpty()) {
            log.info("token不存在，返回错误信息（响应401）");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //5.如果token存在，校验令牌，如果校验失败，则返回错误信息（响应401）
        try {
            JwtUtils.parseJwt(token); // 校验令牌
        } catch (Exception e) {
            log.info("token校验失败，返回错误信息（响应401）");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //6.如果校验通过，则放行
        log.info("token校验通过，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
