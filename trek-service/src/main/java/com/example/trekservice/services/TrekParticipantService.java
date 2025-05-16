package com.example.trekservice.services;

import com.example.trekservice.entity.DTO.TrekParticipantRequestDto;
import com.example.trekservice.entity.DTO.TrekParticipantResponseDto;

import java.util.List;

public interface TrekParticipantService {
    TrekParticipantResponseDto joinTrek(TrekParticipantRequestDto dto);
    List<TrekParticipantResponseDto> getParticipantsByTrekId(Long trekId);
    void updateStatus(Long participantId, String status); // approved / rejected
}
