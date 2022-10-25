package com.wayne.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @author wang qiang
 * @date 2022/10/21 0021 11:10
 * @Description: 消息创建者
 */
@Component
public class MultipleExchangeTopicProducer {
    @Resource
    private RabbitTemplate template;

    public void sendTopicMassage(){
        template.convertAndSend("exchange3","topic.save","Topic模型消息!!");
    }
}
