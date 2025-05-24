package com.example.userservice.controller;

import com.example.userservice.entity.DTO.UserRequestDTO;
import com.example.userservice.entity.DTO.UserResponseDTO;
import com.example.userservice.entity.DTO.UserUpdateDTO;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/email")
    public UserResponseDTO getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        return userService.updateUser(id, userUpdateDTO);
    }

//    @PostMapping
//    public UserResponseDTO addUser(@RequestBody UserRequestDTO userResponseDTO) {
//        return userService.addUser(userResponseDTO);
//    }


}
