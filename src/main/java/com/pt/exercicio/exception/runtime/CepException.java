package com.pt.exercicio.exception.runtime;

import com.pt.exercicio.exception.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        code = HttpStatus.FORBIDDEN,
        reason = "Cep is Invalid.")
@Status(value = 407, message = "The value of CEP is invalid, 8 characters format.")
public class CepException extends RuntimeException {
}
