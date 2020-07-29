package com.georgidinov.sfgpetclinic.controllers;

import com.georgidinov.sfgpetclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    //== fields ==
    private final OwnerService ownerService;


    //== constructors ==
    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }//end of constructor


    //== public methods ==
    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model) {
        model.addAttribute("owners", this.ownerService.findAll());
        return "owners/index";
    }//end of method listOwners

    @RequestMapping({"/find"})
    public String findOwners(){
        return "notimplemented";
    }


}//end of class OwnerController
