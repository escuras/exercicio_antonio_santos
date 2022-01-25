package com.pt.exercicio.exception;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

import java.net.URI;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProblemDetails {
    public static final MediaType JSON_MEDIA_TYPE =
            MediaType.valueOf("application/problem+json");

    private URI type;
    private String title;
    private String detail;
    private Integer status;
    private URI instance;
}
