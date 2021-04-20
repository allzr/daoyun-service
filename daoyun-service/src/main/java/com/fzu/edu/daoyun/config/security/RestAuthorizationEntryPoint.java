package com.fzu.edu.daoyun.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzu.edu.daoyun.entity.ReturnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.security.auth.message.config.AuthConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ReturnBean bean = ReturnBean.error("请求错误");
        bean.setCode(401);
        out.write(new ObjectMapper().writeValueAsString(bean));
        out.flush();
        out.close();
    }
}
