package com.lowkey.complex.wechart.schedule;

import com.alibaba.fastjson.JSONObject;
import com.lowkey.complex.util.HttpClientWrapper;
import com.lowkey.complex.wechart.entity.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lowkey
 * @description 微信公众号定时任务
 * @date 2023年01月09日 17:01
 */
@Slf4j
@Component
public class WechatSchedule {
    private final WechatUtil wechatUtil;

    public WechatSchedule(WechatUtil wechatUtil) {
        this.wechatUtil = wechatUtil;
    }

    /**
     * @author yuanjifan
     * @description 测试定时任务, 每小时执行一次
     * @date 2023/4/28 9:57
     */
    @Scheduled(cron = "0 0 */2 * * ?")
    public void getAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + wechatUtil.getGrantType() + "&appid=" + wechatUtil.getAppid() + "&secret=" + wechatUtil.getAppsecret();
        HttpClientWrapper client = new HttpClientWrapper();
        String response;
        try {
            response = client.doGet(url);
        } catch (Exception e) {
            log.error("get access token error", e);
            throw new RuntimeException(e);
        }
        JSONObject jsonObject = JSONObject.parseObject(response);
        String accessToken = jsonObject.getString("access_token");
        log.info("accessToken = {}", accessToken);
        wechatUtil.getWechatCache().put("accessToken", accessToken);
    }
}
