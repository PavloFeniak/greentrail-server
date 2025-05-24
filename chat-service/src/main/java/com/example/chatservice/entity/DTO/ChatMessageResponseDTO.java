package com.example.chatservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ChatMessageResponseDTO {
    private Long id;
    private String senderId;
    private String content;
    private LocalDateTime sentAt;
}