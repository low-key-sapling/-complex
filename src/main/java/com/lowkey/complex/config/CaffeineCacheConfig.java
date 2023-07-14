package com.lowkey.complex.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.concurrent.TimeUnit;

/**
 * Created by lin on 19/3/14.
 */
@EnableConfigurationProperties(CaffeineProperties.class)
@EnableCaching
@Configuration
public class CaffeineCacheConfig {
    private final CaffeineProperties caffeineProperties;

    public CaffeineCacheConfig(CaffeineProperties caffeineProperties) {
        this.caffeineProperties = caffeineProperties;
    }

    @Bean
    public Cache<String, Object> caffeineCache() {
        long maximumSize = caffeineProperties.getMaximumSize();
        long duration = caffeineProperties.getExpireAfterAccessDuration();
        if (maximumSize <= 0) {
            maximumSize = 1024;
        }
        if (duration <= 0) {
            //默认超时时间10分钟
            duration = 10 * 60 * 1000;
        }
        return Caffeine.newBuilder()
                // 设置最后一次写入或访问后经过固定时间过期
                .expireAfterWrite(duration, TimeUnit.SECONDS)
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 缓存的最大条数
                .maximumSize(maximumSize).build();
    }

    @Bean
    public Caffeine<Object, Object> cacheBuilder() {
        long maximumSize = caffeineProperties.getMaximumSize();
        long duration = caffeineProperties.getExpireAfterAccessDuration();
        if (maximumSize <= 0) {
            maximumSize = 1024;
        }
        if (duration <= 0) {
            //默认超时时间10分钟
            duration = 10 * 60 * 1000;
        }
        return Caffeine.newBuilder()
                // 设置最后一次写入或访问后经过固定时间过期
                .expireAfterAccess(duration, TimeUnit.SECONDS)
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 缓存的最大条数
                .maximumSize(maximumSize);
    }

    /**
     * expireAfterAccess: 当缓存项在指定的时间段内没有被读或写就会被回收。
     * expireAfterWrite：当缓存项在指定的时间段内没有更新就会被回收,如果我们认为缓存数据在一段时间后数据不再可用，那么可以使用该种策略。
     * refreshAfterWrite：当缓存项上一次更新操作之后的多久会被刷新。
     *
     * @return 缓存管理器
     */
    @DependsOn({"cacheBuilder"})
    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> cacheBuilder) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(cacheBuilder);
        return cacheManager;
    }

    /*
    配置说明：
    initialCapacity	integer	初始的缓存空间大小
    maximumSize	long	缓存的最大条数
    maximumWeight	long	缓存的最大权重
    expireAfterAccess	duration	最后一次写入或访问后经过固定时间过期
    refreshAfterWrite	duration	最后一次写入后经过固定时间过期
    refreshAfterWrite	duration	创建缓存或者最近一次更新缓存后经过固定的时间间隔，刷新缓存
    weakKeys	boolean	打开 key 的弱引用
    weakValues	boolean	打开 value 的弱引用
    softValues	boolean	打开 value 的软引用
    recordStats	-	开发统计功能
     */

    /*
    注意：
    weakValues 和 softValues 不可以同时使用。
    maximumSize 和 maximumWeight 不可以同时使用。
    expireAfterWrite 和 expireAfterAccess 同事存在时，以 expireAfterWrite 为准。
    3、软引用与弱引用
    软引用： 如果一个对象只具有软引用，则内存空间足够，垃圾回收器就不会回收它；如果内存空间不足了，就会回收这些对象的内存。
    弱引用： 弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存
    // 软引用
    Caffeine.newBuilder().softValues().build();
    // 弱引用
    Caffeine.newBuilder().weakKeys().weakValues().build();
     */
}

