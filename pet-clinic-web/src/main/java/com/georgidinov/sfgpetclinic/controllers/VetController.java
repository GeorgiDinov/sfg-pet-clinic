package com.georgidinov.sfgpetclinic.controllers;

import com.georgidinov.sfgpetclinic.model.Vet;
import com.georgidinov.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;


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
    @GetMapping({"/vets", "/vets/index", "/vets/index.html", "/vets.html"})
    public String listVets(Model model) {
        model.addAttribute("vets", this.vetService.findAll());
        return "vets/index";
    }//end of method vets

    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetsJson() {
        return this.vetService.findAll();
    }

}//end of class VetController
