package com.example.trekservice.services;

import com.example.trekservice.entity.DTO.TrekRequestDto;
import com.example.trekservice.entity.DTO.TrekResponseDto;

import java.util.List;

public interface TrekService {
    TrekResponseDto createTrek(TrekRequestDto dto);
    TrekResponseDto getTrekById(Long id);
    List<TrekResponseDto> getAllTreks();
    void deleteTrek(Long id);
}
