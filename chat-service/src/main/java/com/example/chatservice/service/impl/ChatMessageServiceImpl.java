package com.example.chatservice.service.impl;

import com.example.chatservice.entity.DTO.ChatMessageRequestDTO;
import com.example.chatservice.entity.DTO.ChatMessageResponseDTO;
import com.example.chatservice.entity.model.Chat;
import com.example.chatservice.entity.model.ChatMessage;
import com.example.chatservice.repository.ChatMessageRepository;
import com.example.chatservice.repository.ChatUserRepository;
import com.example.chatservice.repository.ChatsRepository;
import com.example.chatservice.service.ChatMessageService;
import com.example.chatservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatsRepository chatsRepository;
    private final ChatUserRepository chatUserRepository;

    @Override
    public ChatMessageResponseDTO sendMessage(ChatMessageRequestDTO request) {
        Chat chat = chatsRepository.findById(request.getChatId())
                .orElseThrow(() -> new RuntimeException("chat not found"));
        ChatMessage chatMessage = new ChatMessage()
                .setChat(chat)
                .setSenderId(request.getSenderId())
                .setContent(request.getContent())
                .setSentAt(LocalDateTime.now());
        chatMessage = chatMessageRepository.save(chatMessage);
        return mapToDTO(chatMessage);
    }

    @Override
    public List<ChatMessageResponseDTO> getMessagesInChat(Long chatId) {
        List<ChatMessage> chatMessages = chatMessageRepository.findByChatId(chatId);
        return chatMessages.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private ChatMessageResponseDTO mapToDTO(ChatMessage chatMessage) {
        return new ChatMessageResponseDTO()
                .setId(chatMessage.getId())
                .setSenderId(chatMessage.getSenderId())
                .setContent(chatMessage.getContent())
                .setSentAt(chatMessage.getSentAt());
    }
}
