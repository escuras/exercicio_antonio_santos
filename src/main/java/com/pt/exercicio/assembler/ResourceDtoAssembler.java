package com.pt.exercicio.assembler;

import com.pt.exercicio.dto.AddressDto;
import com.pt.exercicio.dto.ResourceDto;
import com.pt.exercicio.resource.AddressResource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ResourceDtoAssembler implements RepresentationModelAssembler<ResourceDto, ResourceDto> {

    @Override
    public ResourceDto toModel(ResourceDto entity) {
        if (entity instanceof AddressDto a) {
            if (a.getCep() != null) {
                a.add(linkTo(methodOn(AddressResource.class).getAddress(a.getCep().replace("-", ""))).withSelfRel());
                a.add(linkTo(methodOn(AddressResource.class).getByAddress("state", "local", "place")).withRel("get list of places by address"));
            }
        }
        return entity;
    }

    @Override
    public CollectionModel<ResourceDto> toCollectionModel(Iterable<? extends ResourceDto> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
