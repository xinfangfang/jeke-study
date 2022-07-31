package com.example.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqHomework {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建链接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置RabbitMQ服务主机地址，默认localhost
        connectionFactory.setHost("localhost");
        //设置端口，默认5672
        connectionFactory.setPort(5672);
        //设置虚拟主机名，默认/
        connectionFactory.setVirtualHost("/mytest");
        //设置用户名密码
        connectionFactory.setUsername("test");
        connectionFactory.setPassword("123456");

        //创建链接
        Connection connection = connectionFactory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列
        //参数1：消息队列名称
        //参数2，是否持久化
        //参数3，是否独占本次connection链接
        //参数4，是否自动删除消息
        //参数5，是否需要传递额外的队列数据Map
        channel.queueDeclare("simple_queue1",true,false,false,null);
        //创建消息
        String msg = "hello";
        //发布消息
        //参数1，使用指定的交换机，不设置则使用默认交换机
        //参数2，指定路由器key，如果是简单模式直接给队列名就行
        //参数3，发生消息时是否需要额外的消息数据
        //参数4，消息内容
        channel.basicPublish("","simple_queue1",null,msg.getBytes());
        //关闭资源
        channel.close();
        connection.close();
    }
}