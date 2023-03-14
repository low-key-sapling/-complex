package com.lowkey.complex.kafka.origin;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @author yuanjifan
 * @description HASH分区器
 * @date 2022年09月08日 14:25
 */
public class HashPartition implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // 获取所有的分区数量
        Integer partition = cluster.partitionCountForTopic(topic);
        // 通过hashCode计算分区
        // 根据key进行hash取值，如果根据value把key换成value即可
        return key == null ? 0 : Math.abs(key.hashCode()) % partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
