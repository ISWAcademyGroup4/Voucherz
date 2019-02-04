package com.iswAcademy.Voucherz.audit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

//    @Autowired
//    private AmqpTemplate amqpTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${jsa.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsa.rabbitmq.routingkey}")
    private String routingKey;

//    public void sendMessage(String msg){
//        amqpTemplate.convertAndSend(exchange, routingKey, msg);
//        System.out.println("Send msg = " + msg);
//    }

    public void sendMessage(CustomMessage message){
//        amqpTemplate.convertAndSend(exchange, routingKey, message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        logger.info("Sending messages as specific class: {}", message);
        System.out.println("Send msg = " + message);
    }
}