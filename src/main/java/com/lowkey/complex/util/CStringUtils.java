package com.lowkey.complex.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yuanjifan
 * @description
 * @date 2022年07月14日 9:10
 */
public class CStringUtils extends StringUtils {
    /**
     * @param originalString 传入字符串
     * @return 替换后字符串
     * @author yuanjifan
     * @description 替换原字符串中的所有换行符
     * @date 2022/7/14 9:16
     */
    public static String replaceLineBreak(String originalString) {
        return originalString.replaceAll("(\\r\\n|\\n|\\\\n|\\s)", "");
    }
}
