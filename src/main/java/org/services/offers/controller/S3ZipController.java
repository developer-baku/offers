package org.services.offers.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.services.offers.dto.ZipExtractionResponseDTO;
import org.services.offers.service.S3ZipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/offer")
@RequiredArgsConstructor
@Slf4j
public class S3ZipController {

  private final S3ZipService s3ZipService;

  @PostMapping("/images")
  @Operation(
      summary = "Extracts Images from a ZIP file uploaded to the server",
      description =
          "This endpoint allows the client to upload a ZIP file. The server will extract image files from the ZIP and return them as a list of byte arrays.",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully extracted the images",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(type = "array", implementation = byte[].class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
      })
  public ResponseEntity<ZipExtractionResponseDTO> extractZip() throws IOException {
    log.info("Received request to extract images from ZIP.");

    ZipExtractionResponseDTO zipExtractionResponseDTO = s3ZipService.extractImagesFromZip();
    log.info("Successfully  from ZIP.extracted images");
    return ResponseEntity.ok(zipExtractionResponseDTO);
  }
}
