package com.inditex.microservice.domain.response;

import java.io.Serializable;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class ErrorDto implements Serializable {

    private String code;
    private String message;

    @Builder
    public ErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
