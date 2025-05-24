package com.example.chatservice.service.impl;

import com.example.chatservice.entity.DTO.ChatMessageResponseDTO;
import com.example.chatservice.entity.DTO.ChatRequestDTO;
import com.example.chatservice.entity.DTO.ChatResponseDTO;
import com.example.chatservice.entity.DTO.ChatUserRequestDTO;
import com.example.chatservice.entity.model.Chat;
import com.example.chatservice.repository.ChatMessageRepository;
import com.example.chatservice.repository.ChatUserRepository;
import com.example.chatservice.repository.ChatsRepository;
import com.example.chatservice.service.ChatMessageService;
import com.example.chatservice.service.ChatService;
import com.example.chatservice.service.ChatUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatsRepository chatsRepository;
    private final ChatUserService chatUserService;
    private final ChatMessageService chatMessageService;

    @Override
    public ChatResponseDTO createChat(ChatRequestDTO requestDTO) {
        Chat chat = new Chat().setTripId(requestDTO.getTripId());
        chat = chatsRepository.save(chat);
        chatUserService.addUserToChat(new ChatUserRequestDTO()
                .setChatId(chat.getId())
                .setUserId(requestDTO.getCreatedBy()));
        return mapToDTO(chat);
    }

    @Override
    public ChatResponseDTO getChatByTripId(Long tripId) {
        Chat chat = chatsRepository.findByTripId(tripId)
                .orElseThrow(() -> new RuntimeException("chat not found"));
        return mapToDTO(chat);
    }

    @Override
    public ChatResponseDTO getChatById(Long id) {
        Chat chat = chatsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("chat not found"));
        return mapToDTO(chat);
    }

    @Override
    public List<ChatResponseDTO> getAllChats() {
        return chatsRepository.findAll()
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ChatResponseDTO mapToDTO(Chat chat){
        return new ChatResponseDTO()
                .setTripId(chat.getTripId())
                .setId(chat.getId())
                .setCreatedAt(chat.getCreatedAt())
                .setUsers(chatUserService.getUsersInChat(chat.getId()))
                .setMessages(chatMessageService.getMessagesInChat(chat.getId()));
    }
}
