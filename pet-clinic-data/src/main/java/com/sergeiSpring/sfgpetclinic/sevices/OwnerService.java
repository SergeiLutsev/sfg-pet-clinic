package com.sergeiSpring.sfgpetclinic.sevices;

import com.sergeiSpring.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner,Long>{
    Owner findByLastName(String lastName);

}
