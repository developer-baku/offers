package org.services.offers.mapper;

import org.mapstruct.Mapper;
import org.services.offers.dto.OfferResponseDTO;
import org.services.offers.entity.Offer;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface OfferMapper {
  OfferResponseDTO toDto(Offer offer);
}
