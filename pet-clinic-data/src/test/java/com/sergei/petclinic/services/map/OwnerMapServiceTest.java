package com.sergei.petclinic.services.map;

import com.sergei.petclinic.models.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {
    OwnerMapService ownerMapService;
    final Long ownerId=1L;
    final String lastName="Smith";

    @BeforeEach
    void setUp() {
        ownerMapService=new OwnerMapService(new PetTypeMapService(),new PetMapService());
        Owner ovn=new Owner();
        ovn.setId(ownerId);
        ovn.setLastName(lastName);
        ownerMapService.save(ovn);
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1,owners.size());
    }

    @Test
    void deleteById() {
        Owner own = ownerMapService.findById(ownerId);
        assertEquals(ownerId,own.getId());
    }

    @Test
    void delete() {
        Owner own=ownerMapService.findById(ownerId);
        ownerMapService.delete(own);
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(0,owners.size());

    }

    @Test
    void save() {
        Long ownId=2L;
        Owner own2= new Owner();
        own2.setId(ownId);
        Owner owner = ownerMapService.save(own2);
        assertEquals(ownId,owner.getId());
    }

    @Test
    void saveNoId(){
        Owner own= Owner.builder().build();
        Owner ownSaved=ownerMapService.save(own);
        assertNotNull(ownSaved);
        assertNotNull(ownSaved.getId());
    }

    @Test
    void findById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner own = ownerMapService.findByLastName(lastName);
        assertNotNull(own);
        assertNotNull(own.getLastName());
    }

    @Test
    void findByNameNotFound(){
        Owner own=ownerMapService.findByLastName("foo");
        assertNull(own);
    }
}