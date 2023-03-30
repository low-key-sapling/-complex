package com.lowkey.complex.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author yuanjifan
 * @description 获取日志模块的名称
 * @date 2023/3/30 9:27
 */
public class ModuleConverter extends ClassicConverter {

    private static final int MAX_LENGTH = 120;

    @Override
    public String convert(ILoggingEvent event) {
        if (event.getLoggerName().length() > MAX_LENGTH) {
            return "LOWKEY";
        } else {
            String loggerName = event.getLoggerName();
            int lastDotIndex = loggerName.lastIndexOf(".");
            if (lastDotIndex != -1) {
                loggerName = loggerName.substring(lastDotIndex + 1);
            }
            return loggerName;
        }
    }
}