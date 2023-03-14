package com.lowkey.complex.util;

import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lowkey
 * @description
 * @date 2022年09月28日 11:21
 */
public class RequestHolder {
    public static void getRequest() {
        HttpServletRequest requestAttributes = (HttpServletRequest) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        String servletPath = requestAttributes.getServletPath();
        System.out.println(servletPath);
    }
}
