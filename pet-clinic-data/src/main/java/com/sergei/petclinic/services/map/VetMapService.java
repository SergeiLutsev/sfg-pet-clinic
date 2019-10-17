package com.sergei.petclinic.services.map;

import com.sergei.petclinic.models.Speciality;
import com.sergei.petclinic.models.Vet;
import com.sergei.petclinic.services.SpecialityService;
import com.sergei.petclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetMapService extends AbstractMapService<Vet,Long> implements VetService {
    private final SpecialityService specialityService;

    public VetMapService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

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
        if(object==null){
            return null;
        }
        if(object.getSpecialities()!=null){
            object.getSpecialities().forEach(speciality -> {
                if(speciality.getId()==null){
                   Speciality spec = specialityService.save(speciality);
                   speciality.setId(spec.getId());
                }
            });

        }
        return super.save(object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
