package com.lowkey.complex.controller;

import com.lowkey.complex.entity.User;
import com.lowkey.complex.response.ResultEntity;
import com.lowkey.complex.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户表 前端控制器
 *
 * @author lowkey
 * @since 2022-07-19
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping("/list")
    @ResponseBody
    public ResultEntity<List<User>> getUserList() {
        List<User> list = userService.list();
        String s = list.get(0).toString();
        return ResultEntity.successWithData(userService.list());
    }
}