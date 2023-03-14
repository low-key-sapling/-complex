package com.lowkey.complex.controller;

import com.lowkey.complex.response.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello World
 *
 * @author lowkey
 * @since 2022-07-19
 */
@Controller
@RequestMapping("")
public class HelloWorldController {

    @RequestMapping("/hello")
    @ResponseBody
    public ResultEntity<String> helloWorld() {
        return ResultEntity.successWithData("Hello World");
    }

}