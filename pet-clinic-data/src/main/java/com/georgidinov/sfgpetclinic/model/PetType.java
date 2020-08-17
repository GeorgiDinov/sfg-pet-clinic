package com.georgidinov.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {


    //== fields ==
    @Column(name = "name")
    private String name;

}//end of class PetType
