package com.springmvc.util;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 功能概要：消息产生，提交到队列中去
 * @author liyu
 *
 */
public class Productor {
    private Logger logger=Logger.getLogger(Productor.class);
    private static final String MQCACHE="mqCache";
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private RedisUtils redisUtils;

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message,String exchange){
        try {

            CorrelationData correlationData=new CorrelationData(UUID.randomUUID().toString());
//            redisUtils.setToRedis(MQCACHE,correlationData.getId(),message);
            logger.info("=========================开始发送消息=========================");
            rabbitTemplate.convertAndSend(exchange,message);
            logger.info("=========================消息发送成功!!=========================");
            logger.info("=====================消息:"+message+";交换机:"+exchange);
        }catch (Exception e){
            logger.info("--------------------消息发送失败!!");
            e.printStackTrace();
        }
    }
}
