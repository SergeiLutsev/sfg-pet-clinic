package com.sergei.petclinic.services;

import com.sergei.petclinic.models.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner,Long> {
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
