package com.pt.exercicio.client;

import com.pt.exercicio.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url="http://viacep.com.br/ws", name = "http://viacep.com.br/ws")
public interface CepClient {

    @GetMapping("/{cep}/json")
    Address getCep(@PathVariable("cep") String cep);
}
