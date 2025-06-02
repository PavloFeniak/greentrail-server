package com.example.trekservice.services.impl;

import com.example.trekservice.entity.DTO.TrekRequestDto;
import com.example.trekservice.entity.DTO.TrekResponseDto;
import com.example.trekservice.entity.models.Treks;
import com.example.trekservice.repository.TreksRepository;
import com.example.trekservice.services.TrekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrekServiceImpl implements TrekService {

    private final TreksRepository treksRepository;

    @Override
    public TrekResponseDto createTrek(TrekRequestDto dto, String email) {
        Treks treks = new Treks()
                .setTitle(dto.getTitle())
                .setDescription(dto.getDescription())
                .setStartDate(dto.getStartDate())
                .setEndDate(dto.getEndDate())
                .setStartLatitude(dto.getStartLatitude())
                .setStartLongitude(dto.getStartLongitude())
                .setEndLatitude(dto.getEndLatitude())
                .setEndLongitude(dto.getEndLongitude())
                .setNearestTown(dto.getNearestTown())
                .setFirstPhoto(dto.getFirstPhoto())
                .setSecondPhoto(dto.getSecondPhoto())
                .setPreviewImage(dto.getPreviewImage())
                .setCreatedBy(email)
                .setCreatedAt(LocalDateTime.now());
        treks = treksRepository.save(treks);
        return mapToDto(treks);
    }

    @Override
    public TrekResponseDto getTrekById(Long id) {
        Treks treks = treksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trek not found"));
        return mapToDto(treks);
    }

    @Override
    public List<TrekResponseDto> getAllTreks() {
        return treksRepository.findAll()
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTrek(Long id) {
        if(treksRepository.findById(id).isPresent()){
            treksRepository.deleteById(id);
        }
        else throw new RuntimeException("Trek not found");
    }

    @Override
    public List<TrekResponseDto> getCreatorTreks(String id) {
        List<Treks> trekResponseDtos = treksRepository.findByCreatedBy(id);
        return trekResponseDtos.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrekResponseDto> getTreksLimited(int maxCount) {
        List<Treks> treks = treksRepository.findTopN(maxCount);
        return treks.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private TrekResponseDto mapToDto(Treks treks){
        return new TrekResponseDto()
                .setId(treks.getId())
                .setTitle(treks.getTitle())
                .setDescription(treks.getDescription())
                .setStartDate(treks.getStartDate())
                .setEndDate(treks.getEndDate())
                .setStartLatitude(treks.getStartLatitude())
                .setStartLongitude(treks.getStartLongitude())
                .setEndLatitude(treks.getEndLatitude())
                .setEndLongitude(treks.getEndLongitude())
                .setNearestTown(treks.getNearestTown())
                .setFirstPhoto(treks.getFirstPhoto())
                .setSecondPhoto(treks.getSecondPhoto())
                .setPreviewImage(treks.getPreviewImage())
                .setCreatedBy(treks.getCreatedBy())
                .setCreatedAt(treks.getCreatedAt());
    }
     
}
