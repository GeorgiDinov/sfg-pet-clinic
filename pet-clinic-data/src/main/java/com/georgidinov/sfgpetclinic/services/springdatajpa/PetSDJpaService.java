package com.georgidinov.sfgpetclinic.services.springdatajpa;

import com.georgidinov.sfgpetclinic.model.Pet;
import com.georgidinov.sfgpetclinic.repositories.PetRepository;
import com.georgidinov.sfgpetclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {

    //== fields ==
    private final PetRepository petRepository;


    //== constructors ==
    @Autowired
    public PetSDJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }//end of constructor


    //== public methods ==
    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        this.petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long aLong) {
        return this.petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return this.petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        this.petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        this.petRepository.deleteById(aLong);
    }

}//end of class PetSDJpaService
