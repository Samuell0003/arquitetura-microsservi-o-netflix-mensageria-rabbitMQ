package com.iftm.newsletter.mensages;

import com.iftm.newsletter.models.dto.LogDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqSendLog {
    @Value("${newsLetter.rabbitmq.exchange}")
    private String exchange;

    @Value("${newsLetter.rabbitmq.rountingkey}")
    private String routingKey;

    @Value("${newsLetter.rabbitmq.queue}")
    private String queue;

    public final RabbitTemplate rabbitTemplate;
    @Autowired
    public RabbitMqSendLog(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendLog(LogDTO logDTO) {
        rabbitTemplate.execute(channel -> {
            channel.exchangeDeclare(exchange, "direct", true);
            channel.queueDeclare(queue, true, false, false, null);
            channel.queueBind(queue, exchange, routingKey);
            return null;
        });
        rabbitTemplate.convertAndSend(exchange, routingKey, logDTO);
    }

}
