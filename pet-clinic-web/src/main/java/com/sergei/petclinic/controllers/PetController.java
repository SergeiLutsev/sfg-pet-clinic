package com.sergei.petclinic.controllers;

import com.sergei.petclinic.models.Owner;
import com.sergei.petclinic.models.Pet;
import com.sergei.petclinic.models.PetType;
import com.sergei.petclinic.services.OwnerService;
import com.sergei.petclinic.services.PetService;
import com.sergei.petclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;


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
    public Collection<PetType> populatePetTypes(){
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

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model){
        Pet pet=new Pet();
        pet.setOwner(owner);
        owner.getPets().add(pet);
        model.addAttribute("pet",pet);
        return VIEW_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, Model model){
        if(StringUtils.hasLength(pet.getName()) && owner.getPet(pet.getName(),true)!=null){
            result.rejectValue("name","duplicate", "already exist");
        }
        owner.getPets().add(pet);
        if(result.hasErrors()){
            model.addAttribute("pet",pet);
            return  VIEW_PETS_CREATE_OR_UPDATE_FORM;
        }else{
            pet.setOwner(owner);
            petService.save(pet);
            return "redirect:/owners/"+owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId,Model model){
        Pet pet=petService.findById(petId);
        model.addAttribute("pet",pet);
        return VIEW_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model){
        if(result.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet",pet);
            return VIEW_PETS_CREATE_OR_UPDATE_FORM;
        }else {
          //  owner.getPets().add(pet);
            pet.setOwner(owner);
            petService.save(pet);
            return "redirect:/owners/"+owner.getId();
        }
    }
}
