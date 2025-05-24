package com.example.mediaservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class MediaFileResponseDTO {
    private Long id;
    private String url;
    private String fileName;
    private String mimeType;
    private String uploadedBy;
    private LocalDateTime uploadedAt;
    private String relatedType;
    private Integer relatedId;
}
