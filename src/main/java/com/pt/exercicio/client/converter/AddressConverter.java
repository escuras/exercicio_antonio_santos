package com.pt.exercicio.client.converter;

import com.pt.exercicio.client.model.CepDto;
import com.pt.exercicio.model.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter implements Converter<CepDto, Address> {

    @Override
    public Address convert(CepDto source) {
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
