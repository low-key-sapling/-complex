package com.lowkey.complex.response;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author admin
 * @description: 统一定义接口返回结果
 * @date 2020/3/3014:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultEntity<T> {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    //响应结果：SUCCESS：成功；FAILED：失败
    private String result;
    //响应状态码：200：正常；500：错误
    private String status;
    //提示信息
    private String message;
    //响应数据
    private T data;

    public static <Type> ResultEntity<Type> successWithData(Type data) {
        return new ResultEntity<Type>(SUCCESS, "200", "成功", data);
    }

    public static <Type> ResultEntity<Type> successWithoutData() {
        return new ResultEntity<Type>(SUCCESS, "200", "成功", null);
    }

    public static <Type> ResultEntity<Type> failedWithoutData(String message) {
        return new ResultEntity<Type>(FAILED, "500", message, null);
    }

    public boolean isSuccess() {
        return SUCCESS.equals(this.result);
    }

    /**
     * 设置响应
     *
     * @param response    HttpServletResponse
     * @param contentType content-type
     * @param status      http状态码
     * @param value       响应内容
     * @throws IOException IOException
     */
    public static void makeResponse(HttpServletResponse response, String contentType,
                                    int status, Object value) throws IOException {
        response.setContentType(contentType);
        response.setStatus(status);
        response.getOutputStream()
                .write(JSON.toJSONString(value).getBytes());
    }
}
