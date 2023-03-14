package com.lowkey.complex.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author yuanjifan
 * @description 获取IP地址
 * @date 2021/11/18 16:07
 */
public class IPAddressConverter extends ClassicConverter {
    private static String ipAddress;
    private static final Logger logger = LoggerFactory.getLogger(IPAddressConverter.class);

    static {
        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("fetch localhost host address failed", e);
            ipAddress = "UNKNOWN";
        }
    }

    @Override
    public String convert(ILoggingEvent event) {
        return ipAddress;
    }
}