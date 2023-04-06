package com.inditex.microservice.unit.service;

import static com.inditex.microservice.integration.utils.PriceZoneUtils.gePriceResponse;
import static com.inditex.microservice.integration.utils.PriceZoneUtils.getDateWithFormat;
import static com.inditex.microservice.integration.utils.PriceZoneUtils.getPrice;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.inditex.microservice.domain.response.PriceZoneResponse;
import com.inditex.microservice.mapper.PriceZoneMapper;
import com.inditex.microservice.model.PriceZone;
import com.inditex.microservice.repository.PriceZoneRepository;
import com.inditex.microservice.service.PriceZoneService;
import com.inditex.microservice.specification.PriceZoneCriteria;
import com.inditex.microservice.specification.PriceZoneSpecification;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceZoneServiceUnitTest {

    @Mock
    private PriceZoneRepository repository;

    @Mock
    private PriceZoneMapper mapper;

    @InjectMocks
    private PriceZoneService service;

    @Test
    void givenAnyCriteria_whenFindPrices_thenReturnAllPrices() {
        final var priceElement = getPrice(3, 2, 3);
        final var pricesList = Arrays.asList(getPrice(1, 0, 1), getPrice(2, 1, 2), priceElement);
        when(repository.findAll(any(PriceZoneSpecification.class))).thenReturn(pricesList);
        when(mapper.mapEntityToResponse(priceElement)).thenReturn(gePriceResponse(3));

        final var response = service.getPrices(new PriceZoneCriteria());
        verify(repository).findAll(any(PriceZoneSpecification.class));
        assert response.getProductId() == 3;
    }

    @Test
    void givenCriteria_whenFindPrices_thenReturnUniquePrice() {
        final var priceElement = getPrice(3, 2, 3);
        final var pricesList = List.of(priceElement);

        final var criteria = getCriteria(getDateWithFormat("2020-06-16-21.00.00"),1,1);
        when(repository.findAll(any(PriceZoneSpecification.class))).thenReturn(pricesList);
        when(mapper.mapEntityToResponse(priceElement)).thenReturn(gePriceResponse(1));

        final var response = service.getPrices(criteria);
        verify(repository).findAll(any(PriceZoneSpecification.class));
        assert response.getProductId() == 1;
    }

    @Test
    void givenCriteria_whenFindPrices_thenThowError() {
        final var priceElement = getPrice(3, 2, 3);
        final var pricesList = List.of(priceElement);

        final var criteria = getCriteria(getDateWithFormat("2020-06-16-21.00.00"),1,1);
        when(repository.findAll(any(PriceZoneSpecification.class))).thenReturn(pricesList);
        when(mapper.mapEntityToResponse(priceElement)).thenReturn(gePriceResponse(1));

        final var response = service.getPrices(criteria);
        verify(repository).findAll(any(PriceZoneSpecification.class));
        assert response.getProductId() == 1;
    }

    private PriceZoneCriteria getCriteria(LocalDateTime executionDate, Integer productId, Integer brandId) {
        return new PriceZoneCriteria(executionDate, productId, brandId);
    }
}
