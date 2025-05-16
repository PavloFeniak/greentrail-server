package com.example.userservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserParticipationRequestDTO {
    private int tripId;
    private String status;
}