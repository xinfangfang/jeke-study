package com.example.test.controller.homework.src.main.java.week11.pubsub.src.main.java.org.redis.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.logging.Logger;

public class MyListener implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(MyListener.class);


    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();
        logger.info("接收消息: " +new String(body));
    }
}
