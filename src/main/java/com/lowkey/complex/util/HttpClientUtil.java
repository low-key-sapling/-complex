package com.lowkey.complex.util;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuanjifan
 * @description HttpClient工具
 * @date 2023/2/17 17:21
 */
public class HttpClientUtil implements java.io.Serializable {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final long serialVersionUID = 5175284655195807603L;

    private static final int OK = 200; // OK: Success!

    private static final int NOT_MODIFIED = 304; // Not Modified

    private static final int BAD_REQUEST = 400; // Bad Request

    private static final int NOT_AUTHORIZED = 401; // Not Authorized

    private static final int FORBIDDEN = 403; // Forbidden

    private static final int NOT_FOUND = 404; // Not Found

    private static final int NOT_ACCEPTABLE = 406; // Not Acceptable

    private static final int INTERNAL_SERVER_ERROR = 500;// Internal Server

    // Error

    private static final int BAD_GATEWAY = 502;// Bad Gateway

    private static final int SERVICE_UNAVAILABLE = 503;// Service Unavailable

    private String token = "CommUtil.APP_KEY";

    private CloseableHttpClient client = null;

    public String setToken(String token) {
        this.token = token;
        return this.token;
    }

    public CloseableHttpClient getClient() {
        return client;
    }

    public HttpClientUtil() {
        this(1024 * 1024, 150, 60000, 60000);
    }

    /**
     * 构造httpclient
     *
     * @param maxTotal    最大连接数
     * @param maxPerRoute 单个路由最大连接数
     * @param conTimeOut  连接超时时间
     * @param soTimeOut   socket连接超时时间
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public HttpClientUtil(int maxTotal, int maxPerRoute, int conTimeOut,
                          int soTimeOut) {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(conTimeOut).setSocketTimeout(soTimeOut)
                .build();
        HttpClientBuilder builder = HttpClientBuilder.create();
        try {

            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).useTLS().build();
            SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            builder.setSSLSocketFactory(sslConnectionFactory);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("https", sslConnectionFactory)
                    .register("http", new PlainConnectionSocketFactory())
                    .build();
            client = builder.setMaxConnTotal(maxTotal)
                    .setMaxConnPerRoute(maxPerRoute)
                    .setDefaultRequestConfig(config)
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(3, false))
                    .build();

        } catch (Exception e) {
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", new PlainConnectionSocketFactory())
                    .build();
            HttpClientConnectionManager ccm = new PoolingHttpClientConnectionManager(registry);
            builder.setConnectionManager(ccm);
            client = builder.setMaxConnTotal(maxTotal)
                    .setMaxConnPerRoute(maxPerRoute)
                    .setDefaultRequestConfig(config)
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(3, false))
                    .build();
        }


    }


    /**
     * get 带请求参数
     *
     * @param url
     * @param params 请求参数
     * @param isToken 是否需要验证
     * @return json字符串
     * @throws Exception
     */


    /**
     * post
     *
     * @param url
     * @return json 字符串
     * @throws Exception
     */
    public String post(String url) throws Exception {
        return post(url, new HashMap<String, Object>(0), true, null);
    }

    /**
     * post
     *
     * @param url
     * @param params
     * @return json字符串
     * @throws Exception
     */
    public String post(String url, Map<String, Object> params) {
        try {
            return post(url, params, true, null);
        } catch (Exception ex) {
            return "";
        }

    }

    /**
     * post带参数请求
     *
     * @param url
     * @param params 请求参数
     * @return
     * @throws Exception
     */
    public String post(String url, Map<String, Object> params, String cookie) throws Exception {
        return post(url, params, true, cookie);

    }

