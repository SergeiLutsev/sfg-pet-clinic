package com.sergei.petclinic.controllers;

import com.sergei.petclinic.models.Vet;
import com.sergei.petclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping({"/vets","/vets/index","/vets/index.html","/vets.html"})
    public String vetsList(Model model){
        model.addAttribute("vets",vetService.findAll());
        return "vets/index";
    }

    @GetMapping({"/api/vets"})
    @ResponseBody
    public Set<Vet> getVetsJSON(){
        Set<Vet> vets = vetService.findAll();
        return vets;
    }

}
