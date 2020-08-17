package com.georgidinov.sfgpetclinic.repositories;

import com.georgidinov.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}//end of interface OwnerRepository