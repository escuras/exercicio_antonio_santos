package com.pt.exercicio.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class Base {
    @Id
    private String id;
}
