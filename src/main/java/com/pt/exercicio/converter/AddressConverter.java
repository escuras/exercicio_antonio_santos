package com.pt.exercicio.converter;

import com.pt.exercicio.dto.AddressDto;
import com.pt.exercicio.client.model.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

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

}
