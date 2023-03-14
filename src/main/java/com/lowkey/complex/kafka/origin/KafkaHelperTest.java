package com.lowkey.complex.kafka.origin;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Collections;
import java.util.Iterator;

/**
 * @author lowkey
 * @description 测试生产者
 * @date 2022年09月06日 17:05
 */
public class KafkaHelperTest {
    public static void main(String[] args) {
        // 生产者测试
        KafkaProducer<String, String> kafkaProducerForString = KafkaHelper.getKafkaProducerForString();
        kafkaProducerForString.send(new ProducerRecord<>("topic-api", "this is a KafkaHelper message."));
        kafkaProducerForString.close();
        // 消费者测试
        KafkaConsumer<String, String> kafkaConsumerForString = KafkaHelper.getKafkaConsumerForString();
        // 订阅主题
        kafkaConsumerForString.subscribe(Collections.singleton("topic-api"));
        // 循环消费消息
        while (Boolean.TRUE) {
            // 从kafka中拉取消息,poll也是批量方法，poll->1s拉取一次
            ConsumerRecords<String, String> poll = kafkaConsumerForString.poll(Duration.ofSeconds(1));
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
