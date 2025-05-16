package com.example.userservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserResponseDTO {
    private Long id;
    private String email;
    private String phoneNumber;
    private String name;
    private String profilePicture;
    private String address;
}
