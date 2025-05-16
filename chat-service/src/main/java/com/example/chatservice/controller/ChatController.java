package com.example.chatservice.controller;

import com.example.chatservice.entity.DTO.ChatRequestDTO;
import com.example.chatservice.entity.DTO.ChatResponseDTO;
import com.example.chatservice.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    @PostMapping
    public ResponseEntity<?> createChat(@Valid @RequestBody ChatRequestDTO chatRequestDTO) {
        try {
            ChatResponseDTO createdChat = chatService.createChat(chatRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdChat);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<?> getChatByTripId(@PathVariable Long tripId) {
        try {
            ChatResponseDTO chat = chatService.getChatByTripId(tripId);
            return ResponseEntity.ok(chat);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getChatById(@PathVariable Long id) {
        try {
            ChatResponseDTO chat = chatService.getChatById(id);
            return ResponseEntity.ok(chat);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<ChatResponseDTO>> getAllChats() {
        return ResponseEntity.ok(chatService.getAllChats());
    }
}
