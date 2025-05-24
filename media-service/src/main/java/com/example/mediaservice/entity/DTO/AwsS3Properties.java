package com.example.mediaservice.entity.DTO;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws")
@Data
public class AwsS3Properties {
    private String region;
    private String accessKey;
    private String secretKey;

    private S3 s3 = new S3();

    @Data
    public static class S3 {
        private String bucketName;
    }

    public String getBucketName() {
        return s3.getBucketName();
    }
}
