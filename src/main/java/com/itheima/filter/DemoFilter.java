package com.itheima.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtil;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // 从请求头中获取JWT
        String jwt = httpRequest.getHeader("Authorization");

        // 获取url

        String requestURI = httpRequest.getRequestURI();

        // 判断urL 是否有login 有 就放行

        if (requestURI.contains("login")) {
            log.info("登录接口放行{}", requestURI);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 1 如果jwt 为空 或者 null 给客户端 返回错误提示

        if (!StringUtils.hasLength(jwt)) {
            log.info("jwt 为空或者 null,{}", jwt);

            Result error = Result.error("NOT_LOGIN");

            String s = JSON.toJSONString(error);

            httpResponse.getWriter().write(s);
            return;
        }

        // jwt 存在 解析jwt

        try {
            JwtUtil.decodeToken(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            Result error = Result.error("NOT_LOGIN");

            String s = JSON.toJSONString(error);

            httpResponse.getWriter().write(s);
            return;
        }

// 成功
        log.info("成功");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
