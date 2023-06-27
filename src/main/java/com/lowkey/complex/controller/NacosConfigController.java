package com.lowkey.complex.controller;

import com.lowkey.complex.response.ResultEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/nacos")
@RefreshScope
public class NacosConfigController {

    @Value("${user.name:test}")
    private String name;
    @Value("${user.age:1}")
    private String age;

    @GetMapping(value = "/config")
    public ResultEntity<Map<String, String>> function() {
        Map<String, String> entries = new HashMap<>();
        entries.put("name", name);
        entries.put("age", age);
        return ResultEntity.successWithData(entries);
    }
}