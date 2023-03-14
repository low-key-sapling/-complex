package com.lowkey.complex.kafka.origin;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;

import java.time.Duration;
import java.util.*;

/**
 * @author lowkey
 * @description 消费者消费消息
 * @date 2022年09月06日 15:14
 */
public class KafkaConsumerTest {
    public static void main(String[] args) {
        // 实例化一个Properties对象，作为Kafka构造入参
        Properties properties = new Properties();

        // 设置kafka brokerIP地址列表
        properties.setProperty("bootstrap.servers", "192.168.126.130:9092");
        // key反序列化
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // value反序列化
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // ConsumerConfig:将常用的属性以常量的形式定义好了，可以直接使用
        // 设置消费者组
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "api-group");
        // 是否设置自动提交
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Boolean.FALSE.toString());

        // 构建Consumer对象，指定消费消息的key和value类型，指定消费者属性
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        // 订阅主题
        kafkaConsumer.subscribe(Collections.singleton("kraft-test"));
        Map<String, List<PartitionInfo>> stringListMap = kafkaConsumer.listTopics();
        // 循环消费消息
        while (Boolean.TRUE) {
            // 从kafka中拉取消息,poll也是批量方法，poll->1s拉取一次
            ConsumerRecords<String, String> poll = kafkaConsumer.poll(Duration.ofSeconds(1));
            // 获取迭代器，遍历消息，进行下一步处理
            Iterator<ConsumerRecord<String, String>> iterator = poll.iterator();
            while (iterator.hasNext()) {
                ConsumerRecord<String, String> consumerRecords = iterator.next();
                String topic = consumerRecords.topic();
                int partition = consumerRecords.partition();
                String key = consumerRecords.key();
                String value = consumerRecords.value();
                long offset = consumerRecords.offset();
                System.out.printf("topic=%s,partition=%s,key=%s,value=%s,offset=%s", topic, partition, key, value, offset);
            }
        }
    }
}
