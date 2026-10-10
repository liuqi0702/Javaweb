package com.example.guanli.interceptor;

import com.example.guanli.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        //1.获取请求路径
//        String requestURI = request.getRequestURI();
//
//        //2.判断是否是登录或注册接口，如果是，则放行
//        if (requestURI.contains("/login")) {
//            log.info("登录或注册接口，放行");
//            return true;
//        }
        //3.获取token
        String token = request.getHeader("token");
        //4.判断token是否存在，如果不存在，则返回错误信息（响应401）
        if (token == null || token.isEmpty()) {
            log.info("token不存在，返回错误信息（响应401）");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //5.如果token存在，校验令牌，如果校验失败，则返回错误信息（响应401）
        try {
            JwtUtils.parseJwt(token); // 校验令牌
        } catch (Exception e) {
            log.info("token校验失败，返回错误信息（响应401）");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //6.如果校验通过，则放行
        log.info("token校验通过，放行");
        return true;
    }
}
