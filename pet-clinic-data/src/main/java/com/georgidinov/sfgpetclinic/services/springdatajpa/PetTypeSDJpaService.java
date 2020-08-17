package com.georgidinov.sfgpetclinic.services.springdatajpa;

import com.georgidinov.sfgpetclinic.model.PetType;
import com.georgidinov.sfgpetclinic.repositories.PetTypeRepository;
import com.georgidinov.sfgpetclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetTypeSDJpaService implements PetTypeService {


    //== fields ==
    private final PetTypeRepository petTypeRepository;


    //== constructors ==
    @Autowired
    public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }


    //== public methods ==
    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        this.petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long aLong) {
        return this.petTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return this.petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        this.petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        this.petTypeRepository.deleteById(aLong);
    }

}//end of class PetTypeSDJpaService