    /**
     * post带请求参数
     *
     * @param url
     * @param params  请求参数
     * @param isToken 是否需要验证
     * @return json字符串
     * @throws Exception
     */
    public String post(String url, Map<String, Object> params, Boolean isToken, String cookie)
            throws Exception {
        HttpPost post = new HttpPost(url);
        List<NameValuePair> paramsValus = new ArrayList<NameValuePair>();


        if (null != params && params.size() > 0) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                paramsValus.add(new BasicNameValuePair(entry.getKey(),
                        (String) entry.getValue()));
            }

        }

        post.setEntity(new UrlEncodedFormEntity(paramsValus, "UTF-8"));
        return httpRequest(post, isToken, cookie);
    }

    /**
     * post 请求 携带json参数 并设置请求头
     *
     * @param url        请求地址
     * @param paramsJson 请求json对象参数
     * @return json字符串
     */
    public String post(String url, JSONObject paramsJson) throws Exception {
        return post(url, paramsJson, Maps.newHashMap());
    }

    /**
     * post 请求 携带json参数 并设置请求头
     *
     * @param url        请求地址
     * @param paramsJson 请求json对象参数
     * @param header     请求头
     * @return json字符串
     */
    public String post(String url, JSONObject paramsJson, Map<String, String> header) {
        HttpPost post = null;
        CloseableHttpResponse response = null;
        String message = "";
        try {
            post = new HttpPost(url);

            post.setHeader(HTTP.CONTENT_TYPE, "application/json");

            //设置自定义header参数
            if (header != null && header.size() > 0) {
                header.forEach(post::addHeader);
            }

            StringEntity stringEntity = new StringEntity(paramsJson.toString(), StandardCharsets.UTF_8);
            stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

            post.setEntity(stringEntity);

            response = client.execute(post);

            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (entity != null) {
                message = EntityUtils.toString(entity);
            }

            if (statusCode != OK && statusCode != 302) {
                logger.error("httpclient post error more info :\n" + message);
            }
        } catch (IOException e) {
            logger.error("http client post error.", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("response closed error.", e);
                }
            }
            if (post != null) {
                post.reset();
                post.releaseConnection();
            }
        }
        return message;
    }


    public String httpRequest(HttpRequestBase method, Boolean isToken, String cookie)
            throws Exception {
        String msg = null;
        InetAddress ipaddr;
        method.setHeader(HttpHeaders.CONNECTION, HTTP.CONN_CLOSE);
        try {
            // ipaddr = InetAddress.getLocalHost();
            if (isToken) {
                if (token == null) {
                    throw new IllegalStateException("token is not set!");
                }
                method.addHeader(new BasicHeader("app_key", token));


            }
            if (StringUtils.isNotBlank(cookie)) {
                method.addHeader(new BasicHeader("Cookie", cookie));
            }
            method.addHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36"));

            CloseableHttpResponse resp = client.execute(method);
            try {
                HttpEntity entity = resp.getEntity();
                int statusCode = resp.getStatusLine().getStatusCode();
                if (entity != null) {
                    msg = EntityUtils.toString(entity);
                }
                if (statusCode != OK && statusCode != 302) {
                    throw new RuntimeException(getCause(statusCode)
                            + "more info:\n" + msg);
                }
            } finally {
                resp.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            method.reset();
            method.releaseConnection();
        }
        return msg;

    }

    /**
     * 下载远程地址文件
     *
     * @param url        http://www.naidu.com
     * @param targetPath //保存的目标路径
     * @param filename   //文件名
     * @return boolean
     */
    public boolean down(String url, String targetPath, String filename) {
        HttpGet method = new HttpGet(url);
        byte[] bytes = new byte[1024];
        FileOutputStream out = null;
        try {
            CloseableHttpResponse resp = client.execute(method);

            try {
                int statusCode = resp.getStatusLine().getStatusCode();
                if (statusCode == OK) {
                    HttpEntity entity = resp.getEntity();
                    if (entity != null) {
                        bytes = EntityUtils.toByteArray(entity);
                        File targetFile = new File(targetPath);
                        if (!targetFile.exists()) FileUtil.file(targetPath);
                        out = new FileOutputStream(targetPath + File.separator
                                + filename);
                        out.write(bytes);
                        return true;
                    }
                }
            } finally {
                if (out != null) {
                    out.close();
                }
                resp.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            method.reset();
            method.releaseConnection();
        }
        return false;

    }

    private static String getCause(int statusCode) {
        String cause = null;
        switch (statusCode) {
            case NOT_MODIFIED:
                break;
            case BAD_REQUEST:
                cause = "The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.";
                break;
            case NOT_AUTHORIZED:
                cause = "Authentication credentials were missing or incorrect.";
                break;
            case FORBIDDEN:
                cause = "The request is understood, but it has been refused.  An accompanying error message will explain why.";
                break;
            case NOT_FOUND:
                cause = "The URI requested is invalid or the resource requested, such as a user, does not exists.";
                break;
            case NOT_ACCEPTABLE:
                cause = "Returned by the Search API when an invalid format is specified in the request.";
                break;
            case INTERNAL_SERVER_ERROR:
                cause = "Something is broken.  Please post to the group so the our team can investigate.";
                break;
            case BAD_GATEWAY:
                cause = "API is down or being upgraded.";
                break;
            case SERVICE_UNAVAILABLE:
                cause = "Service Unavailable: The servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.";
                break;
            default:
                cause = "";
        }
        return statusCode + ":" + cause;
    }

    public static void main(String[] args) {
        HttpClientUtil zf = new HttpClientUtil();
        String cookieString = "JSESSIONID=EDC90468DCEBFC531319CD64C5AFC598";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "name");
        String json = zf.post("http://172.168.2.214:8888/core/account/manage_index?_=1523283049404", params);

        System.out.println(json);

    }

}
