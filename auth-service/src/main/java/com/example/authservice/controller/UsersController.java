package com.example.authservice.controller;

import com.example.authservice.entity.model.Users;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UsersController {
    @PostMapping
    public Users getUser(@RequestBody Users user) {
        return user;
    }
}
