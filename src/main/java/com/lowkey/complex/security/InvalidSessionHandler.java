package com.lowkey.complex.security;

import com.lowkey.complex.enums.ContentTypeEnum;
import com.lowkey.complex.enums.LoginEnum;
import com.lowkey.complex.response.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class InvalidSessionHandler implements InvalidSessionStrategy {

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("用户登录超时，访问[{}]失败", request.getRequestURI());

        ResultEntity.makeResponse(response, ContentTypeEnum.APPLICATION_JSON.getValue(), LoginEnum.LOGIN_SESSION_EXPIRED.getCode(), ResultEntity.failedWithoutData(LoginEnum.LOGIN_SESSION_EXPIRED.getValue()));
    }
}