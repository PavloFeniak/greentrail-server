package com.example.trekservice.services.impl;

import com.example.trekservice.entity.DTO.TrekParticipantRequestDto;
import com.example.trekservice.entity.DTO.TrekParticipantResponseDto;
import com.example.trekservice.entity.models.TrekParticipants;
import com.example.trekservice.entity.models.Treks;
import com.example.trekservice.repository.TreksParticipantRepository;
import com.example.trekservice.repository.TreksRepository;
import com.example.trekservice.services.TrekParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrekParticipantServiceImpl implements TrekParticipantService {

    private final TreksRepository treksRepository;
    private final TreksParticipantRepository treksParticipantRepository;

    @Override
    public TrekParticipantResponseDto joinTrek(TrekParticipantRequestDto dto) {
        Treks trek = treksRepository.findById(dto.getTrekId())
                .orElseThrow(() -> new RuntimeException("Trek not found"));
        TrekParticipants participant = new TrekParticipants()
                .setTrek(trek)
                .setUserId(dto.getUserId())
                .setStatus(dto.getStatus() != null ? dto.getStatus() : "pending");
        participant = treksParticipantRepository.save(participant);
        return mapToDto(participant);
    }

    @Override
    public List<TrekParticipantResponseDto> getParticipantsByTrekId(Long trekId) {
        List<TrekParticipants> trekParticipants = treksParticipantRepository.findByTrekId(trekId);
        return trekParticipants.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(Long participantId, String status) {
        TrekParticipants trekParticipants = treksParticipantRepository.findById(participantId)
                .orElseThrow(() -> new RuntimeException("Trek participant not found"));
        trekParticipants.setStatus(status);
        treksParticipantRepository.save(trekParticipants);
    }
    private TrekParticipantResponseDto mapToDto(TrekParticipants p) {
        TrekParticipantResponseDto dto = new TrekParticipantResponseDto();
        dto.setId(p.getId());
        dto.setTrekId(p.getTrek().getId());
        dto.setUserId(p.getUserId());
        dto.setStatus(p.getStatus());
        dto.setJoinedAt(p.getJoinedAt());
        return dto;
    }
}
