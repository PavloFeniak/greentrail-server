package com.example.chatservice.service.impl;

import com.example.chatservice.entity.DTO.ChatRequestDTO;
import com.example.chatservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatEventListener {
    private final ChatService chatService;
    @RabbitListener(queues = "chat.created")
    public void handleChatCreated(ChatRequestDTO chatRequestDTO) {
        chatService.createChat(chatRequestDTO);
    }

}