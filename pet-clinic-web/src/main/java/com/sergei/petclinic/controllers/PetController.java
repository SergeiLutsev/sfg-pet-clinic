package com.sergei.petclinic.controllers;

import com.sergei.petclinic.models.Owner;
import com.sergei.petclinic.models.PetType;
import com.sergei.petclinic.services.OwnerService;
import com.sergei.petclinic.services.PetService;
import com.sergei.petclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("owners/{ownerId}")
public class PetController {
    private static final String VIEW_PETS_CREATE_OR_UPDATE_FORM="pets/createOrUpdatePetForm";

    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    private final PetService petService;

    public PetController(OwnerService ownerService, PetTypeService petTypeService, PetService petService) {
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @ModelAttribute("types")
    public Set<PetType> populatePetTypes(){
        return  petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId){
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }
}
