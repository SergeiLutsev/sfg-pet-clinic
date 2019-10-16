package com.sergei.petclinic.repisitories;

import com.sergei.petclinic.models.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepository extends CrudRepository<Speciality,Long> {
}
