package com.example.mediaservice.controller;

import com.example.mediaservice.entity.DTO.MediaFileRequestDTO;
import com.example.mediaservice.entity.DTO.MediaFileResponseDTO;
import com.example.mediaservice.service.MediaFileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaFilesController {
    private final MediaFileService mediaFileService;

    @PostMapping
    public ResponseEntity<MediaFileResponseDTO> uploadMedia(@Valid @RequestBody MediaFileRequestDTO requestDTO) {
        MediaFileResponseDTO saved = mediaFileService.saveMediaFile(requestDTO);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MediaFileResponseDTO>> getAllMediaFiles() {
        return ResponseEntity.ok(mediaFileService.getAllMediaFiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaFileResponseDTO> getMediaFileById(@PathVariable Long id) {
        MediaFileResponseDTO dto = mediaFileService.getMediaFileById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMediaFile(@PathVariable Long id) {
        mediaFileService.deleteMediaFile(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-related")
    public ResponseEntity<List<MediaFileResponseDTO>> getMediaFilesByRelated(
            @RequestParam String relatedType,
            @RequestParam Integer relatedId) {
        return ResponseEntity.ok(mediaFileService.getMediaFilesByRelated(relatedType, relatedId));
    }
}
