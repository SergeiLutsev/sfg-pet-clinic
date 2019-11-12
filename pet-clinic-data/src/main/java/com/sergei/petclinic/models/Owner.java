package com.sergei.petclinic.models;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "owners")
public class Owner extends Person {
    private String address;
    private String city;
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets=new HashSet<>();

@Builder
    public Owner(Long id, String firstName, String lastName, String address, String city, String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        if(pets!=null) {
            this.pets = pets;
        }
    }

    public Pet getPet(String name){
        return getPet(name, false);
    }

    public Pet getPet(String name, boolean ignoreNew) {
        name=name.toLowerCase();

        for (Pet pet: pets){
            if(!ignoreNew || !pet.isNew()){
                String compName=pet.getName();
                if(compName.toLowerCase().equals(name)){
                    return pet;
                }
            }
        }
        return null;
    }

}
