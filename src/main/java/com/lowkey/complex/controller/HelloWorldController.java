package com.lowkey.complex.controller;

import com.lowkey.complex.response.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/test")
public class HelloWorldController {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping("/hello")
    @ResponseBody
    public ResultEntity<String> helloWorld() {
        logger.info("this is test controller");
        return ResultEntity.successWithData("Hello World");
    }
}