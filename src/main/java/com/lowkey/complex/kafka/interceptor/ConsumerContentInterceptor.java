package com.lowkey.complex.kafka.interceptor;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author lowkey
 * @description
 * @date 2022年09月13日 16:13
 */
public class ConsumerContentInterceptor implements ConsumerInterceptor<String, String> {

    /**
     * @param consumerRecords 拦截到的消息对象
     * @return ConsumerRecords 返回处理了后的消息对象
     * @author lowkey
     * @description 消息从Kafka中取出，被发送给消费者应用程序之前调用，经过这个方法处理才会发送给消费者
     * @date 2022/9/14 15:43
     */
    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> consumerRecords) {
        // 对接收到消息进行处理，将时间戳转为年月日

        // 实例化一个map集合
        HashMap<TopicPartition, List<ConsumerRecord<String, String>>> consumerRecordHashMap = new HashMap<>();

        // 遍历拦截到的每一条消息
        Iterator<ConsumerRecord<String, String>> iterator = consumerRecords.iterator();
        if (iterator.hasNext()) {
            // 提取拦截到的一条消息
            ConsumerRecord<String, String> consumerRecord = iterator.next();
            // 消息主题
            String topic = consumerRecord.topic();
            // 消息分区
            int partition = consumerRecord.partition();
            // 对消息进行处理
            String format = Instant.ofEpochMilli(Long.parseLong(consumerRecord.key())).atZone(ZoneOffset.ofHours(8)).toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            // 创建TopicPartition对象，用来作为map集合的key
            TopicPartition topicPartition = new TopicPartition(topic, partition);
            // 将一个TopicPartition对象与修改后消息集合存入Map
            consumerRecordHashMap.put(topicPartition, Collections.singletonList(new ConsumerRecord<>(topic, partition, consumerRecord.offset(), format, consumerRecord.value())));
        }
        return new ConsumerRecords<>(consumerRecordHashMap);
    }

    /**
     * @param map 分区消息元数据信息
     * @author lowkey
     * @description offset提交之前的调用
     * @date 2022/9/14 15:48
     */
    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> map) {

    }

    @Override
    public void close() {

    }

    /**
     * @param map 配置信息
     * @author yuanjifan
     * @description 获取配置信息，初始化数据
     * @date 2022/9/13 16:22
     */
    @Override
    public void configure(Map<String, ?> map) {

    }
}