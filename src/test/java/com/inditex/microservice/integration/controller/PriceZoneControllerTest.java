package com.inditex.microservice.integration.controller;

import static com.inditex.microservice.integration.utils.PriceZoneUtils.buildHeaders;
import static com.inditex.microservice.integration.utils.PriceZoneUtils.getDateWithFormat;
import static com.inditex.microservice.integration.utils.PriceZoneUtils.getUrl;

import com.inditex.microservice.domain.response.PriceZoneResponse;
import java.math.BigDecimal;
import java.net.URI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PriceZoneControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void testGetPriceDay14Hour10() {

        final var executionDate = "2020-06-14-10.00.00";
        var uri = URI.create(getUrl(port).concat("?executionDate=" + executionDate + "&productId=35455&brandId=1"));
        var request = new RequestEntity<>(buildHeaders(MediaType.APPLICATION_JSON_VALUE), HttpMethod.GET, uri);

        final var response = restTemplate.exchange(request, PriceZoneResponse.class);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null;
        assert response.getBody().getPriceList() == 1;
        assert response.getBody().getStartDate().isBefore(getDateWithFormat(executionDate));
        assert response.getBody().getEndDate().isAfter(getDateWithFormat(executionDate));
        assert response.getBody().getPrice().equals(new BigDecimal("35.5"));
    }

    @Test
    void testGetPriceDay14Hour16() {

        final var executionDate = "2020-06-14-16.00.00";
        var uri = URI.create(getUrl(port).concat("?executionDate=" + executionDate + "&productId=35455&brandId=1"));
        var request = new RequestEntity<>(buildHeaders(MediaType.APPLICATION_JSON_VALUE), HttpMethod.GET, uri);

        final var response = restTemplate.exchange(request, PriceZoneResponse.class);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null;
        assert response.getBody().getPriceList() == 2;
        assert response.getBody().getStartDate().isBefore(getDateWithFormat(executionDate));
        assert response.getBody().getEndDate().isAfter(getDateWithFormat(executionDate));
        assert response.getBody().getPrice().equals(new BigDecimal("25.45"));
    }

    @Test
    void testGetPriceDay14Hour21() {

        final var executionDate = "2020-06-14-21.00.00";
        var uri = URI.create(getUrl(port).concat("?executionDate=" + executionDate + "&productId=35455&brandId=1"));
        var request = new RequestEntity<>(buildHeaders(MediaType.APPLICATION_JSON_VALUE), HttpMethod.GET, uri);

        final var response = restTemplate.exchange(request, PriceZoneResponse.class);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null;
        assert response.getBody().getPriceList() == 1;
        assert response.getBody().getStartDate().isBefore(getDateWithFormat(executionDate));
        assert response.getBody().getEndDate().isAfter(getDateWithFormat(executionDate));
        assert response.getBody().getPrice().equals(new BigDecimal("35.5"));
    }

    @Test
    void testGetPriceDay15Hour10() {

        final var executionDate = "2020-06-15-10.00.00";
        var uri = URI.create(getUrl(port).concat("?executionDate=" + executionDate + "&productId=35455&brandId=1"));
        var request = new RequestEntity<>(buildHeaders(MediaType.APPLICATION_JSON_VALUE), HttpMethod.GET, uri);

        final var response = restTemplate.exchange(request, PriceZoneResponse.class);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null;
        assert response.getBody().getPriceList() == 3;
        assert response.getBody().getStartDate().isBefore(getDateWithFormat(executionDate));
        assert response.getBody().getEndDate().isAfter(getDateWithFormat(executionDate));
        assert response.getBody().getPrice().equals(new BigDecimal("30.5"));
    }

    @Test
    void testGetPriceDay16Hour21() {

        final var executionDate = "2020-06-16-21.00.00";
        var uri = URI.create(getUrl(port).concat("?executionDate=" + executionDate + "&productId=35455&brandId=1"));
        var request = new RequestEntity<>(buildHeaders(MediaType.APPLICATION_JSON_VALUE), HttpMethod.GET, uri);

        final var response = restTemplate.exchange(request, PriceZoneResponse.class);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null;
        assert response.getBody().getPriceList() == 4;
        assert response.getBody().getStartDate().isBefore(getDateWithFormat(executionDate));
        assert response.getBody().getEndDate().isAfter(getDateWithFormat(executionDate));
        assert response.getBody().getPrice().equals(new BigDecimal("38.95"));
    }

}
