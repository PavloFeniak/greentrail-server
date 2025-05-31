package com.example.mediaservice.entity.DTO;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Data
@Accessors(chain = true)
public class MultiPartMediaFilesRequestDTO {
    private MultipartFile original;
    private MultipartFile thumbnail;
    private String fileName;
    private String mimeType;
    private String relatedType;
    private Integer relatedId;
}
