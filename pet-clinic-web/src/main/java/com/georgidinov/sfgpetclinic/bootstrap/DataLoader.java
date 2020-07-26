package com.georgidinov.sfgpetclinic.bootstrap;

import com.georgidinov.sfgpetclinic.model.Owner;
import com.georgidinov.sfgpetclinic.model.Vet;
import com.georgidinov.sfgpetclinic.services.OwnerService;
import com.georgidinov.sfgpetclinic.services.VetService;
import com.georgidinov.sfgpetclinic.services.map.OwnerServiceMap;
import com.georgidinov.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        this.ownerService = new OwnerServiceMap();
        this.vetService = new VetServiceMap();
    }//end of constructor


    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");

        this.ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");

        this.ownerService.save(owner2);
        System.out.println("Loaded Owners...");


        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Jessie");
        vet1.setLastName("Porter");
        
        this.vetService.save(vet1);


        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Sam");
        vet2.setLastName("Axe");

        this.vetService.save(vet2);
        System.out.println("Loaded Vets...");
        
    }//end of method run

}//end of class DataLoader
