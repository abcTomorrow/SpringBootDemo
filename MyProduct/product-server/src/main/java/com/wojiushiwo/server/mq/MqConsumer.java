package com.wojiushiwo.server.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by 我就是我
 * 2018/12/17 下午4:35
 */
@Component
@Slf4j
public class MqConsumer {
//    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    @RabbitListener(bindings =@QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange"),
            key = "wojiushiwo"
    ))
    public void process(String message) {
        log.info("---------------myReceiver:{}----------------", message);
    }
}
