package com.georgidinov.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

public class Person extends BaseEntity {

    //== fields ==
    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;


}//end of class Person
