package com.lowkey.complex.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.lowkey.complex.entity.User;
import com.lowkey.complex.response.ResultEntity;
import com.lowkey.complex.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author yuanjifan
 * @description 用户控制器
 * @date 2023/4/28 9:23
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final IUserService userService;
    private final Cache<String, Object> caffeineCache;

    public UserController(IUserService userService, Cache<String, Object> caffeineCache) {
        this.userService = userService;
        this.caffeineCache = caffeineCache;
    }

    @RequestMapping("/list")
    @ResponseBody
    public ResultEntity<List<User>> getUserList() {
        logger.info("get user list.");
        return ResultEntity.successWithData(userService.list());
    }
    @RequestMapping("/login")
    @ResponseBody
    public ResultEntity<String> getLogin() {
        logger.info("get user list.");
        return ResultEntity.successWithData("hahaah");
    }
}