package com.sergei.petclinic.services.springdatajpa;

import com.sergei.petclinic.models.Owner;
import com.sergei.petclinic.repisitories.OwnerRepository;
import com.sergei.petclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners=new HashSet<>();
       // ownerRepository.findAll().iterator().forEachRemaining(owners::add);
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long id) {
        Optional<Owner> ownerOptional=ownerRepository.findById(id);
//        if(ownerOptional.isPresent()){
//            return ownerOptional.get();
//        }else{
//            return null;
//        }
        return ownerOptional.orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
            ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
            ownerRepository.deleteById(id);
    }
}
