package com.georgidinov.sfgpetclinic.repositories;

import com.georgidinov.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}//end of interface PetTypeRepository
