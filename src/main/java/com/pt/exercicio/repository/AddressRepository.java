package com.pt.exercicio.repository;

import com.pt.exercicio.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    Optional<Address> findByUfAndLocalidadeAndLogradouro(String uf, String localidade, String logradouro);
}
