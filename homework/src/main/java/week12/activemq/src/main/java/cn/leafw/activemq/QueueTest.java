package com.example.test.controller.homework.src.main.java.week12.activemq.src.main.java.cn.leafw.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 基于Queue的测试
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/7/18
 */
public class QueueTest {
    public static void main(String[] args) {
        try {
            consume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void produce() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.0.113:61617");
        Session session;
        Queue sendQueue;
        Connection connection = null;
        MessageProducer sender = null;
        MessageConsumer consumer = null;

        try {
            // 进行连接
            connection = connectionFactory.createQueueConnection();
            connection.start();
            System.out.println("链接成功");

            // 建立会话（设置一个带有事务特性的会话）
            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            // 建立queue（当然如果有了就不会重复建立）
            sendQueue = session.createQueue("/test");
            // 建立消息发送者对象
            sender = session.createProducer(sendQueue);
            TextMessage outMessage = session.createTextMessage();
            outMessage.setText("这是发送的消息内容");

            // 发送（JMS是支持事务的）
            sender.send(outMessage);
            session.commit();

            // 建立消息消费者对象
            consumer = session.createConsumer(sendQueue);
            consumer.setMessageListener(message -> {
                // 接收到消息后，不需要再发送ack了。
                System.out.println("received Message = " + message);
            });
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭
                assert consumer != null;
                consumer.close();

                // 关闭
                sender.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }


    public static void consume() throws Exception{
        // 定义JMS-ActiveMQ连接信息
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.0.113:61617");
        Session session ;
        Destination sendQueue;
        Connection connection ;

        // 进行连接
        connection = connectionFactory.createQueueConnection();
        connection.start();

        // 建立会话(设置为自动ack)
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 建立Queue（当然如果有了就不会重复建立）
        sendQueue = session.createQueue("/test");
        // 建立消息消费者对象
        MessageConsumer consumer = session.createConsumer(sendQueue);
        consumer.setMessageListener(message -> {
            // 接收到消息后，不需要再发送ack了。
            System.out.println("Message = " + message);
        });

        Thread.sleep(5000);
        // 关闭
        consumer.close();
        connection.close();
    }

}

