package com.pt.exercicio.client;

import com.pt.exercicio.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url="${domain.viacep}", name = "${domain.viacep-name}")
public interface CepClient {

    @GetMapping("/{cep}/json")
    AddressDto getAddress(@PathVariable("cep") String cep);

    @GetMapping("/{state}/{local}/{place}/json")
    List<AddressDto> getAddress(@PathVariable String state, @PathVariable String local, @PathVariable String place);
}
