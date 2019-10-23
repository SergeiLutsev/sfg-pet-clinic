package com.sergei.petclinic.services.springdatajpa;

import com.sergei.petclinic.models.Owner;
import com.sergei.petclinic.repisitories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService ownerService;

    final Long ownerId=1L;
    final String ownerLastName = "Smith";
    Owner own;

    @BeforeEach
    void setUp() {
        own=new Owner();
        own.setId(ownerId);
        own.setLastName(ownerLastName);
    }

    @Test
    void findByLastName() {


        when(ownerRepository.findByLastName(any())).thenReturn(own);

        Owner smith= ownerService.findByLastName(ownerLastName);
        assertEquals(ownerLastName,smith.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> owners=new HashSet<>();
        owners.add(new Owner());
        owners.add(new Owner());

        when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> lst=ownerService.findAll();

        assertEquals(2,lst.size());

    }

    @Test
    void findById() {

        when(ownerRepository.findById(any())).thenReturn(Optional.of(own));
        Owner opt=ownerService.findById(ownerId);

        assertEquals(ownerLastName,opt.getLastName());

    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(own);

        Owner tmp=ownerService.save(own);

        assertEquals(ownerId,tmp.getId());
    }

    @Test
    void delete() {
        ownerService.delete(own);
        verify(ownerRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerService.deleteById(ownerId);
        verify(ownerRepository,times(1)).deleteById(anyLong());
    }
}