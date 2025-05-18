package com.example.chatservice.controller;

import com.example.chatservice.entity.DTO.ChatMessageRequestDTO;
import com.example.chatservice.entity.DTO.ChatMessageResponseDTO;
import com.example.chatservice.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat-messages")
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody ChatMessageRequestDTO chatMessageRequestDTO) {
        try{
            ChatMessageResponseDTO chatMessageResponseDTO = chatMessageService.sendMessage(chatMessageRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(chatMessageResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<?> getMessagesInChat(@PathVariable Long chatId) {
        try {
            List<ChatMessageResponseDTO> messages = chatMessageService.getMessagesInChat(chatId);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

}
