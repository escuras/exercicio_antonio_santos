package com.pt.exercicio.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pt.exercicio.controller.CepController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address extends RepresentationModel<Address> {

    @Id
    @JsonIgnore
    private String id;

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
