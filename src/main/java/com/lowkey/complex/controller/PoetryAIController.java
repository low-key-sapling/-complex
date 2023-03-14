package com.lowkey.complex.controller;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.lowkey.complex.response.ResultEntity;
import com.lowkey.complex.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 百度AI智能接口
 *
 * @author lowkey
 * @since 2022-07-19
 */
@Controller
@RequestMapping("/ai")
public class PoetryAIController {
    private static final Logger logger = LoggerFactory.getLogger(PoetryAIController.class);
    private static final String POETRY_BAIDU_AI_URL = "https://aip.baidubce.com/rpc/2.0/nlp/v1/poem";
    private static final String BAIDU_AI_API_KEY = "IfMd9Orb3UAfZ3G3rwjGMe2N";
    private static final String BAIDU_AI_CLIENT_SECRET = "IQAP5Efc2U5Rb0IjAMN67tQZi7jYFx1i";

    private static final HttpClientUtil httpClientUtil = new HttpClientUtil();

    @RequestMapping("/poetry")
    @ResponseBody
    public ResultEntity<Map> helloWorld(@RequestParam String text) {
        Map<String, String> header = Maps.newHashMap();
        header.put("Accept", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("text", text);
        String post = httpClientUtil.post(POETRY_BAIDU_AI_URL + "?access_token=" + getAccessToken(), jsonObject, header);
        return ResultEntity.successWithData(JSONUtil.toBean(post, Map.class));
    }

    /**
     * 从用户的AK，SK生成鉴权签名（Access Token）
     *
     * @return 鉴权签名（Access Token）
     */
    public static String getAccessToken() {
        Map<String, String> header = Maps.newHashMap();
        header.put("Content-Type", "application/x-www-form-urlencoded");
        JSONObject jsonObject = new JSONObject();
        String post = httpClientUtil.post("https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=" + BAIDU_AI_API_KEY + "&client_secret=" + BAIDU_AI_CLIENT_SECRET, jsonObject, header);
        return JSONUtil.toBean(post, Map.class).get("access_token").toString();
    }
}
