package com.lowkey.complex.kafka.interceptor;

import cn.hutool.core.exceptions.ExceptionUtil;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author lowkey
 * @description
 * @date 2022年09月13日 16:13
 */
public class ProducerContentInterceptor implements ProducerInterceptor<String, String> {
    /**
     * @param producerRecord 拦截到的消息对象
     * @return ProducerRecord 处理之后的消息对象
     * @author lowkey
     * @description 在消息被序列化之后，选择分区之前触发(但最好不要修改原来隶属的分区，否则会影响后续的处理)
     * @date 2022/9/13 16:16
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        // 获得消息的value
        String value = producerRecord.value();
        // 对value进行处理，给value添加后缀-From China
        value = value + "-From China";
        // 重新构建，添加key=时间戳
        return new ProducerRecord<>(producerRecord.topic(), producerRecord.partition(), String.valueOf(System.currentTimeMillis()), value);
    }

    /**
     * @param recordMetadata 消息的元数据信息
     * @param e              失败时的异常信息
     * @author yuanjifan
     * @description 消息发送成功或失败时候调用
     * 注意事项：这个方法的触发，与onSend的触发不在一个线程中，因此如果两个方法使用到了同一个资源，需要注意线程安全问题
     * @date 2022/9/13 16:18
     */
    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        // 打印异常
        if (e != null) {
            System.out.println("Kafka send error." + ExceptionUtil.stacktraceToString(e));
        }
        // 打印消息内容
        System.out.printf("topic=%s,partition=%s,offset=%s%n", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
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
