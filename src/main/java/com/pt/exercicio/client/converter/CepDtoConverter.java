package com.pt.exercicio.client.converter;

import com.pt.exercicio.client.model.CepDto;
import com.pt.exercicio.model.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CepDtoConverter implements Converter<Address, CepDto>  {

    @Override
    public CepDto convert(Address source) {
        if (source == null) {
            return null;
        }
        CepDto cepDto = new CepDto();
        cepDto.setBairro(source.getBairro());
        cepDto.setCep(source.getCep());
        cepDto.setComplemento(source.getComplemento());
        cepDto.setDdd(source.getDdd());
        cepDto.setGia(source.getGia());
        cepDto.setIbge(source.getIbge());
        cepDto.setLocalidade(source.getLocalidade());
        cepDto.setLogradouro(source.getLogradouro());
        cepDto.setSiafi(source.getSiafi());
        cepDto.setUf(source.getUf());
        return cepDto;
    }

}
