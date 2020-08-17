package com.georgidinov.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "specialities")
public class Speciality extends BaseEntity {

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

}//end of class Speciality
