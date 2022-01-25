package com.pt.exercicio.service;

import com.pt.exercicio.client.CepClient;
import com.pt.exercicio.client.model.Address;
import com.pt.exercicio.dto.AddressDto;
import com.pt.exercicio.repository.AddressRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final CepClient cepClient;
    private final AddressRepository addressRepository;
    private final ConversionService conversionService;

    @Override
    public Address getOne(String cep) {
        var optionalAddress = addressRepository.findById(cep);
        if (optionalAddress.isEmpty()) {
            Address address = conversionService.convert(cepClient.getAddress(cep), Address.class);
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
            List<AddressDto> addressesDto = cepClient.getAddress(state, local, place);
            if (!CollectionUtils.isEmpty(addressesDto)) {
                return addressesDto.stream().map(e -> this.conversionService.convert(e, Address.class)).collect(Collectors.toList());
            }
        } catch (FeignException e) {
            log.error("client returns an error or empty.");
        }
        return Collections.emptyList();
    }
}
