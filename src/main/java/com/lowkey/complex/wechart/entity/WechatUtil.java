package com.lowkey.complex.wechart.entity;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class WechatUtil {
    @Value("${wechat.interface.config.token}")
    private String token;
    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.appsecret}")
    private String appsecret;
    private static final Cache<String, String> wechatCache = Caffeine.newBuilder()
            // 设置最后一次写入或访问后经过固定时间过期
            .expireAfterWrite(7000, TimeUnit.SECONDS)
            // 初始的缓存空间大小
            .initialCapacity(10)
            // 缓存的最大条数
            .maximumSize(100).build();

    public String getToken() {
        return token;
    }

    public String getAppid() {
        return appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public String getGrantType() {
        return "client_credential";
    }

    public static Cache<String, String> getWechatCache() {
        return wechatCache;
    }

    /**
     * 获取 access_token
     *
     * @return access_token
     */
    public static String getAccessToken() {
        return getWechatCache().getIfPresent("accessToken");
    }
}