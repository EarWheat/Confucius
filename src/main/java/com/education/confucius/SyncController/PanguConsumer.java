package com.education.confucius.SyncController;

import com.pangu.Http.response.RestResult;
import com.pangu.Mq.consumer.MqMessageListener;
import com.pangu.Mq.consumer.MqMessageListenerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2020-12-28 17:24
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
//@MqMessageListenerConfig(topic = "pangu", consumerGroup = "confucius_consumer")
//public class PanguConsumer implements MqMessageListener {
//    @Override
//    public RestResult exec(ConsumerRecord<String, String> record) {
//        System.out.println("===confucius record:" + record.toString());
//        return RestResult.successResult();
//    }
//}
