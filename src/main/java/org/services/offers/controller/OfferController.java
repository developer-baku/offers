package org.services.offers.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.services.offers.dto.OfferResponseDTO;
import org.services.offers.service.OfferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/offer")
@Slf4j
public class OfferController {
  private final OfferService offerService;

  @GetMapping("/retrieve/page")
  @Operation(
      summary = "Retrieve paginated offers",
      description = "Retrieves a paginated list of offers based on page number and size.",
      tags = {"Offers", "Pagination"},
      parameters = {
        @Parameter(
            name = "page",
            description = "The page number to retrieve",
            schema = @Schema(type = "integer", example = "0")),
        @Parameter(
            name = "size",
            description = "The number of offers per page",
            schema = @Schema(type = "integer", example = "5"))
      },
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the paginated list of offers",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = OfferResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad request: Invalid syntax."),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error: Something went wrong on the server.")
      })
  public ResponseEntity<Page<OfferResponseDTO>> getPaginatedOffers(
      @RequestParam(defaultValue = "0") @Min(0) int page,
      @RequestParam(defaultValue = "5") @Min(1) int size) {
    log.info("Fetching paginated offers: page={}, size={}", page, size);
    Page<OfferResponseDTO> offers = offerService.getPaginatedOffers(PageRequest.of(page, size));
    return ResponseEntity.ok(offers);
  }
}
