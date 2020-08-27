package com.georgidinov.sfgpetclinic.services.springdatajpa;

import com.georgidinov.sfgpetclinic.model.Owner;
import com.georgidinov.sfgpetclinic.repositories.OwnerRepository;
import com.georgidinov.sfgpetclinic.repositories.PetRepository;
import com.georgidinov.sfgpetclinic.repositories.PetTypeRepository;
import com.georgidinov.sfgpetclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

    //== fields ==
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;


    //== constructors ==
    @Autowired
    public OwnerSDJpaService(OwnerRepository ownerRepository,
                             PetRepository petRepository, PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }//end of constructor


    //== public methods ==
    @Override
    public Owner findByLastName(String lastName) {
        return this.ownerRepository.findByLastName(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        this.ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long aLong) {
        return this.ownerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return this.ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        this.ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        this.ownerRepository.deleteById(aLong);
    }


    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

}//end of class OwnerSDJpaService
