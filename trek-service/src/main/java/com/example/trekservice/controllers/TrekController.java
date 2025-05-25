package com.example.trekservice.controllers;

import com.example.trekservice.entity.DTO.TrekCreatedEvent;
import com.example.trekservice.entity.DTO.TrekParticipantRequestDto;
import com.example.trekservice.entity.DTO.TrekRequestDto;
import com.example.trekservice.entity.DTO.TrekResponseDto;
import com.example.trekservice.entity.models.Treks;
import com.example.trekservice.services.TrekParticipantService;
import com.example.trekservice.services.TrekService;
import com.example.trekservice.services.impl.TrekEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/treks")
@RequiredArgsConstructor
public class TrekController {

    private final TrekService trekService;
    private  final TrekParticipantService trekParticipantService;
    @Autowired
    private TrekEventPublisher eventPublisher;

    @PostMapping
    public ResponseEntity<TrekResponseDto> createTrek(@RequestHeader("X-User-Email") String email,  @RequestBody TrekRequestDto trekRequestDto) {
        TrekResponseDto treks =  trekService.createTrek(trekRequestDto, email);
        trekParticipantService.joinTrek(new TrekParticipantRequestDto()
                .setTrekId(treks.getId())
                .setUserId(email)
                .setStatus("ACCEPTED"));
        eventPublisher.sendTrekCreatedEvent(new TrekCreatedEvent()
                .setTripId(treks.getId())
                .setCreatedBy(treks.getCreatedBy()));
        return ResponseEntity.status(HttpStatus.CREATED).body(treks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrekResponseDto> getTrekById(@PathVariable Long id) {
        try {
            TrekResponseDto trek = trekService.getTrekById(id);
            return ResponseEntity.ok(trek);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("creator/treks")
    public ResponseEntity<List<TrekResponseDto>> getCreatorTreks(@RequestHeader("X-User-Email") String email) {
        try{
            List<TrekResponseDto> treks = trekService.getCreatorTreks(email);
            return ResponseEntity.ok(treks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/get-all-treks")
    public ResponseEntity<List<TrekResponseDto>> getAllTreks() {
        List<TrekResponseDto> treks = trekService.getAllTreks();
        if (treks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(treks);
    }
    @GetMapping("/get-part-of-all-treks")
    public ResponseEntity<List<TrekResponseDto>> getPartOfAllTreks(@RequestParam int maxCount) {
        List<TrekResponseDto> treks = trekService.getTreksLimited(maxCount);
        if (treks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(treks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrek(@PathVariable Long id) {
        trekService.deleteTrek(id);
        return ResponseEntity.noContent().build();
    }


}
