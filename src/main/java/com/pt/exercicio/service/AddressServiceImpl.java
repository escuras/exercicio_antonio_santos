package com.pt.exercicio.service;

import com.pt.exercicio.client.CepClient;
import com.pt.exercicio.model.Address;
import com.pt.exercicio.repository.AddressRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final CepClient cepClient;
    private final AddressRepository addressRepository;

    @Override
    public Address getOne(String cep) {
        var optionalAddress = addressRepository.findById(cep);
        if (optionalAddress.isEmpty()) {
            Address address = cepClient.getAddress(cep);
            if (address.getCep() != null) {
                address.setId(cep);
                return addressRepository.save(address);
            }
            return null;
        }
        return optionalAddress.get();
    }

    @Override
    public List<Address> getByAddress(String state, String local, String place) {
        try {
            return cepClient.getAddress(state, local, place);
        } catch (FeignException e) {
            log.error("client returns an error or empty.");
        }
        return Collections.emptyList();
    }
}
