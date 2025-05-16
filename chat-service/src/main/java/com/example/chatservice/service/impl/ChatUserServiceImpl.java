package com.example.chatservice.service.impl;

import com.example.chatservice.entity.DTO.ChatUserRequestDTO;
import com.example.chatservice.entity.DTO.ChatUserResponseDTO;
import com.example.chatservice.entity.model.Chat;
import com.example.chatservice.entity.model.ChatUser;
import com.example.chatservice.repository.ChatUserRepository;
import com.example.chatservice.repository.ChatsRepository;
import com.example.chatservice.service.ChatUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatUserServiceImpl implements ChatUserService {

    private final ChatUserRepository chatUserRepository;
    private final ChatsRepository chatsRepository;

    @Override
    public ChatUserResponseDTO addUserToChat(ChatUserRequestDTO request) {
        Chat chat = chatsRepository.findById(request.getChatId())
                .orElseThrow(() -> new RuntimeException("Chat not found"));
        ChatUser chatUser = new ChatUser()
                .setChat(chat)
                .setJoinedAt(LocalDateTime.now())
                .setUserId(request.getUserId());
        chatUser = chatUserRepository.save(chatUser);
        return mapToDTO(chatUser);
    }

    @Override
    public List<ChatUserResponseDTO> getUsersInChat(Long chatId) {
        List<ChatUser> chatUser = chatUserRepository.findByChatId(chatId);
        return chatUser.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void markUserLeft(Long userId, Long chatId) {
        ChatUser chatUser = chatUserRepository.findByUserIdAndChatId(userId, chatId)
                .orElseThrow(() -> new RuntimeException("Chat not found"));
        chatUserRepository.delete(chatUser);
        System.out.println("User " + userId + " left chat " + chatId);
    }

    private ChatUserResponseDTO mapToDTO(ChatUser chatUser) {
        return new ChatUserResponseDTO()
                .setId(chatUser.getId())
                .setUserId(chatUser.getUserId())
                .setJoinedAt(chatUser.getJoinedAt())
                .setLeftAt(chatUser.getLeftAt());
    }
}
