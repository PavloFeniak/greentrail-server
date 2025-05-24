package com.example.trekservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class TrekParticipantResponseDto {
    private Long id;
    private Long trekId;
    private String userId;
    private String status;
    private LocalDateTime joinedAt;
}