package com.wojiushiwo.server.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by 我就是我
 * 2018/12/17 下午4:30
 */
@Component
@Slf4j
public class MqSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
//        amqpTemplate.convertAndSend("myQueue", new Date());
        amqpTemplate.convertAndSend("myQueue","wojiushiwo",new Date()+"-----------------------------------");
    }
}
