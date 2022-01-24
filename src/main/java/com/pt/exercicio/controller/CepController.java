package com.pt.exercicio.controller;

import com.pt.exercicio.model.Address;
import com.pt.exercicio.service.AddressService;
import com.pt.exercicio.validation.CepValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cep")
public class CepController {

    private final AddressService addressService;

    @GetMapping(value = "/{cep}")
    public Address getAddress(@PathVariable String cep) {
        CepValidate.validate(cep);
        Address address = addressService.getOne(cep);
        if (address != null) {
            address.addLink();
        }
        return address;
    }

    @GetMapping
    public List<Address> getAll() {
        List<Address> addresses = addressService.getAll();
        addresses.parallelStream().forEach(e -> e.addLink());
        return addresses;
    }
}
