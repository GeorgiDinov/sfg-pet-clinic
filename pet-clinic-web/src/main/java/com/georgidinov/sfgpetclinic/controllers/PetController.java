package com.georgidinov.sfgpetclinic.controllers;

import com.georgidinov.sfgpetclinic.model.Owner;
import com.georgidinov.sfgpetclinic.model.Pet;
import com.georgidinov.sfgpetclinic.model.PetType;
import com.georgidinov.sfgpetclinic.services.OwnerService;
import com.georgidinov.sfgpetclinic.services.PetService;
import com.georgidinov.sfgpetclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    //== constants ==
    private static final String VIEW_PETS_CREATE_OR_UPDATE_FORM =
            "pets/createOrUpdatePetForm";

    //== fields ==
    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    //== constructors ==
    @Autowired
    public PetController(OwnerService ownerService,
                         PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }//end of constructor


    //== init data binder ==
    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    //== model attributes ==
    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return this.petTypeService.findAll();
    }


    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return this.ownerService.findById(ownerId);
    }

    //== public methods ==

    //== add new pet ==
    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEW_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet,
                                      BindingResult result, Model model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() &&
                owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.getPets().add(pet);
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEW_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            this.petService.save(pet);
            String redirection = "/owners/" + owner.getId();
            return "redirect:" + redirection;
        }
    }


    //== update existing pet ==
    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", this.petService.findById(petId));
        return VIEW_PETS_CREATE_OR_UPDATE_FORM;
    }


    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result,
                                    Owner owner, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEW_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            owner.getPets().add(pet);
            this.petService.save(pet);
            String redirection = "/owners/" + owner.getId();
            return "redirect:" + redirection;
        }
    }


}//end of class PetController
