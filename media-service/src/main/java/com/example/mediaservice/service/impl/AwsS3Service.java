package com.example.mediaservice.service.impl;

import com.example.mediaservice.entity.DTO.AwsS3Properties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final AwsS3Properties awsS3Properties;
    private S3Client s3Client;

    @PostConstruct
    public void init() {
        s3Client = S3Client.builder()
                .region(Region.of(awsS3Properties.getRegion()))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(
                                awsS3Properties.getAccessKey(),
                                awsS3Properties.getSecretKey()
                        )
                ))
                .build();
    }
    public String uploadFile(MultipartFile file) throws IOException {
        String key = "uploads/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(awsS3Properties.getBucketName())
                .key(key)
                .acl(ObjectCannedACL.PUBLIC_READ) // якщо хочеш прямий доступ
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));

        return "https://" + awsS3Properties.getBucketName() + ".s3." +
                awsS3Properties.getRegion() + ".amazonaws.com/" + key;
    }
}

