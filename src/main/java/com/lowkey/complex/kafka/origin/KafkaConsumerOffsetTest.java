package com.lowkey.complex.kafka.origin;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

/**
 * @author lowkey
 * @description 消费者消费消息
 * @date 2022年09月06日 15:14
 */
public class KafkaConsumerOffsetTest {
    public static void main(String[] args) {
        // 实例化一个Properties对象，作为Kafka构造入参
        Properties properties = new Properties();

        // 设置kafka brokerIP地址列表
        properties.setProperty("bootstrap.servers", "192.168.85.130:9092,192.168.85.131:9092,192.168.85.132:9092");
        // key反序列化
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // value反序列化
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // ConsumerConfig:将常用的属性以常量的形式定义好了，可以直接使用
        // 设置消费者组
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "api-group");

        // 设置自动提交offset的周期，单位：毫秒
        // properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1");

        // 设置手动提交offset：修改enable.auto.commit为false
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Boolean.FALSE.toString());
        // 设置消费位置:earliest,latest,none
        // 当没有初始的offset->消费者组中的第一个消费者消费数据,或者记录的offset已经不存在了(消息已经过期清除了)，设置应该从什么位置开始
        // 误区：如果设置earliest，并不是每一次都从头开始,在各分区下有提交的offset时：从offset处开始消费,在各分区下无提交的offset时：从头开始消费
        // latest->在各分区下有提交的offset时：从offset处开始消费,在各分区下无提交的offset时：从最新的数据开始消费
        // none->如果没有为消费者找到先前的offset的值,即没有自动维护偏移量,也没有手动维护偏移量,则抛出异常
        // properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // 构建Consumer对象，指定消费消息的key和value类型，指定消费者属性
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        // 指定消费位置，使用assign
        // 指定需要消费主题及消费分区
        kafkaConsumer.assign(Arrays.asList(
                new TopicPartition("topic", 0),
                new TopicPartition("topic", 1),
                new TopicPartition("topic", 2)
        ));
        // 设置指定分区消费位置
        kafkaConsumer.seek(new TopicPartition("topic-api", 0), 3);
        kafkaConsumer.seek(new TopicPartition("topic-api", 1), 7);
        kafkaConsumer.seek(new TopicPartition("topic-api", 2), 13);

        // 设置从头开始消费
        // 当然可以这样设置,设置offset为0：kafkaConsumer.seek(new TopicPartition("topic-api", 0), 0);
        // kafka提供了API
//        kafkaConsumer.seekToBeginning((Arrays.asList(
//                new TopicPartition("topic", 0),
//                new TopicPartition("topic", 1),
//                new TopicPartition("topic", 2)
//        )));

        // 设置从最新位置消费
//        kafkaConsumer.seekToEnd((Arrays.asList(
//                new TopicPartition("topic", 0),
//                new TopicPartition("topic", 1),
//                new TopicPartition("topic", 2)
//        )));

        // 订阅主题
        kafkaConsumer.subscribe(Collections.singleton("topic-api"));

        // 循环消费消息
        while (Boolean.TRUE) {
            // 从kafka中拉取消息,poll也是批量方法，poll->1s拉取一次
            ConsumerRecords<String, String> poll = kafkaConsumer.poll(Duration.ofSeconds(1));
            // 获取迭代器，遍历消息，进行下一步处理
            for (ConsumerRecord<String, String> consumerRecords : poll) {
                String topic = consumerRecords.topic();
                int partition = consumerRecords.partition();
                String key = consumerRecords.key();
                String value = consumerRecords.value();
                long offset = consumerRecords.offset();
                System.out.printf("topic=%s,partition=%s,key=%s,value=%s,offset=%s", topic, partition, key, value, offset);
            }
            // 同步：阻塞当前线程，直至提交成功
            // kafkaConsumer.commitSync();
            // 异步提交：但是也不知道是否提交成功，可以使用callBack进行后续处理
            kafkaConsumer.commitAsync((completeData, exception) -> {
                if (exception == null) {
                    System.out.println("commit success." + completeData.toString());
                } else {
                    System.out.println("commit error." + exception.getMessage());
                }
            });
        }
    }
}