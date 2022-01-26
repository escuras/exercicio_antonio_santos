package com.pt.exercicio.converter;

import com.pt.exercicio.client.dto.AddressClientDto;
import com.pt.exercicio.model.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressClientDtoToAddress implements Converter<AddressClientDto, Address> {

    @Override
    public Address convert(AddressClientDto source) {
        return Address.builder()
                .bairro(source.getBairro())
                .cep(source.getCep())
                .complemento(source.getComplemento())
                .ddd(source.getDdd())
                .gia(source.getGia())
                .ibge(source.getIbge())
                .localidade(source.getLocalidade())
                .logradouro(source.getLogradouro())
                .siafi(source.getSiafi())
                .uf(source.getUf())
                .build();
    }
}
