package com.lowkey.complex.kafka.interceptor;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.lowkey.complex.kafka.origin.KafkaHelper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author lowkey
 * @description
 * @date 2022年09月14日 15:04
 */
public class ConsumerInterceptorTest {
    static {
        //设置main函数日志级别
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.INFO));
    }

    public static void main(String[] args) {
        // 消费者测试
        KafkaConsumer<String, String> kafkaConsumerForString = KafkaHelper.getKafkaConsumerForString(new HashMap<String, String>() {{
            // 指定拦截器
            put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, "com.lowkey.complex.kafka.interceptor.ConsumerContentInterceptor");
        }});
        // 订阅主题
        kafkaConsumerForString.subscribe(Collections.singleton("topic-api"));
        // 循环消费消息
        while (Boolean.TRUE) {
            // 从kafka中拉取消息,poll也是批量方法，poll->1s拉取一次
            ConsumerRecords<String, String> poll = kafkaConsumerForString.poll(Duration.ofSeconds(1));
            if (poll.isEmpty()) {
                continue;
            }
            // 获取迭代器，遍历消息，进行下一步处理
            Iterator<ConsumerRecord<String, String>> iterator = poll.iterator();
            while (iterator.hasNext()) {
                ConsumerRecord<String, String> consumerRecords = iterator.next();
                String topic = consumerRecords.topic();
                int partition = consumerRecords.partition();
                String key = consumerRecords.key();
                String value = consumerRecords.value();
                long offset = consumerRecords.offset();
                System.out.printf("topic=%s,partition=%s,key=%s,value=%s,offset=%s\n", topic, partition, key, value, offset);
            }
        }
    }
}