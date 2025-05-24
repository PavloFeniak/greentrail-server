package com.example.chatservice.service;

import com.example.chatservice.entity.DTO.ChatUserRequestDTO;
import com.example.chatservice.entity.DTO.ChatUserResponseDTO;

import java.util.List;

public interface ChatUserService {
    ChatUserResponseDTO addUserToChat(ChatUserRequestDTO request);
    List<ChatUserResponseDTO> getUsersInChat(Long chatId);
    void markUserLeft(String userId, Long chatId);
}