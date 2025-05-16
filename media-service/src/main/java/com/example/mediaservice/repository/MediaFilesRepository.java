package com.example.mediaservice.repository;

import com.example.mediaservice.entity.Model.MediaFiles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaFilesRepository extends JpaRepository<MediaFiles, Long> {
    List<MediaFiles> findByRelatedTypeAndRelatedId(String relatedType, Integer relatedId);
}
