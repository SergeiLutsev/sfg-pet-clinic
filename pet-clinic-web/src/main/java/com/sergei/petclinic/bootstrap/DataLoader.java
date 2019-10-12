package com.sergei.petclinic.bootstrap;

import com.sergei.petclinic.models.Owner;
import com.sergei.petclinic.models.Pet;
import com.sergei.petclinic.models.PetType;
import com.sergei.petclinic.models.Vet;
import com.sergei.petclinic.services.OwnerService;
import com.sergei.petclinic.services.PetTypeService;
import com.sergei.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadPetType();
        loadOwner();
        loadVet();

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

        System.out.println("Owners was loaded....");
    }

    private void loadVet(){
        Vet vet=new Vet();
        vet.setFirstName("Isabelle");
        vet.setLastName("Carrer");

        vetService.save(vet);

        Vet vet2=new Vet();
        vet2.setFirstName("Ai");
        vet2.setLastName("Bolit");

        vetService.save(vet2);

        System.out.println("Vets was loaded....");
    }

}
