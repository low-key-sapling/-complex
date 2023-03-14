package com.lowkey.complex.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.Enumeration;

/**
 * @author yuanjifan
 * @description 系统相关操作工具类
 * @date 2022年01月21日 15:07
 */
@Slf4j
public class OSUtil {
    private static final String OS = System.getProperty("os.name").toLowerCase();

    /**
     * @return Windows系统返回：true；非Windows系统返回：false
     * @author yuanjifan
     * @description 判断系统操作系统是否为windows
     * @date 2022/1/21 15:11
     */
    public static boolean isWindows() {
        return OS.contains("windows");
    }

    /**
     * @return 当前主机IP地址
     * @author yuanjifan
     * @description 获取当前主机IP地址
     * @date 2022/1/21 15:30
     */
    public static String getLocalAddr() {
        Enumeration<NetworkInterface> networkInterfaces;
        InetAddress inetAddress;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
            inetAddress = InetAddress.getLocalHost();
        } catch (SocketException | UnknownHostException e) {
            log.error("get local addr error.", e);
            return "127.0.0.1";
        }

        //尝试直接获取非127.0.0.1IP地址
        String hostAddress = inetAddress.getHostAddress();
        if (StringUtils.isNotBlank(hostAddress) && !hostAddress.endsWith(".1")) {
            log.info("get local address:".concat(hostAddress));
            return hostAddress;
        }

        //inet6IP地址
        String hostAddress6 = "";

        //从网卡信息中依次判断获取IP地址
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                inetAddress = inetAddresses.nextElement();
                String tempHostAddress = inetAddress.getHostAddress();
                //排除以.1结尾的127.0.0.1和可能作为网关的地址以及网卡信息
                if (!tempHostAddress.endsWith(".1") && !tempHostAddress.contains("fe80") && !tempHostAddress.contains("::") && !tempHostAddress.contains("0:0:")) {
                    log.info("local address 00:".concat(tempHostAddress));
                    //inet4地址
                    if (inetAddress instanceof Inet4Address) {
                        log.info("local address inet4:".concat(tempHostAddress));
                        hostAddress = tempHostAddress;
                    }
                    //inet6地址
                    if (inetAddress instanceof Inet6Address) {
                        log.info("local address inet6:".concat(tempHostAddress));
                        hostAddress6 = tempHostAddress;
                    }
                }
            }
        }

        //如果hostAddress仍为127.0.0.1尝试替换为inet6地址
        if ("127.0.0.1".equals(hostAddress) && StringUtils.isNotBlank(hostAddress6)) {
            hostAddress = hostAddress6;
        }

        return hostAddress;
    }

    /**
     * @author yuanjifan
     * @description 获取请求客户端IP地址
     * @date 2022/1/24 14:07
     */
    public static String getClientAddr(HttpServletRequest request) {
        String addr = request.getHeader("X-Forwarded-For");

        if (StringUtils.isNotBlank(addr) && !"unKnown".equalsIgnoreCase(addr)) {
            return addr;
        }

        addr = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(addr) && !"unKnown".equalsIgnoreCase(addr)) {
            return addr;
        }

        addr = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(addr) && !"unKnown".equalsIgnoreCase(addr)) {
            //多次反向代理后会有多个IP值，第一个IP才是真实IP
            int addrIndex = addr.indexOf(",");
            if (addrIndex != -1) {
                return addr.substring(0, addrIndex);
            } else {
                return addr;
            }
        }

        return request.getRemoteAddr();
    }
}
