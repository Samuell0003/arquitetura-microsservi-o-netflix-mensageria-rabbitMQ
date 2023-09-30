package com.iftm.serverlogs.mensages;

import com.iftm.serverlogs.models.Logs;
import com.iftm.serverlogs.services.ServerLogsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqReadLog {
    @Autowired
    private ServerLogsService logsService;

    @RabbitListener(queues = "${newsLetter.rabbitmq.queue}")
    public void receiveLog(@Payload Logs logs) {
        if (logs != null)
            logsService.save(logs);
    }
}
