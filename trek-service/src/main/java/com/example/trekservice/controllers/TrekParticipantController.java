package com.example.trekservice.controllers;

import com.example.trekservice.entity.DTO.TrekParticipantRequestDto;
import com.example.trekservice.entity.DTO.TrekParticipantResponseDto;
import com.example.trekservice.services.TrekParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/treks/participants")
public class TrekParticipantController {

    private final TrekParticipantService trekParticipantService;

    @PostMapping
    public ResponseEntity<TrekParticipantResponseDto> joinTrek(@RequestBody TrekParticipantRequestDto dto) {
        TrekParticipantResponseDto response = trekParticipantService.joinTrek(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{trekId}")
    public ResponseEntity<List<TrekParticipantResponseDto>> getParticipants(@PathVariable Long trekId) {
        List<TrekParticipantResponseDto> participants = trekParticipantService.getParticipantsByTrekId(trekId);
        return ResponseEntity.ok(participants);
    }

    @PutMapping("/{participantId}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long participantId,
                                             @RequestParam String status) {
        trekParticipantService.updateStatus(participantId, status);
        return ResponseEntity.noContent().build();
    }
}
