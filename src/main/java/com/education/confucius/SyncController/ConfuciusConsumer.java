package com.education.confucius.SyncController;

import com.pangu.Http.response.RestResult;
import com.pangu.Mq.consumer.MqMessageListener;
import com.pangu.Mq.consumer.MqMessageListenerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2020-12-28 15:24
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@MqMessageListenerConfig(topic = "confucius", consumerGroup = "confucius_consumer")
@Component
public class ConfuciusConsumer implements MqMessageListener {
    @Override
    public RestResult exec(ConsumerRecord<String, String> record) {
        System.out.println("===confucius record:" + record.toString());
        return RestResult.successResult();
    }
}
