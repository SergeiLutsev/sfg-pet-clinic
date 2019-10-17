package com.sergei.petclinic.bootstrap;

import com.sergei.petclinic.models.*;
import com.sergei.petclinic.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count=petTypeService.findAll().size();
        if(count == 0) {
            loadSpeciality();
            loadPetType();
            loadOwner();
            loadVet();
        }

    }

    private void loadSpeciality() {
        Speciality radiology=new Speciality();
        radiology.setDescription("radiology");
        specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("surgery");
        specialityService.save(surgery);

        Speciality dantistry = new Speciality();
        dantistry.setDescription("dantistry");
        specialityService.save(dantistry);

    }

    private void loadPetType() {
        PetType dog=new PetType();
        dog.setName("Dog");
        PetType dogSave=petTypeService.save(dog);

        PetType cat=new PetType();
        cat.setName("Cat");
        PetType catSave=petTypeService.save(cat);

        System.out.println("Pet type was loaded.....");
    }

    private void loadOwner(){



        Owner owner=new Owner();
        owner.setFirstName("Sergei");
        owner.setLastName("Lutsev");
        owner.setAddress("95 rue du Conservatoire");
        owner.setCity("Gatineau");
        owner.setTelephone("849356984");

        Pet serPet=new Pet();
        serPet.setName("Murka");
        serPet.setOwner(owner);
        serPet.setPetType(petTypeService.findById(2l));
        serPet.setBirthDate(LocalDate.now());
        owner.getPets().add(serPet);
        ownerService.save(owner);

        Visit visitSerPet=new Visit();
        visitSerPet.setPet(serPet);
        visitSerPet.setDate(LocalDate.now());
        visitSerPet.setDescription("Sneezzzy cat");
        visitService.save(visitSerPet);

//-------------------------------------------------------------------------
        Owner owner2=new Owner();
        owner2.setFirstName("Vadim");
        owner2.setLastName("Dombrovski");
        owner2.setAddress("116 rue Breach");
        owner2.setCity("Gatineau");
        owner2.setTelephone("819654875");

        Pet vadimPet=new Pet();
        vadimPet.setName("Rex");
        vadimPet.setOwner(owner2);
        vadimPet.setPetType(petTypeService.findById(2l));
        vadimPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(vadimPet);
        ownerService.save(owner2);

        Visit visitVadimPet=new Visit();
        visitVadimPet.setPet(vadimPet);
        visitVadimPet.setDate(LocalDate.now());
        visitVadimPet.setDescription("Hiperactive dog");
        visitService.save(visitVadimPet);

        System.out.println("Owners was loaded....");
    }

    private void loadVet(){
        Vet vet=new Vet();
        vet.setFirstName("Isabelle");
        vet.setLastName("Carrer");
        vet.getSpecialities().add(specialityService.findById(1L));
        vetService.save(vet);

        Vet vet2=new Vet();
        vet2.setFirstName("Ai");
        vet2.setLastName("Bolit");
        vet2.getSpecialities().add(specialityService.findById(2l));
        vetService.save(vet2);

        System.out.println("Vets was loaded....");
    }

}
