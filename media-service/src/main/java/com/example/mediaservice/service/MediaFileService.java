package com.example.mediaservice.service;

import com.example.mediaservice.entity.DTO.MediaFileRequestDTO;
import com.example.mediaservice.entity.DTO.MediaFileResponseDTO;

import java.util.List;

public interface MediaFileService {
    String saveMediaFile(MediaFileRequestDTO requestDTO, String email);
    List<MediaFileResponseDTO> getAllMediaFiles();
    MediaFileResponseDTO getMediaFileById(Long id);
    void deleteMediaFile(Long id);
    List<MediaFileResponseDTO> getMediaFilesByRelated(String relatedType, Integer relatedId);
}
