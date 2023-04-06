package com.inditex.microservice.mapper;

import com.inditex.microservice.domain.response.PriceZoneResponse;
import com.inditex.microservice.model.PriceZone;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceZoneMapper {

    PriceZoneResponse mapEntityToResponse(final PriceZone priceZone);
}
