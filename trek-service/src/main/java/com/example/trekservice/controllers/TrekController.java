package com.example.trekservice.controllers;

import com.example.trekservice.entity.DTO.TrekRequestDto;
import com.example.trekservice.entity.DTO.TrekResponseDto;
import com.example.trekservice.entity.models.Treks;
import com.example.trekservice.services.TrekService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public ResponseEntity<TrekResponseDto> createTrek(@RequestBody TrekRequestDto trekRequestDto) {
        TrekResponseDto treks =  trekService.createTrek(trekRequestDto);
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
    @GetMapping
    public ResponseEntity<List<TrekResponseDto>> getAllTreks() {
        List<TrekResponseDto> treks = trekService.getAllTreks();
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
