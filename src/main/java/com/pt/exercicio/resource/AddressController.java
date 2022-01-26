package com.pt.exercicio.resource;

import com.pt.exercicio.dto.AddressDto;
import com.pt.exercicio.dto.ResourceDto;
import com.pt.exercicio.dto.assembler.ResourceDtoAssembler;
import com.pt.exercicio.model.Address;
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
public class AddressController {

    private final AddressService addressService;
    private final ConversionService conversionService;
    private final ResourceDtoAssembler resourceDtoAssembler;

    @GetMapping(value = "/cep/{cep}")
    public ResourceDto getAddress(@PathVariable String cep) {
        AddressValidation.validate(cep);
        Address address = addressService.getOne(cep);
        return resourceDtoAssembler.toModel(conversionService.convert(address, AddressDto.class));
    }

    @GetMapping(value = "/{state}/{local}/{place}")
    public List<ResourceDto> getByAddress(@PathVariable String state, @PathVariable String local, @PathVariable String place) {
        AddressValidation.validate(state, local, place);
        List<Address> addresses = addressService.getByAddress(state, local, place);
        return addresses.stream().map(e -> resourceDtoAssembler.toModel(conversionService.convert(e, AddressDto.class))).collect(Collectors.toList());
    }
}
