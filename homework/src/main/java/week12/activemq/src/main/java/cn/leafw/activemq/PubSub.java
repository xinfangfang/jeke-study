package com.example.test.controller.homework.src.main.java.week12.activemq.src.main.java.cn.leafw.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * pub-sub模式
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/7/18
 */
public class PubSub {

    public static void main(String[] args) {
//        pub();
        sub();
    }

    public static void pub() {
        // 定义JMS-ActiveMQ连接信息（默认为Openwire协议）
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.0.113:61617");
        Session session = null;
        Connection connection = null;
        MessageProducer producer = null;
        try {
            //1.获取一个MQ链接
            connection = connectionFactory.createConnection();
            //2.获取一个会话
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //3.获取目的地
            Topic topic = session.createTopic("mytopic");
            //4.获取消息生产者 此步骤会产生以下Topic或者Queue
            producer = session.createProducer(topic);
            // 设置消息传递模式为持久化模式  默认就是持久化
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            connection.start();
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("pubsub message");
            producer.send(textMessage);
            Thread.sleep(5000);
        } catch (InterruptedException | JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                if(producer!=null) {
                    producer.close();
                }
                if(session!=null) {
                    session.close();
                }
                if(connection!=null) {
                    connection.close();
                }
            } catch (JMSException   e) {
                e.printStackTrace();
            }
        }
    }

    public static void sub() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.0.113:61617");
        Session session = null;
        Connection connection = null;
        MessageConsumer consumer = null;
        try {
            connection = connectionFactory.createConnection();
            connection.setClientID("tnp");
            session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("mytopic");
            consumer = session.createDurableSubscriber(topic,"tnp");
            // 开启连接
            connection.start();
            // 设置消息监听器
            consumer.setMessageListener(message -> System.out.println("received message = " + message));
            // 设置异常监听器
            connection.setExceptionListener(e -> System.out.println("exception received message = " + e.getMessage()));
            connection.start();
            // 只是防止关闭太快这里先sleep一下
            Thread.sleep(10000);
        } catch (JMSException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if(consumer!=null) {
                    consumer.close();
                }
                if(session!=null) {
                    session.close();
                }
                if(connection!=null) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}

