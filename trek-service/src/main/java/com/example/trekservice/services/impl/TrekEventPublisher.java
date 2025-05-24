package com.example.trekservice.services.impl;

import com.example.trekservice.entity.DTO.TrekCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrekEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void sendTrekCreatedEvent(TrekCreatedEvent event) {
        rabbitTemplate.convertAndSend("chat.created", event);
    }

}
