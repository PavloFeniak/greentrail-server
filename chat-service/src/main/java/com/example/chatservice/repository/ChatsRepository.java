package com.example.chatservice.repository;

import com.example.chatservice.entity.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatsRepository extends JpaRepository<Chat, Long> {
}
