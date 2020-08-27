package com.georgidinov.sfgpetclinic.controllers;

import com.georgidinov.sfgpetclinic.model.Owner;
import com.georgidinov.sfgpetclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    //== constants ==
    public static final String VIES_OWNER_CREATE_OR_UPDATE_FORM =
            "owners/createOrUpdateOwnerForm";

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
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(this.ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return VIES_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Validated Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIES_OWNER_CREATE_OR_UPDATE_FORM;
        }
        Owner savedOwner = ownerService.save(owner);
        String redirection = "/owners/" + savedOwner.getId();
        return "redirect:" + redirection;
    }


    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return VIES_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Validated Owner owner, BindingResult result,
                                         @PathVariable Long ownerId) {
        if (result.hasErrors()) {
            return VIES_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            String redirection = "/owners/" + savedOwner.getId();
            return "redirect:" + redirection;
        }
    }


}//end of class OwnerController
