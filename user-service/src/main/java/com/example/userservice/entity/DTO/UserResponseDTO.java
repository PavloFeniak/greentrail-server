package com.example.userservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class UserResponseDTO {
    private Long id;
    private String email;
    private String phoneNumber;
    private String name;
    private String profilePicture;
    private String address;
    private LocalDateTime dateOfCreation;
}
