package com.wojiushiwo.server.mq;

import com.wojiushiwo.server.dao.ProductInfoMapperTest;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by 我就是我
 * 2018/12/17 下午4:37
 */
public class MqSenderTest extends ProductInfoMapperTest {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send() {
        amqpTemplate.convertAndSend("myExchange","wojiushiwo", new Date());
    }
}
