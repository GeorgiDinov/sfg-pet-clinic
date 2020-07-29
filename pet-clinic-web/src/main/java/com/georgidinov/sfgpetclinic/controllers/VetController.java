package com.georgidinov.sfgpetclinic.controllers;

import com.georgidinov.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    //== fields ==
    private final VetService vetService;


    //== constructors ==
    @Autowired
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }//end of constructor


    //== public methods ==
    @RequestMapping({"/vets", "/vets/index", "/vets/index.html","/vets.html"})
    public String listVets(Model model) {
        model.addAttribute("vets", this.vetService.findAll());
        return "vets/index";
    }//end of method vets

}//end of class VetController
