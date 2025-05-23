package com.example.authservice.entity.DTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
    private String phoneNumber;
    private String name;
    private String address;
}