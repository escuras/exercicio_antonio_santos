package com.pt.exercicio.controller;

import com.pt.exercicio.dto.AddressDto;
import com.pt.exercicio.client.model.Address;
import com.pt.exercicio.service.AddressService;
import com.pt.exercicio.validation.AddressValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class CepController {

    private final AddressService addressService;
    private final ConversionService conversionService;

    @GetMapping(value = "/cep/{cep}")
    public AddressDto getAddress(@PathVariable String cep) {
        AddressValidation.validate(cep);
        Address address = addressService.getOne(cep);
        return conversionService.convert(address, AddressDto.class);
    }

    @GetMapping(value = "/{state}/{local}/{place}")
    public List<AddressDto> getByAddress(@PathVariable String state, @PathVariable String local, @PathVariable String place) {
        AddressValidation.validate(state, local, place);
        List<Address> addresses = addressService.getByAddress(state, local, place);
        return addresses.stream().map(e -> conversionService.convert(e, AddressDto.class)).collect(Collectors.toList());
    }
}
