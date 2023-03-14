package com.lowkey.complex.kafka.origin;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yuanjifan
 * @description 随机分区器
 * @date 2022年09月08日 14:25
 */
public class RoundRobinPartition implements Partitioner {
    // 用于统计累加的数量
    private final AtomicInteger count = new AtomicInteger();

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // 获取所有的分区数量
        Integer partition = cluster.partitionCountForTopic(topic);
        // 计算分区
        return count.getAndIncrement() % partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
