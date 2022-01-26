package com.pt.exercicio.service.impl;

import com.pt.exercicio.client.AddressClient;
import com.pt.exercicio.dto.AddressDto;
import com.pt.exercicio.model.Address;
import com.pt.exercicio.client.dto.AddressClientDto;
import com.pt.exercicio.repository.AddressRepository;
import com.pt.exercicio.service.AddressService;
import com.pt.exercicio.validation.AddressValidation;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressClient cepClient;
    private final AddressRepository addressRepository;
    private final ConversionService conversionService;

    @Override
    public AddressDto getByCep(String cep) {
        AddressValidation.validate(cep);
        return conversionService.convert(addressRepository.findById(cep).orElseGet(() -> getAddress(cep)), AddressDto.class);
    }

    private Address getAddress(String cep) {
        Address address = conversionService.convert(cepClient.getAddress(cep), Address.class);
        if (address.getCep() != null) {
            address.setId(cep);
            return addressRepository.save(address);
        }
        return null;
    }

    @Override
    public List<AddressDto> getByAddress(String state, String local, String place) {
        AddressValidation.validate(state, local, place);
        try {
            List<AddressClientDto> cepsDto = cepClient.getAddress(state, local, place);
            return cepsDto.stream()
                    .map(cep -> conversionService.convert(cep, AddressDto.class))
                    .collect(Collectors.toList());
        } catch (FeignException e) {
            log.error("client returns an error or empty.");
        }
        return Collections.emptyList();
    }
}
