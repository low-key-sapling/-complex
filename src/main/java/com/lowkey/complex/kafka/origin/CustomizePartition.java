package com.lowkey.complex.kafka.origin;


import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @author lowkey
 * @description 自定义分区器
 * @date 2022年09月06日 17:38
 */
public class CustomizePartition implements Partitioner {

    /**
     * @param topic      topic：生产者生产消息需要保存的主题
     * @param key        key：消息的key
     * @param keyBytes   keyBytes：消息key序列化之后的字节序列
     * @param value      value：消息的value
     * @param valueBytes valueBytes：消息value序列化之后的字节序列
     * @param cluster    集群信息
     * @return int 返回分区
     * @author lowkey
     * @description 分区器中最核心方法，由这个方法决定一条消息属于哪一个分区
     * @date 2022/9/6 17:40
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // 实现最简单的分区器
        // 消息的value大写开头存入0分区
        // 消息的value小写开头存入1分区
        // 否则存入2分区
        String valueString = value.toString();
        char charAt = valueString.charAt(0);
        if (Character.isUpperCase(charAt)) {
            return 0;
        } else if (Character.isLowerCase(charAt)) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
