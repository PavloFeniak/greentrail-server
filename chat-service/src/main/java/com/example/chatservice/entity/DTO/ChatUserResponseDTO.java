package com.example.chatservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ChatUserResponseDTO {
    private Long id;
    private String userId;
    private LocalDateTime joinedAt;
    private LocalDateTime leftAt;
}