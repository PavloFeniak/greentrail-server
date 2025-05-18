package com.example.userservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRequestDTO {
    private String email;
    private String phoneNumber;
    private String name;
    private String address;
}
