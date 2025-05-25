package com.example.mediaservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Data
@Accessors(chain = true)
public class MediaFileRequestDTO {
    private MultipartFile multipartFile;
    private String fileName;
    private String mimeType;
    private String relatedType;
    private Integer relatedId;
}
