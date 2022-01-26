package com.pt.exercicio.service;

import com.pt.exercicio.dto.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto getByCep(String cep);
    List<AddressDto> getByAddress(String state, String local, String place);
}
