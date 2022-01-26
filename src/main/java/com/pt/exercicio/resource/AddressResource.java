package com.pt.exercicio.resource;

import com.pt.exercicio.dto.AddressDto;
import com.pt.exercicio.dto.ResourceDto;
import com.pt.exercicio.assembler.ResourceDtoAssembler;
import com.pt.exercicio.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class AddressResource {

    private final AddressService addressService;
    private final ConversionService conversionService;
    private final ResourceDtoAssembler resourceDtoAssembler;

    @GetMapping(value = "/cep/{cep}")
    public ResourceDto getAddress(@PathVariable @NotBlank String cep) {
        return resourceDtoAssembler.toModel(addressService.getByCep(cep));
    }

    @GetMapping(value = "/{state}/{local}/{place}")
    public CollectionModel<ResourceDto> getByAddress(@PathVariable String state, @PathVariable String local, @PathVariable String place) {
        List<AddressDto> addresses = addressService.getByAddress(state, local, place);
        return resourceDtoAssembler.toCollectionModel(addresses);
    }
}
