package org.services.offers.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.services.offers.dto.ZipExtractionResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ZipExtractionMapper {
  @Mapping(target = "imageFiles", source = "imageFiles")
  default ZipExtractionResponseDTO mapToDto(List<byte[]> imageFiles) {
    return new ZipExtractionResponseDTO(imageFiles);
  }
}
