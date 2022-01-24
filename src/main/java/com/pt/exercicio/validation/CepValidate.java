package com.pt.exercicio.validation;

import com.pt.exercicio.exception.runtime.CepException;
import org.springframework.util.StringUtils;

public class CepValidate {

    public static void validate(String cep) {
        if (!StringUtils.hasLength(cep) || cep.length() != 8 || !cep.matches("[0-9]+")) {
            throw new CepException();
        }
    }

}
