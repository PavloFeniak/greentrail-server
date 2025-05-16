package com.example.authservice.entity.DTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}