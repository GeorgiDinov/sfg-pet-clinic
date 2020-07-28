package com.georgidinov.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person {

    @Getter
    @Setter
    private Set<Speciality> specialities = new HashSet<>();

}//end of class Vet
