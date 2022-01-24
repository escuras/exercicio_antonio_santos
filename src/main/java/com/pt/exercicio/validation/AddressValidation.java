package com.pt.exercicio.validation;

import com.pt.exercicio.exception.runtime.AddressException;
import com.pt.exercicio.exception.runtime.CepException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;

public class AddressValidation {

    public static void validate(String cep) {
        if (!StringUtils.hasLength(cep) || cep.length() != 8 || !cep.matches("[0-9]+")) {
            throw new CepException();
        }
    }

    public static void validate(String state, String local, String place) {
        if (Strings.isBlank(state) || Strings.isBlank(local) || Strings.isBlank(place)) {
            throw new AddressException();
        }
    }

}
