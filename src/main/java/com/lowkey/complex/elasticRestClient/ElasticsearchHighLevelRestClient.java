package com.lowkey.complex.elasticRestClient;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.MainResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticsearchHighLevelRestClient {
    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchHighLevelRestClient.class);
    private static volatile RestHighLevelClient restHighLevelClient = null;
    private static final String ElasticsearchUrl = "192.168.126.133";
    //private static  HwRestClient hwRestClient = new HwRestClient();


    private ElasticsearchHighLevelRestClient() {

    }

    public static RestHighLevelClient getInstance() {
        if (restHighLevelClient == null) {
            synchronized (ElasticsearchHighLevelRestClient.class) {
                if (restHighLevelClient == null) {
                    RestClientBuilder restClientBuilder = RestClient.builder(
                            new HttpHost(ElasticsearchUrl, 9200, "http"));
                    restClientBuilder.setRequestConfigCallback(config -> config
                            //连接超时时间
                            .setConnectTimeout(120000)
                            //从连接池获取连接的超时时间
                            .setConnectionRequestTimeout(120000)
                            //响应超时时间
                            .setSocketTimeout(120000));
                    restClientBuilder.setHttpClientConfigCallback(config -> config
                            .setMaxConnTotal(100)
                            .setMaxConnPerRoute(20));
                    restHighLevelClient = new RestHighLevelClient(restClientBuilder);
                }
            }
        }
        return restHighLevelClient;    }


    /**
     * Get cluster information
     */
    public static void queryClusterInfo(RestHighLevelClient highLevelClient) {
        try {
            MainResponse response = highLevelClient.info(RequestOptions.DEFAULT);
            logger.info("\nElasticsearch信息：\n" +
                            "集群名称：{}\n" +
                            "集群版本：{}",
                    response.getClusterName(),
                    JSON.toJSONString(response.getVersion().getNumber()));
        } catch (Exception e) {
            logger.error("QueryClusterInfo is failed,exception occurred.", e);
        }
    }

    public static void main(String[] args) {
        RestHighLevelClient instance = ElasticsearchHighLevelRestClient.getInstance();
        queryClusterInfo(instance);
    }
}
