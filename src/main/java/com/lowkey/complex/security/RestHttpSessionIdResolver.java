package com.lowkey.complex.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 同时支持 sessionId 存到 cookie，header 和 request parameter
 *
 * @author songyinyin
 * @date 2020/3/18 下午 05:53
 */
@Slf4j
@Service("httpSessionIdResolver")
public class RestHttpSessionIdResolver {

}