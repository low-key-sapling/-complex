package com.lowkey.complex.enums;

public enum ContentTypeEnum {
    APPLICATION_JSON("20001", "application/json;charset=UTF-8");
    private final String code;
    private final String value;

    ContentTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static ContentTypeEnum getContentTypeEnumByCode(String code) {
        for (ContentTypeEnum contentTypeEnum : ContentTypeEnum.values()) {
            if (contentTypeEnum.getCode().equals(code)) {
                return contentTypeEnum;
            }
        }
        return null;
    }
}
