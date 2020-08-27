package com.georgidinov.sfgpetclinic.services;

import com.georgidinov.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);

}//end of interface OwnerService
