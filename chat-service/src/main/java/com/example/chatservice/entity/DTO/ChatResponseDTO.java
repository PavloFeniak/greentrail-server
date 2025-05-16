package com.example.chatservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class ChatResponseDTO {
    private Long id;
    private Long tripId;
    private LocalDateTime createdAt;
    private List<ChatUserResponseDTO> users;
    private List<ChatMessageResponseDTO> messages;
}