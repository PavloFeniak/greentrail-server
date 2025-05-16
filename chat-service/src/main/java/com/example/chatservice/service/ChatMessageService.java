package com.example.chatservice.service;

import com.example.chatservice.entity.DTO.ChatMessageRequestDTO;
import com.example.chatservice.entity.DTO.ChatMessageResponseDTO;

import java.util.List;

public interface ChatMessageService {
    ChatMessageResponseDTO sendMessage(ChatMessageRequestDTO request);
    List<ChatMessageResponseDTO> getMessagesInChat(Long chatId);
}
