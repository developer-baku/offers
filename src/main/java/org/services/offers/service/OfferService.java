package org.services.offers.service;

import lombok.RequiredArgsConstructor;
import org.services.offers.dto.OfferResponseDTO;
import org.services.offers.mapper.OfferMapper;
import org.services.offers.repository.OfferRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferService {
  private final OfferRepository offerRepository;
  private final OfferMapper offerMapper;

  public Page<OfferResponseDTO> getPaginatedOffers(Pageable pageable) {
    return offerRepository.findAll(pageable).map(offerMapper::toDto);
  }
}
