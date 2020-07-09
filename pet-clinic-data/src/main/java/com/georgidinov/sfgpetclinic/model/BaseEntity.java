package com.georgidinov.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    //== constants ==
    private static final long serialVersionUID = 1L;

    //== fields ==
    @Getter
    @Setter
    private Long id;


}
