package com.lowkey.complex.security;

import com.lowkey.complex.enums.ContentTypeEnum;
import com.lowkey.complex.enums.LoginEnum;
import com.lowkey.complex.response.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        ResultEntity<Object> resultEntity = ResultEntity.failedWithoutData("未知错误");
        String userName = request.getParameter("userName");
        if (e instanceof AccountExpiredException) {
            // 账号过期
            log.info("[登录失败] - 用户[{}]账号过期", userName);
            resultEntity.setStatus(String.valueOf(LoginEnum.ACCOUNT_EXPIRED.getCode()));
            resultEntity.setMessage(LoginEnum.ACCOUNT_EXPIRED.getValue());

        } else if (e instanceof BadCredentialsException) {
            // 密码错误
            log.info("[登录失败] - 用户[{}]密码错误", userName);
            resultEntity.setStatus(String.valueOf(LoginEnum.ACCOUNT_PASSWORD_ERROR.getCode()));
            resultEntity.setMessage(LoginEnum.ACCOUNT_PASSWORD_ERROR.getValue());

        } else if (e instanceof CredentialsExpiredException) {
            // 密码过期
            log.info("[登录失败] - 用户[{}]密码过期", userName);
            resultEntity.setStatus(String.valueOf(LoginEnum.ACCOUNT_PASSWORD_EXPIRED.getCode()));
            resultEntity.setMessage(LoginEnum.ACCOUNT_PASSWORD_EXPIRED.getValue());

        } else if (e instanceof DisabledException) {
            // 用户被禁用
            log.info("[登录失败] - 用户[{}]被禁用", userName);

            resultEntity.setStatus(String.valueOf(LoginEnum.ACCOUNT_PASSWORD_DISABLE.getCode()));
            resultEntity.setMessage(LoginEnum.ACCOUNT_PASSWORD_DISABLE.getValue());


        } else if (e instanceof LockedException) {
            // 用户被锁定
            log.info("[登录失败] - 用户[{}]被锁定", userName);
            resultEntity.setStatus(String.valueOf(LoginEnum.LOGIN_LOCKED.getCode()));
            resultEntity.setMessage(LoginEnum.LOGIN_LOCKED.getValue());

        } else if (e instanceof InternalAuthenticationServiceException) {
            // 内部错误
            log.error(String.format("[登录失败] - [%s]内部错误", userName), e);

            resultEntity.setStatus(String.valueOf(LoginEnum.LOGIN_FAILED_UNKNOWN.getCode()));
            resultEntity.setMessage(LoginEnum.LOGIN_FAILED_UNKNOWN.getValue());

        } else {
            // 其他错误
            log.error(String.format("[登录失败] - [%s]其他错误", userName), e);
            resultEntity.setStatus(String.valueOf(LoginEnum.LOGIN_FAILED_UNKNOWN.getCode()));
            resultEntity.setMessage(LoginEnum.LOGIN_FAILED_UNKNOWN.getValue());
        }
        ResultEntity.makeResponse(response, ContentTypeEnum.APPLICATION_JSON.getValue(), LoginEnum.LOGIN_FAILED.getCode(), resultEntity);
    }
}