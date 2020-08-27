package com.georgidinov.sfgpetclinic.controllers;

import com.georgidinov.sfgpetclinic.model.Owner;
import com.georgidinov.sfgpetclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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


    //== web data binder ==
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    //== public methods ==
    @RequestMapping({"/find"})
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {

        if (owner.getLastName() == null) {
            owner.setLastName("");
        }
        List<Owner> ownersList = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (ownersList.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (ownersList.size() == 1) {
            owner = ownersList.get(0);
            String redirection = "/owners/" + owner.getId();
            return "redirect:" + redirection;
        } else {
            model.addAttribute("selections", ownersList);
            return "owners/ownersList";
        }
    }//end of method processFindForm

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(this.ownerService.findById(ownerId));
        return mav;
    }


}//end of class OwnerController
