package com.lowkey.complex.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class KafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/kafka/test/{msg}")
    public void sendMessage(@PathVariable("msg") String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", msg);
        kafkaTemplate.send("test", JSON.toJSONString(jsonObject));
    }

    //同步发送
    @GetMapping("/kafka/sync/{msg}")
    public void sync(@PathVariable("msg") String msg) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", msg);
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test", JSON.toJSONString(jsonObject));
        //注意，可以设置等待时间，超出后，不再等候结果
        SendResult<String, Object> result = future.get(3, TimeUnit.SECONDS);
        logger.info("send result:{}", result.getProducerRecord().value());
    }
}
