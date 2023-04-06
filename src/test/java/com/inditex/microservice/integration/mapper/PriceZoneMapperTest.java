package com.inditex.microservice.integration.mapper;

import static com.inditex.microservice.integration.utils.PriceZoneUtils.getDateWithFormat;
import static com.inditex.microservice.integration.utils.PriceZoneUtils.getPrice;

import com.inditex.microservice.mapper.PriceZoneMapper;
import java.math.BigDecimal;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PriceZoneMapperTest {

    @Autowired
    PriceZoneMapper mapper;

    @Test
    void testmapEntityToResponse() {

        final var response = mapper.mapEntityToResponse(getPrice(1, 1, 1));

        assert Objects.equals(response.getPrice(), new BigDecimal(25));
        assert Objects.equals(response.getStartDate(), getDateWithFormat("2020-06-16-21.00.00"));
    }
}
