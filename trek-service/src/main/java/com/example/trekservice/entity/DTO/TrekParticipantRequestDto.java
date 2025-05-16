package com.example.trekservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TrekParticipantRequestDto {
    private Long trekId;
    private int userId;
    private String status;
}
