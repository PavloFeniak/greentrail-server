package com.example.mediaservice.service;

import com.example.mediaservice.entity.DTO.MediaFileRequestDTO;
import com.example.mediaservice.entity.DTO.MediaFileResponseDTO;
import com.example.mediaservice.entity.DTO.MultiPartMediaFilesRequestDTO;
import com.example.mediaservice.entity.DTO.MultipartMediaFileResponseDTO;

import java.util.List;

public interface MediaFileService {
    String saveMediaFile(MediaFileRequestDTO requestDTO, String email);
    MultipartMediaFileResponseDTO saveSeveralMediaFiles(MultiPartMediaFilesRequestDTO requestDTO, String email);
    List<MediaFileResponseDTO> getAllMediaFiles();
    MediaFileResponseDTO getMediaFileById(Long id);
    void deleteMediaFile(Long id);
    List<MediaFileResponseDTO> getMediaFilesByRelated(String relatedType, Integer relatedId);
}
