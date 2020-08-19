package com.georgidinov.sfgpetclinic.bootstrap;

import com.georgidinov.sfgpetclinic.model.Owner;
import com.georgidinov.sfgpetclinic.model.Pet;
import com.georgidinov.sfgpetclinic.model.PetType;
import com.georgidinov.sfgpetclinic.model.Speciality;
import com.georgidinov.sfgpetclinic.model.Vet;
import com.georgidinov.sfgpetclinic.model.Visit;
import com.georgidinov.sfgpetclinic.services.OwnerService;
import com.georgidinov.sfgpetclinic.services.PetTypeService;
import com.georgidinov.sfgpetclinic.services.SpecialityService;
import com.georgidinov.sfgpetclinic.services.VetService;
import com.georgidinov.sfgpetclinic.services.VisitService;
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
    private final SpecialityService specialityService;
    private final VisitService visitService;


    //== constructors ==
    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }//end of constructor


    //== public methods ==
    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }

    }//end of method run


    //== private methods ==
    private void loadData() {
        //== creating petTypes ==
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = this.petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = this.petTypeService.save(cat);

        //== creating specialities ==
        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = this.specialityService.save(radiology);

        Speciality surgery = new Speciality();
        radiology.setDescription("Surgery");
        Speciality savedSurgery = this.specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        radiology.setDescription("Dentistry");
        Speciality savedDentistry = this.specialityService.save(dentistry);

        //== creating owner ==
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickrel");
        owner1.setCity("Miami");
        owner1.setTelephone("123456789");



        //== creating owner pet ==
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosko");
        owner1.getPets().add(mikesPet);

        this.ownerService.save(owner1);

        //== creating another owner ==
        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickrel");
        owner2.setCity("Miami");
        owner2.setTelephone("987654321");

        //== creating pet for the second owner ==
        Pet fionasCat = new Pet();
        fionasCat.setPetType(savedCatPetType);
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setName("Just Cat");
        owner2.getPets().add(fionasCat);

        this.ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(fionasCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");
        visitService.save(catVisit);


        System.out.println("Loaded Owners...");


        //== creating vets ==
        Vet vet1 = new Vet();
        vet1.setFirstName("Jessie");
        vet1.setLastName("Porter");
        vet1.getSpecialities().add(savedSurgery);

        this.vetService.save(vet1);


        Vet vet2 = new Vet();
        vet2.setFirstName("Sam");
        vet2.setLastName("Axe");
        vet2.getSpecialities().add(savedRadiology);

        this.vetService.save(vet2);
        System.out.println("Loaded Vets...");
    }//end of method loadData

}//end of class DataLoader
