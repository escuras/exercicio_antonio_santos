package com.pt.exercicio.client;

import com.pt.exercicio.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url="http://viacep.com.br/ws", name = "http://viacep.com.br/ws")
public interface CepClient {

    @GetMapping("/{cep}/json")
    Address getAddress(@PathVariable("cep") String cep);

    @GetMapping("/{state}/{local}/{place}/json")
    List<Address> getAddress(@PathVariable String state, @PathVariable String local, @PathVariable String place);
}
