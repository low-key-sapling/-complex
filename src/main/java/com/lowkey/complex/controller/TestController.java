package com.lowkey.complex.controller;

import com.lowkey.complex.listener.RegisterSuccessEvent;
import com.lowkey.complex.response.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    final ApplicationContext applicationContext;

    public TestController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @RequestMapping("/hello")
    public ResultEntity<String> helloWorld() {
        log.info("this is test controller to hello world.");
        return ResultEntity.successWithData("Hello World");
    }

    @RequestMapping("/register/user")
    public ResultEntity<String> registerUser(String userName) {
        log.info("=======测试用户注册.");
        //发布用户注册事件
        applicationContext.publishEvent(new RegisterSuccessEvent(userName));
        return ResultEntity.successWithData("用户注册成功");
    }
}
