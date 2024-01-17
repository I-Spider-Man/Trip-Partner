package com.example.demo.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.utils.IoUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class StorageService {

    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public boolean uploadFile(String fileName, MultipartFile file) {
        File objFile=convertMultipartFileToFile(file);
        try {
            s3Client.putObject(new PutObjectRequest(bucketName, fileName,objFile));
            objFile.delete();
            return true;
        } catch (Exception e) {
            logger.error("Error uploading file", e);
            throw new RuntimeException("Error uploading file", e);
        }

    }
    public byte[] viewFile(String fileName) {
        try {

            S3Object s3Object = s3Client.getObject(bucketName, fileName);
            try (S3ObjectInputStream inputStream = s3Object.getObjectContent()) {
                return IoUtils.toByteArray(inputStream);
            }
        } catch (IOException e) {
            logger.error("Error viewing file", e);
            throw new RuntimeException("Error viewing file", e);
        }
    }

    public String deleteFile(String fileName) {
        try {
            s3Client.deleteObject(bucketName, fileName);
            return "File " + fileName + " deleted.";
        } catch (Exception e) {
            logger.error("Error deleting file", e);
            throw new RuntimeException("Error deleting file", e);
        }
    }

    private File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            logger.error("Error converting MultipartFile to File", e);
            throw new RuntimeException("Error converting MultipartFile to File", e);
        }
        return convertedFile;
    }
}
