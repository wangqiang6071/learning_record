package com.wayne.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wang qiang
 * @date 2022/10/21 0021 11:07
 * @Description: 消息接收者
 */
@Component
public class MultipleExchangeTopicCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("topic"),//临时队列
                    exchange = @Exchange(value = "exchange3",type = "topic"),
                    key = {"topic","topic.*"}
            )
    })
    public void receiveMassage1(String message){
        System.out.println("topic1==message==>"+message);
    }


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("topic"),//临时队列
                    exchange = @Exchange(value = "exchange3",type = "topic"),
                    key = {"topic.*"}
            )
    })
    public void receiveMassage2(String message){
        System.out.println("topic2==message==>"+message);
    }
}
