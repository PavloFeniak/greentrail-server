package com.example.userservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class UserParticipationDTO {
    private Long id;
    private Long userId;
    private int tripId;
    private LocalDateTime joinedAt;
    private String status;
}