package com.sergei.petclinic.controllers;

import com.sergei.petclinic.models.Pet;
import com.sergei.petclinic.models.Visit;
import com.sergei.petclinic.services.PetService;
import com.sergei.petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class VisitController {
    private final PetService petService;
    private final VisitService visitService;

    public VisitController(PetService petService, VisitService visitService) {
        this.petService = petService;
        this.visitService = visitService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model){
        Pet pet=petService.findById(petId);
        model.addAttribute("pet",pet);
        Visit visit=new Visit();
        visit.setPet(pet);
        pet.getVisits().add(visit);
        return visit;
    }

    //Spring MVC calls method loadPetWithVisit(..) before initNewVisitsForm is called
    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitsForm(@PathVariable Long petId, Model model){
        return "pets/createOrUpdateVisitForm";
    }

    //Spring MVC calls method loadPetWithVisit(..) before processNewVisitsForm is called
    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitsForm(@Valid Visit visit, BindingResult result,@PathVariable String ownerId){
        if(result.hasErrors()){
            return "pets/createOrUpdateVisitForm";
        }else{
            visitService.save(visit);
            return "redirect:/owners/"+ownerId;
        }
    }


}
