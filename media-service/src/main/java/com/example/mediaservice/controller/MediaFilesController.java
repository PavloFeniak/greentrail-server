package com.example.mediaservice.controller;

import com.example.mediaservice.entity.DTO.MediaFileRequestDTO;
import com.example.mediaservice.entity.DTO.MediaFileResponseDTO;
import com.example.mediaservice.service.MediaFileService;
import com.example.mediaservice.service.impl.AwsS3Service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaFilesController {
    private final MediaFileService mediaFileService;
    private final AwsS3Service awsS3Service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadMedia(@RequestHeader("X-User-Email") String email, @ModelAttribute MediaFileRequestDTO requestDTO) {
        String saved = mediaFileService.saveMediaFile(requestDTO, email);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MediaFileResponseDTO>> getAllMediaFiles() {
        return ResponseEntity.ok(mediaFileService.getAllMediaFiles());
    }
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String url = awsS3Service.uploadFile(file);
            return ResponseEntity.ok(Map.of(
                    "message", "File uploaded successfully",
                    "url", url
            ));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Upload failed"));
        }
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
