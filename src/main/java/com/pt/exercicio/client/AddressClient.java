package com.pt.exercicio.client;

import com.pt.exercicio.client.dto.AddressClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url="${domain.viacep.url}", name = "viacep")
public interface AddressClient {

    @GetMapping("/{cep}/json")
    AddressClientDto getAddress(@PathVariable String cep);

    @GetMapping("/{state}/{local}/{place}/json")
    List<AddressClientDto> getAddress(@PathVariable String state, @PathVariable String local, @PathVariable String place);
}
