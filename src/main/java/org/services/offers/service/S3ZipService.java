package org.services.offers.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.services.offers.dto.ZipExtractionResponseDTO;
import org.services.offers.mapper.ZipExtractionMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3ZipService {

  @Value("${aws.s3.object-key}")
  private String objectKey;

  @Value("${aws.s3.bucket-name}")
  private String bucketName;

  private final S3Client s3Client;

  private final ZipExtractionMapper zipExtractionMapper;

  public ZipExtractionResponseDTO extractImagesFromZip() throws IOException {
    log.info(
        "Starting to extract images from ZIP located in bucket: {} with key: {}",
        bucketName,
        objectKey);

    GetObjectRequest getObjectRequest =
        GetObjectRequest.builder().bucket(bucketName).key(objectKey).build();
    InputStream zipInputStream = s3Client.getObject(getObjectRequest);

    List<byte[]> imageFiles = new ArrayList<>();

    try (ZipInputStream zis = new ZipInputStream(zipInputStream)) {
      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        if (entry.getName().toLowerCase().matches(".*\\.(png|jpg|jpeg|gif|bmp)$")) {
          ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
          byte[] buffer = new byte[4096];
          int length;
          while ((length = zis.read(buffer)) > 0) {
            byteStream.write(buffer, 0, length);
          }
          imageFiles.add(byteStream.toByteArray());
          log.debug("Extracted image: {}", entry.getName());
        }
      }
    }

    log.info("Successfully extracted {} images from the ZIP file.", imageFiles.size());
    return zipExtractionMapper.mapToDto(imageFiles);
  }
}
