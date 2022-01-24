package com.pt.exercicio.converter;

import com.pt.exercicio.dto.AddressDto;
import com.pt.exercicio.model.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressConverter implements Converter<Address, AddressDto>  {

    @Override
    public AddressDto convert(Address source) {
        if (source == null) {
            return null;
        }
        AddressDto addressDto = new AddressDto();
        addressDto.setBairro(source.getBairro());
        addressDto.setCep(source.getCep());
        addressDto.setComplemento(source.getComplemento());
        addressDto.setDdd(source.getDdd());
        addressDto.setGia(source.getGia());
        addressDto.setIbge(source.getIbge());
        addressDto.setLocalidade(source.getLocalidade());
        addressDto.setLogradouro(source.getLogradouro());
        addressDto.setSiafi(source.getSiafi());
        addressDto.setUf(source.getUf());
        addressDto.addLink();
        return addressDto;
    }

    public List<AddressDto> convert(List<Address> source) {
        if (!CollectionUtils.isEmpty(source)) {
            return source.stream().map(e -> this.convert(e)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
