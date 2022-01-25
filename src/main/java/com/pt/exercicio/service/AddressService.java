package com.pt.exercicio.service;

import com.pt.exercicio.client.model.Address;

import java.util.List;

public interface AddressService {
    Address getOne(String cep);
    List<Address> getByAddress(String state, String local, String place);
}
