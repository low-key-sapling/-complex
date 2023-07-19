package com.lowkey.complex.listener;

import lombok.Data;

/**
 * 注册成功事件
 */
@Data
public class RegisterSuccessEvent {
    private String userName;

    public RegisterSuccessEvent(String userName) {
        this.userName = userName;
    }
}
