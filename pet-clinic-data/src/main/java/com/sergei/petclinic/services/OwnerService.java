package com.sergei.petclinic.services;

import com.sergei.petclinic.models.Owner;

public interface OwnerService extends CrudService<Owner,Long> {
    Owner findByLastName(String lastName);
}
