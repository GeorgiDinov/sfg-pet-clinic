package com.georgidinov.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity {

    //== fields ==
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    //== constructors ==
    public Person(Long id, String firstName, String lastName) {                                       
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }//end of constructor

}//end of class Person
