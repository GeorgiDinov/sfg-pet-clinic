package com.georgidinov.sfgpetclinic.repositories;

import com.georgidinov.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}//end of interface PetRepository
