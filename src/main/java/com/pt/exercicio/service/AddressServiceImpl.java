package com.pt.exercicio.service;

import com.pt.exercicio.client.CepClient;
import com.pt.exercicio.model.Address;
import com.pt.exercicio.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService{

    private final CepClient cepClient;
    private final AddressRepository addressRepository;

    @Override
    public Address getOne(String cep) {
        Optional<Address> optionalAddress = addressRepository.findById(cep);
        if (optionalAddress.isEmpty()) {
            Address address = cepClient.getCep(cep);
            if (address.getCep() != null) {
                address.setId(cep);
                return addressRepository.save(address);
            }
            return null;
        }
        return optionalAddress.get();
    }

    @Override
    public List<Address> getAll() {
       return addressRepository.findAll();
    }

    private Address save(Address address) {
        return addressRepository.save(address);
    }
}
