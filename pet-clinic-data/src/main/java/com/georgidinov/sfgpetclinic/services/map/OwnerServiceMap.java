package com.georgidinov.sfgpetclinic.services.map;

import com.georgidinov.sfgpetclinic.model.Owner;
import com.georgidinov.sfgpetclinic.model.Pet;
import com.georgidinov.sfgpetclinic.services.OwnerService;
import com.georgidinov.sfgpetclinic.services.PetService;
import com.georgidinov.sfgpetclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    //== fields ==
    private final PetTypeService petTypeService;
    private final PetService petService;


    //== constructors ==
    @Autowired
    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }//end of constructors


    //== public methods ==
    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if (object != null) {
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null) {
                        if (pet.getPetType().getId() == null) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet Type is required!");
                    }
                    if (pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        } else {
            return null;
        }
    }//end of method save

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    //todo: needs to be implemented
    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }

}//end of class OwnerServiceMap
