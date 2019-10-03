package com.sergeiSpring.sfgpetclinic.sevices.map;

import com.sergeiSpring.sfgpetclinic.model.Vet;
import com.sergeiSpring.sfgpetclinic.sevices.CrudService;

import java.util.Set;

public class VetServiceMap extends AbstractServiceMap<Vet,Long> implements CrudService<Vet,Long> {
    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(),object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
