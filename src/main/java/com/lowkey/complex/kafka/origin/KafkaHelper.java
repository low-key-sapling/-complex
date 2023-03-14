package com.lowkey.complex.kafka.origin;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author lowkey
 * @description Kafka生产者与消费者对象构建工具
 * @date 2022年09月06日 16:30
 */
public class KafkaHelper {
    private static final String PATH_PRODUCER_CONFIG = "producer.properties";
    private static final String PATH_CONSUMER_CONFIG = "consumer.properties";

    /**
     * @author lowkey
     * @description 获取一个生产者，这个生产者具备通用的一些属性
     * @date 2022/9/6 16:44
     */
    public static KafkaProducer<String, String> getKafkaProducerForString() {
        // 读取默认配置文件
        Properties properties = loadKafkaProperties(PATH_PRODUCER_CONFIG);
        // 实例化生产者对象
        return new KafkaProducer<>(properties);
    }

    /**
     * @param config 配置生产者属性
     * @author lowkey
     * @description 获取一个生产者，这个生产者具备通用的一些属性，也可由调用者传递配置属性
     * @date 2022/9/6 16:44
     */
    public static KafkaProducer<String, String> getKafkaProducerForString(Map<String, String> config) {
        // 读取默认配置文件
        Properties properties = loadKafkaProperties(PATH_PRODUCER_CONFIG);
        // 个性化配置，使用调用方属性覆盖默认配置
        properties.putAll(config);
        // 实例化生产者对象
        return new KafkaProducer<>(properties);
    }

    /**
     * @author lowkey
     * @description 获取一个消费者，这个消费者具备通用的一些属性
     * @date 2022/9/6 16:44
     */
    public static KafkaConsumer<String, String> getKafkaConsumerForString() {
        // 读取默认配置文件
        Properties properties = loadKafkaProperties(PATH_CONSUMER_CONFIG);
        // 实例化生产者对象
        return new KafkaConsumer<>(properties);
    }

    /**
     * @param config 配置消费者属性
     * @author lowkey
     * @description 获取一个消费者，这个消费者具备通用的一些属性，也可由调用者传递配置属性
     * @date 2022/9/6 16:44
     */
    public static KafkaConsumer<String, String> getKafkaConsumerForString(Map<String, String> config) {
        // 读取默认配置文件
        Properties properties = loadKafkaProperties(PATH_CONSUMER_CONFIG);
        // 个性化配置，使用调用方属性覆盖默认配置
        properties.putAll(config);
        // 实例化生产者对象
        return new KafkaConsumer<>(properties);
    }

    /**
     * @param path 配置文件路径
     * @author lowkey
     * @description 加载Kafka配置文件
     * @date 2022/9/6 16:47
     */
    private static Properties loadKafkaProperties(String path) {
        Properties properties = new Properties();
        try {
            File file = new File("");
            String canonicalPath = file.getCanonicalPath();
            properties.load(new FileInputStream(canonicalPath + "\\src\\main\\resources\\" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
