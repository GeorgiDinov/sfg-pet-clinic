package com.georgidinov.sfgpetclinic.services;

import com.georgidinov.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}//end of interface OwnerService
