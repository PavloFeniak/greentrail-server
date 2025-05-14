package com.example.chatservice.controller;

import com.example.chatservice.entity.model.Chat;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @PostMapping
    public Chat getChat(@RequestBody Chat chat) {
        return chat;
    }
}
