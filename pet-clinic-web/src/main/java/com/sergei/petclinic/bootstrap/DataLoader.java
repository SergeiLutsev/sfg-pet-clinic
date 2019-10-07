package com.sergei.petclinic.bootstrap;

import com.sergei.petclinic.models.Owner;
import com.sergei.petclinic.models.Vet;
import com.sergei.petclinic.services.OwnerService;
import com.sergei.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadOwner();
        loadVet();
    }

    private void loadOwner(){
        Owner owner=new Owner();
        owner.setFirstName("Sergei");
        owner.setLastName("Lutsev");

        ownerService.save(owner);

        Owner owner2=new Owner();
        owner2.setFirstName("Vadim");
        owner2.setLastName("Dombrovski");

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
