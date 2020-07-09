package com.georgidinov.sfgpetclinic.services;

import com.georgidinov.sfgpetclinic.model.Vet;

import java.util.Set;

public interface VetService {
   Vet findById(Long id);

    Vet save (Vet vet);

    Set<Vet> findAll();
}
