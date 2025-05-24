package com.example.chatservice.entity.DTO;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChatUserRequestDTO {
    private Long chatId;
    private String userId;
}