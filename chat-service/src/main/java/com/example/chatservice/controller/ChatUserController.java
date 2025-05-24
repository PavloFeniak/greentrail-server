package com.example.chatservice.controller;

import com.example.chatservice.entity.DTO.ChatUserRequestDTO;
import com.example.chatservice.entity.DTO.ChatUserResponseDTO;
import com.example.chatservice.service.ChatUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-users")
public class ChatUserController {

    private final ChatUserService chatUserService;

    @PostMapping
    public ResponseEntity<?> addChatUser(@RequestBody ChatUserRequestDTO chatUser) {
        try {
            ChatUserResponseDTO chatUserResponseDTO = chatUserService.addUserToChat(chatUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(chatUserResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error: ",e.getMessage()));
        }
    }
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<?> getUsersInChat(@PathVariable Long chatId) {
        try {
            List<ChatUserResponseDTO> users = chatUserService.getUsersInChat(chatId);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/chat/{chatId}/user/{userId}")
    public ResponseEntity<?> markUserLeft(@PathVariable String userId, @PathVariable Long chatId) {
        try {
            chatUserService.markUserLeft(userId, chatId);
            return ResponseEntity.ok(Map.of("message", "User removed from chat"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }
}
