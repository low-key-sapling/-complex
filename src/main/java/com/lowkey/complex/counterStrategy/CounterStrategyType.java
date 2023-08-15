package com.lowkey.complex.counterStrategy;

/**
 * 计算器策略枚举
 */
public enum CounterStrategyType {
    ADD("add", "加法运算"),
    SUBTRACTION("subtraction", "减法运算");

    private final String code;
    private final String value;

    CounterStrategyType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static com.lowkey.complex.enums.ContentTypeEnum getContentTypeEnumByCode(String code) {
        for (com.lowkey.complex.enums.ContentTypeEnum contentTypeEnum : com.lowkey.complex.enums.ContentTypeEnum.values()) {
            if (contentTypeEnum.getCode().equals(code)) {
                return contentTypeEnum;
            }
        }
        return null;
    }
}
