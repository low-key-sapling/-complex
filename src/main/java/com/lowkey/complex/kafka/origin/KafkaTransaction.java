package com.lowkey.complex.kafka.origin;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.util.HashMap;

/**
 * @author lowkey
 * @description
 * @date 2022年09月17日 16:11
 */
public class KafkaTransaction {
    public static void main(String[] args) {
        // 获取生产者对象
        KafkaProducer<String, String> kafkaProducer = KafkaHelper.getKafkaProducerForString(
                new HashMap<String, String>() {{
                    // 设置事务ID
                    put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, System.currentTimeMillis() + "_id");
                    // 开启幂等性
                    put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, Boolean.TRUE.toString());
                }}
        );
        // 初始化事务
        kafkaProducer.initTransactions();
        try {
            // 开始事务
            kafkaProducer.beginTransaction();
            for (int i = 0; i < 100; i++) {
                kafkaProducer.send(new ProducerRecord<>("topic-api", "key:" + i, "value:" + i));
            }
            // 提交事务
            kafkaProducer.commitTransaction();
        } catch (ProducerFencedException e) {
            // 出现异常，回滚事务
            kafkaProducer.abortTransaction();
            e.printStackTrace();
        }
    }
}