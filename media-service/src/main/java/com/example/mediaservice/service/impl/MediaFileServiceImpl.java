package com.example.mediaservice.service.impl;

import com.example.mediaservice.entity.DTO.MediaFileRequestDTO;
import com.example.mediaservice.entity.DTO.MediaFileResponseDTO;
import com.example.mediaservice.entity.Model.MediaFiles;
import com.example.mediaservice.repository.MediaFilesRepository;
import com.example.mediaservice.service.MediaFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaFileServiceImpl implements MediaFileService {

    private final MediaFilesRepository mediaFilesRepository;
    private  final AwsS3Service awsS3Service;

    @Override
    public MediaFileResponseDTO saveMediaFile(MediaFileRequestDTO requestDTO) {
        MediaFiles mediaFiles = new MediaFiles()
            .setFileName(requestDTO.getFileName())
            .setMimeType(requestDTO.getMimeType())
            .setUploadedBy(requestDTO.getUploadedBy())
            .setUploadedAt(LocalDateTime.now())
            .setRelatedType(requestDTO.getRelatedType())
            .setRelatedId(requestDTO.getRelatedId());

        try {
            String url = awsS3Service.uploadFile(requestDTO.getMultipartFile());
            mediaFiles.setUrl(url);
        } catch (IOException e) {
            throw new RuntimeException("Can`t upload file in S3 bucket", e);
        }
        mediaFiles = mediaFilesRepository.save(mediaFiles);
        return mapToResponse(mediaFiles);
    }

    @Override
    public List<MediaFileResponseDTO> getAllMediaFiles() {
        return mediaFilesRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public MediaFileResponseDTO getMediaFileById(Long id) {
        return mediaFilesRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    public void deleteMediaFile(Long id) {
        mediaFilesRepository.deleteById(id);
    }

    @Override
    public List<MediaFileResponseDTO> getMediaFilesByRelated(String relatedType, Integer relatedId) {
        return mediaFilesRepository.findByRelatedTypeAndRelatedId(relatedType, relatedId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private MediaFileResponseDTO mapToResponse(MediaFiles media) {
        MediaFileResponseDTO dto = new MediaFileResponseDTO();
        dto.setId(media.getId());
        dto.setUrl(media.getUrl());
        dto.setFileName(media.getFileName());
        dto.setMimeType(media.getMimeType());
        dto.setUploadedBy(media.getUploadedBy());
        dto.setUploadedAt(media.getUploadedAt());
        dto.setRelatedType(media.getRelatedType());
        dto.setRelatedId(media.getRelatedId());
        return dto;
    }
}
