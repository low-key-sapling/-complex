package com.lowkey.complex.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * 获取日志模块的名称
 *
 * @author zhangjianbing
 * time 2019/7/9
 */
public class ModuleConverter extends ClassicConverter {

    private static final int MAX_LENGTH = 20;

    @Override
    public String convert(ILoggingEvent event) {
        if (event.getLoggerName().length() > MAX_LENGTH) {
            return "LOWKEY";
        } else {
            return event.getLoggerName();
        }
    }
}