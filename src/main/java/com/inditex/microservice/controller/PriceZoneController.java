package com.inditex.microservice.controller;

import com.inditex.microservice.constants.UrlConstants;
import com.inditex.microservice.domain.response.PriceZoneResponse;
import com.inditex.microservice.service.PriceZoneService;
import com.inditex.microservice.specification.PriceZoneCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlConstants.PRICES_ENDPOINT)
public class PriceZoneController {

    private final PriceZoneService service;

    public PriceZoneController(final PriceZoneService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get prices data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "PriceZone list retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "PriceZone values does not exist"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public PriceZoneResponse getPriceList(@Parameter(hidden = true) final PriceZoneCriteria criteria) {
        return service.getPrices(criteria);

    }
}
