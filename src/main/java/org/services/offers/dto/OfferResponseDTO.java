package org.services.offers.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OfferResponseDTO {
  private String name;
  private String description;
  private String banner;
}
