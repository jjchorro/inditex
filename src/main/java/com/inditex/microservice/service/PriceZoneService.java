package com.inditex.microservice.service;

import com.inditex.microservice.domain.response.PriceZoneResponse;
import com.inditex.microservice.exception.PriceZoneException;
import com.inditex.microservice.mapper.PriceZoneMapper;
import com.inditex.microservice.model.PriceZone;
import com.inditex.microservice.repository.PriceZoneRepository;
import com.inditex.microservice.specification.PriceZoneCriteria;
import com.inditex.microservice.specification.PriceZoneSpecification;
import java.util.Comparator;
import org.springframework.stereotype.Service;

@Service
public class PriceZoneService {

    private final PriceZoneMapper mapper;
    private final PriceZoneRepository repository;

    public PriceZoneService(final PriceZoneMapper mapper, final PriceZoneRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public PriceZoneResponse getPrices(PriceZoneCriteria criteria) {
        final var price = repository.findAll(new PriceZoneSpecification(criteria)).stream()
            .max(Comparator.comparingInt(PriceZone::getPriority));

        return price.map(prices -> mapper.mapEntityToResponse(price.get()))
            .orElseThrow(() -> new PriceZoneException("There is not values for entry parameters"));
    }
}
