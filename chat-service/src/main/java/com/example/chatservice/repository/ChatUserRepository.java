package com.example.chatservice.repository;

import com.example.chatservice.entity.model.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
    List<ChatUser> findByChatId(Long chatId);

    Optional<ChatUser> findByUserIdAndChatId(Long userId, Long chatId);
}