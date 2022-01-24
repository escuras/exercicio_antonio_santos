package com.pt.exercicio.tests;

import com.pt.exercicio.client.CepClient;
import com.pt.exercicio.model.Address;
import com.pt.exercicio.repository.AddressRepository;
import com.pt.exercicio.service.AddressService;
import com.pt.exercicio.service.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AddressServiceImplTest {

    private CepClient cepClient;
    private AddressRepository addressRepository;
    private AddressService addressService;

    private final String ID = "11110000";

    @BeforeEach
    public void setup() {
        this.addressRepository = mock(AddressRepository.class);
        this.cepClient = mock(CepClient.class);
        this.addressService = new AddressServiceImpl(cepClient, addressRepository);

    }

    @Test
    public void whenGetOneOptionalAddressIsEmptyButCepClientReturnsValue(){
        when(addressRepository.findById(anyString())).thenReturn(Optional.empty());
        when(cepClient.getCep(anyString())).thenReturn(buildAddress(ID));
        when((addressRepository.save(any()))).thenReturn(buildAddress(ID));
        Address address = addressService.getOne(ID);
        verify(addressRepository, times(1)).save(any());
        assertNotNull(address);
    }

    @Test
    public void whenGetOneOptionalAddressIsEmptyAndCepClientReturnsNull(){
        when(addressRepository.findById(anyString())).thenReturn(Optional.empty());
        when(cepClient.getCep(anyString())).thenReturn(buildAddress(null));
        Address address = addressService.getOne(ID);
        verify(addressRepository, times(0)).save(any());
        assertNull(address);
    }

    @Test
    public void whenGetOneOptionalAddressIsNotNull(){
        when(addressRepository.findById(anyString())).thenReturn(Optional.of(buildAddress(ID)));
        Address address = addressService.getOne(ID);
        verify(addressRepository, times(0)).save(any());
        assertNotNull(address);
    }

    private Address buildAddress(String id) {
        Address address = new Address();
        address.setId(id);
        address.setCep(id);
        address.setBairro("Benfica");
        return address;
    }
}
