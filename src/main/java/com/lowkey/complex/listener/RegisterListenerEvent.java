package com.lowkey.complex.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 注册事件监听
 */
@Slf4j
@Component
public class RegisterListenerEvent {
    @EventListener
    public void handleNotifyEvent(RegisterSuccessEvent registerSuccessEvent) {
        log.info("监听到用户注册成功事件,{}已经注册成功,记得登录看看哦~", registerSuccessEvent.getUserName());
    }
}
