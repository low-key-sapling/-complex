package com.lowkey.complex.security;

import com.lowkey.complex.enums.ContentTypeEnum;
import com.lowkey.complex.enums.LoginEnum;
import com.lowkey.complex.response.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class DefaultLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        ResultEntity.makeResponse(response, ContentTypeEnum.APPLICATION_JSON.getValue(), LoginEnum.LOGIN_OUT_SUCCESS.getCode(), ResultEntity.successWithData(LoginEnum.LOGIN_OUT_SUCCESS.getValue()));
    }
}