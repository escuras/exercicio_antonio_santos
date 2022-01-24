package com.pt.exercicio.exception.runtime;

import com.pt.exercicio.exception.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid address.")
@Status(value = 408, message = "The value of the address is invalid")
public class AddressException extends CustomException {
}
