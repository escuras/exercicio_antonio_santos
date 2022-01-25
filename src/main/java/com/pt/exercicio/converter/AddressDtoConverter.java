package com.pt.exercicio.converter;

import com.pt.exercicio.dto.AddressDto;
import com.pt.exercicio.client.model.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoConverter implements Converter<AddressDto, Address> {

    @Override
    public Address convert(AddressDto source) {
        if (source == null) {
            return null;
        }
        Address address = new Address();
        address.setBairro(source.getBairro());
        address.setCep(source.getCep());
        address.setComplemento(source.getComplemento());
        address.setDdd(source.getDdd());
        address.setGia(source.getGia());
        address.setIbge(source.getIbge());
        address.setLocalidade(source.getLocalidade());
        address.setLogradouro(source.getLogradouro());
        address.setSiafi(source.getSiafi());
        address.setUf(source.getUf());
        return address;
    }
}
