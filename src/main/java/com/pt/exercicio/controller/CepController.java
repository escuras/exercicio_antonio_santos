package com.pt.exercicio.controller;

import com.pt.exercicio.converter.AddressConverter;
import com.pt.exercicio.dto.AddressDto;
import com.pt.exercicio.model.Address;
import com.pt.exercicio.service.AddressService;
import com.pt.exercicio.validation.AddressValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CepController {

    private final AddressService addressService;
    private final AddressConverter addressConverter;

    @GetMapping(value = "/cep/{cep}")
    public AddressDto getAddress(@PathVariable String cep) {
        AddressValidation.validate(cep);
        Address address = addressService.getOne(cep);
        return addressConverter.convert(address);

    }

    @GetMapping(value = "/{state}/{local}/{place}")
    public List<AddressDto> getByAddress(@PathVariable String state, @PathVariable String local, @PathVariable String place) {
        AddressValidation.validate(state, local, place);
        List<Address> address = addressService.getByAddress(state, local, place);
        return addressConverter.convert(address);
    }
}
