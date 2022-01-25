package com.pt.exercicio.dto;

import com.pt.exercicio.resource.CepController;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
public class AddressDto extends RepresentationModel<AddressDto> {

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


    public void addLink() {
        if (cep != null) {
            add(linkTo(methodOn(CepController.class).getAddress(this.cep.replace("-", ""))).withSelfRel());
        }
    }


}
