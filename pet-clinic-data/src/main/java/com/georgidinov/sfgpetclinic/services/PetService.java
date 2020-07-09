package com.georgidinov.sfgpetclinic.services;

import com.georgidinov.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save (Pet pet);

    Set<Pet> findAll();

}
