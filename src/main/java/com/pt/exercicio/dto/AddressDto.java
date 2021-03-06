package com.pt.exercicio.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto extends ResourceDto {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

}
