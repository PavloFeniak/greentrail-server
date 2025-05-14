package com.example.mediaservice.repository;

import com.example.mediaservice.entity.Model.MediaFiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaFilesRepository extends JpaRepository<MediaFiles, Long> {
}
