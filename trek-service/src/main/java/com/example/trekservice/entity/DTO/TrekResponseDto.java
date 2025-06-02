package com.example.trekservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Accessors(chain = true)
public class TrekResponseDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double startLatitude;
    private Double startLongitude;
    private Double endLatitude;
    private Double endLongitude;
    private String nearestTown;
    private String firstPhoto;
    private String secondPhoto;
    private String createdBy;
    private String previewImage;
    private LocalDateTime createdAt;
}