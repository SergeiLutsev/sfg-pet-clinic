package com.sergei.petclinic.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "vets")
public class Vet extends Person {
    @ManyToMany
    @JoinTable(name = "vet_specialitiy", joinColumns = @JoinColumn(name="vet_id"),
                inverseJoinColumns = @JoinColumn(name="specialitiy_id"))
    private Set<Speciality> specialities = new HashSet<>();

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
