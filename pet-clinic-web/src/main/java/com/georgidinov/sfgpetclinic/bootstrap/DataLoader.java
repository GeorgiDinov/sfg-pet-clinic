package com.georgidinov.sfgpetclinic.bootstrap;

import com.georgidinov.sfgpetclinic.model.Owner;
import com.georgidinov.sfgpetclinic.model.Pet;
import com.georgidinov.sfgpetclinic.model.PetType;
import com.georgidinov.sfgpetclinic.model.Vet;
import com.georgidinov.sfgpetclinic.services.OwnerService;
import com.georgidinov.sfgpetclinic.services.PetTypeService;
import com.georgidinov.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    //== fields ==
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;


    //== constructors ==
    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }//end of constructor


    //== public methods ==
    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = this.petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = this.petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickrel");
        owner1.setCity("Miami");
        owner1.setTelephone("123456789");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosko");
        owner1.getPets().add(mikesPet);

        this.ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickrel");
        owner2.setCity("Miami");
        owner2.setTelephone("987654321");

        Pet fionasCat = new Pet();
        fionasCat.setPetType(savedCatPetType);
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setName("Just Cat");
        owner1.getPets().add(fionasCat);

        this.ownerService.save(owner2);
        System.out.println("Loaded Owners...");


        Vet vet1 = new Vet();
        vet1.setFirstName("Jessie");
        vet1.setLastName("Porter");

        this.vetService.save(vet1);


        Vet vet2 = new Vet();
        vet2.setFirstName("Sam");
        vet2.setLastName("Axe");

        this.vetService.save(vet2);
        System.out.println("Loaded Vets...");

    }//end of method run

}//end of class DataLoader
