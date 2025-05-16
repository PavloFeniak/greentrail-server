package com.example.chatservice.service;

import com.example.chatservice.entity.DTO.ChatRequestDTO;
import com.example.chatservice.entity.DTO.ChatResponseDTO;

import java.util.List;

public interface ChatService {
    ChatResponseDTO createChat(ChatRequestDTO requestDTO);
    ChatResponseDTO getChatByTripId(Long tripId);
    ChatResponseDTO getChatById(Long id);
    List<ChatResponseDTO> getAllChats();
}