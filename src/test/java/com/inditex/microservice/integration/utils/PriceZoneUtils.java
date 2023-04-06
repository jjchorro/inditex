package com.inditex.microservice.integration.utils;

import com.inditex.microservice.constants.UrlConstants;
import com.inditex.microservice.domain.response.PriceZoneResponse;
import com.inditex.microservice.model.PriceZone;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class PriceZoneUtils {

    static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
    }

    public static LocalDateTime getDateWithFormat(String date) {
        return LocalDateTime.parse(date, getFormatter());
    }

    public static String getUrl(Integer port) {
        return "http://localhost:" + port + UrlConstants.PRICES_ENDPOINT;
    }

    public static HttpHeaders buildHeaders(String contentType) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        return headers;
    }

    public static PriceZone getPrice(Integer id, Integer priority, Integer productId) {
        return PriceZone.builder()
            .id(id)
            .brandId(1)
            .startDate(getDateWithFormat("2020-06-16-21.00.00"))
            .endDate(getDateWithFormat("2020-06-16-21.00.00"))
            .priceList(1)
            .productId(productId)
            .priority(priority)
            .price(new BigDecimal(25))
            .currency("EUR")
            .build();
    }

    public static PriceZoneResponse gePriceResponse(Integer productId) {
        return PriceZoneResponse.builder()
            .productId(productId)
            .brandId(1)
            .priceList(1)
            .startDate(getDateWithFormat("2020-06-16-21.00.00"))
            .endDate(getDateWithFormat("2020-06-16-21.00.00"))
            .price(new BigDecimal(25))
            .build();
    }

}
