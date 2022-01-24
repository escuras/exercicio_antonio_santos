package com.pt.exercicio.service;

import com.pt.exercicio.model.Address;

import java.util.List;

public interface AddressService {
    Address getOne(String cep);
    List<Address> getAll();
}
