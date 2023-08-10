package com.lowkey.complex.security;

import com.lowkey.complex.enums.ContentTypeEnum;
import com.lowkey.complex.enums.LoginEnum;
import com.lowkey.complex.response.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AnonymousAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        log.warn("用户未登录，访问[{}]失败，AuthenticationException={}", request.getRequestURI(), e.getMessage(), e);
        ResultEntity.makeResponse(response, ContentTypeEnum.APPLICATION_JSON.getValue(), LoginEnum.LOGIN_NOT.getCode(), ResultEntity.failedWithoutData(LoginEnum.LOGIN_NOT.getValue()));
    }
}