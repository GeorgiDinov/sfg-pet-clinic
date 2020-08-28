package com.georgidinov.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

    //== fields ==
    @Column(name = "name")
    private String name;


    //== constructors ==
    @Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }//end of constructor

}//end of class PetType
