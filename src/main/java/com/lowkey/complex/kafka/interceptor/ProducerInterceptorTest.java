package com.lowkey.complex.kafka.interceptor;

import com.lowkey.complex.kafka.origin.KafkaHelper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author lowkey
 * @description
 * @date 2022年09月14日 15:04
 */
public class ProducerInterceptorTest {
    public static void main(String[] args) {
        // 获取生产者对象
        KafkaProducer<String, String> kafkaProducer = KafkaHelper.getKafkaProducerForString(
                new HashMap<String, String>() {{
                    //指定自定义分区器
                    put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.lowkey.complex.kafka.origin.CustomizePartition");
                    // 指定拦截器
                    put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, "com.lowkey.complex.kafka.interceptor.ProducerContentInterceptor");
                }});
        // 生产消息并发送到主题
        while (true) {
            // 从控制台读取需要生产的消息
            System.out.println("请输入需要发送的消息：");
            Scanner in = new Scanner(System.in);
            String next = in.next();
            // 整理并发送
            kafkaProducer.send(new ProducerRecord<>("topic-api", next));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}