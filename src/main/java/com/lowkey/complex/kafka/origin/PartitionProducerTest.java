package com.lowkey.complex.kafka.origin;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Collections;
import java.util.Scanner;

/**
 * @author lowkey
 * @description 测试自定义分区器
 * @date 2022年09月06日 17:51
 */
public class PartitionProducerTest {
    public static void main(String[] args) {
        // 获取生产者对象
        KafkaProducer<String, String> kafkaProducer = KafkaHelper.getKafkaProducerForString(
                //指定自定义分区器
                Collections.singletonMap(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.lowkey.complex.kafka.origin.CustomizePartition"));
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