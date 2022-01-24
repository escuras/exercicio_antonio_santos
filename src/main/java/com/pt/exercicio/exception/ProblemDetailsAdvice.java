package com.pt.exercicio.exception;

import com.pt.exercicio.exception.runtime.CepException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ProblemDetailsAdvice {

    @ExceptionHandler(CepException.class)
    public ResponseEntity<?> toProblemDetail(CepException cepException) {
        ProblemDetails details = new ProblemDetailsBuilder(cepException).build();

        log.debug(details.toString(), cepException);

        return ResponseEntity.status(details.getStatus())
                .contentType(ProblemDetails.JSON_MEDIA_TYPE)
                .body(details);
    }
}