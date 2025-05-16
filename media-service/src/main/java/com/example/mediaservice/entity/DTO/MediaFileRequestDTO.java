package com.example.mediaservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MediaFileRequestDTO {
    private String url;
    private String fileName;
    private String mimeType;
    private Integer uploadedBy;
    private String relatedType;
    private Integer relatedId;
}
