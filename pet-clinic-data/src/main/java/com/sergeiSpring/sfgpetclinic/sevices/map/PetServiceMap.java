package com.sergeiSpring.sfgpetclinic.sevices.map;

import com.sergeiSpring.sfgpetclinic.model.Pet;
import com.sergeiSpring.sfgpetclinic.sevices.CrudService;

import java.util.Set;

public class PetServiceMap extends AbstractServiceMap<Pet, Long>  implements CrudService<Pet, Long> {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
            super.deleteById(id);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(),object);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}
