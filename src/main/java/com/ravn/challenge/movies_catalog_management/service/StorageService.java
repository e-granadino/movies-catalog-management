package com.ravn.challenge.movies_catalog_management.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class StorageService {

    private final S3Client s3Client;
    private static final String BUCKET_NAME = "movie_catalog_resources";
    private static final String REGION = "us-west-1";
    private static final String ENDPOINT_URL = "https://gtwsdscawhrmyhwjcuhl.supabase.co/storage/v1";

    public StorageService(@Value("${supabase.access.key}") String accessKeyId, @Value("${supabase.secret.key}") String secretAccessKey) {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        this.s3Client = S3Client.builder()
                .region(Region.of(REGION))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .endpointOverride(URI.create(ENDPOINT_URL.concat("/s3")))
                .build();
    }

    public String uploadFile(MultipartFile file, String fileName) {
        try {
            // Save the multipart file to a temporary location
            Path tempFile = Files.createTempFile(null, null);
            Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

            String fileNameToSave = String.valueOf(Instant.now().toEpochMilli()).concat("_").concat(fileName.trim().toLowerCase());

            // Create metadata (optional)
            Map<String, String> metadata = new HashMap<>();
            metadata.put("Content-Type", file.getContentType());  // Get the MIME type from MultipartFile

            // Prepare the upload request
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(fileNameToSave)
                    .metadata(metadata)
                    .build();

            // Upload the file to Supabase (S3-compatible storage)
            s3Client.putObject(putObjectRequest, tempFile);

            // Delete temp file after upload
            Files.delete(tempFile);

            return ENDPOINT_URL.concat("/object/public/").concat(BUCKET_NAME).concat("/".concat(fileNameToSave));
        } catch (S3Exception | IOException e) {
            throw new RuntimeException("Failed to upload image: " + e.getMessage(), e);
        }
    }
}

