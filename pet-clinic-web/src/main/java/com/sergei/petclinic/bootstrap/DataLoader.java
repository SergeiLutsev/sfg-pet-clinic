package com.sergei.petclinic.bootstrap;

import com.sergei.petclinic.models.Owner;
import com.sergei.petclinic.models.Vet;
import com.sergei.petclinic.services.OwnerService;
import com.sergei.petclinic.services.VetService;
import com.sergei.petclinic.services.map.OwnerServiceMap;
import com.sergei.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        loadOwner();
        loadVet();
    }

    private void loadOwner(){
        Owner owner=new Owner();
        owner.setId(1L);
        owner.setFirstName("Sergei");
        owner.setLastName("Lutsev");

        ownerService.save(owner);

        Owner owner2=new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Vadim");
        owner2.setLastName("Dombrovski");

        ownerService.save(owner2);

        System.out.println("Owners was loaded....");
    }

    private void loadVet(){
        Vet vet=new Vet();
        vet.setId(1L);
        vet.setFirstName("Isabelle");
        vet.setLastName("Carrer");

        vetService.save(vet);

        Vet vet2=new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Ai");
        vet2.setLastName("Bolit");

        vetService.save(vet2);

        System.out.println("Vets was loaded....");
    }

}
