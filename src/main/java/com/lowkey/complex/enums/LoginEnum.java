package com.lowkey.complex.enums;

public enum LoginEnum {
    LOGIN_SUCCESS(10001, "登录成功"),
    LOGIN_FAILED(10002, "登录失败"),
    LOGIN_NOT(10003, "请用户先登录"),
    LOGIN_USER_NAME_NOT_FOUNT(10004, "请输入登录用户名"),
    LOGIN_LOCKED_NOT(10005, "登录用户未被锁定"),
    LOGIN_LOCKED(10006, "登录用户已被锁定"),
    ACCOUNT_EXPIRED(10007, "登录失败,账号过期"),
    ACCOUNT_PASSWORD_ERROR(10008, "登录失败,账号或密码错误"),
    ACCOUNT_PASSWORD_EXPIRED(10009, "登录失败,账号密码过期"),
    ACCOUNT_PASSWORD_DISABLE(10010, "登录失败,账号已被禁用"),
    LOGIN_FAILED_UNKNOWN(10011, "登录失败,未知错误,请联系管理员"),
    LOGIN_OUT_SUCCESS(10012, "登出成功"),
    LOGIN_SESSION_EXPIRED(10013, "SESSION过期,请重新登录"),
    ACCOUNT_LIMIT(10014, "账号已超出登录限制,请稍后重新登录"),
    ACCOUNT_NO_AUTH(10015, "账号暂无权限访问");
    private final int code;

    private final String value;

    LoginEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static LoginEnum getContentTypeEnumByCode(int code) {
        for (LoginEnum contentTypeEnum : LoginEnum.values()) {
            if (contentTypeEnum.getCode() == code) {
                return contentTypeEnum;
            }
        }
        return null;
    }
}
