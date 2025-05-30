package com.example.capd.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Uploader {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    private AmazonS3 s3Client;

    private void initializeS3Client() {
        if (s3Client == null) {
            BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
            this.s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(region)
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .build();
        }
    }

    // ✅ 1. MultipartFile 업로드
    public String upload(MultipartFile file, String dirName) throws IOException {
        initializeS3Client();

        String originalFilename = file.getOriginalFilename();
        String ext = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";
        String fileName = dirName + "/" + UUID.randomUUID() + ext;

        InputStream inputStream = file.getInputStream();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        s3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, metadata));

        return s3Client.getUrl(bucket, fileName).toString();
    }

    // ✅ 2. File 경로 업로드
    public String uploadFromFilePath(String filePath, String dirName) {
        initializeS3Client();

        File file = new File(filePath);
        String ext = file.getName().contains(".") ? file.getName().substring(file.getName().lastIndexOf(".")) : ".jpg";
        String fileName = dirName + "/" + UUID.randomUUID() + ext;

        s3Client.putObject(new PutObjectRequest(bucket, fileName, file));

        return s3Client.getUrl(bucket, fileName).toString();
    }

    // ✅ 3. InputStream 업로드 (에러 해결 핵심)
    public String upload(InputStream inputStream, String fileName) {
        initializeS3Client();

        ObjectMetadata metadata = new ObjectMetadata();
        // contentLength를 모르면 설정 안 해도 되지만 정확히 알면 안정성 증가
        // metadata.setContentLength(...);

        s3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, metadata));

        return s3Client.getUrl(bucket, fileName).toString();
    }
}
