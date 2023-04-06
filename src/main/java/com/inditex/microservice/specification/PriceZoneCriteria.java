package com.inditex.microservice.specification;

import io.swagger.v3.oas.annotations.Parameter;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceZoneCriteria {

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    @Parameter(description = "Filter by execution date")
    private LocalDateTime executionDate;

    @Parameter(description = "Filter by product id")
    private Integer productId;

    @Parameter(description = "Filter by brand id")
    private Integer brandId;

}
