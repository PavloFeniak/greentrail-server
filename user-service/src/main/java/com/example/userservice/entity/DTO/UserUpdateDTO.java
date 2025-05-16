package com.example.userservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserUpdateDTO {
    private String name;
    private String phoneNumber;
    private String profilePicture;
    private String address;
}
