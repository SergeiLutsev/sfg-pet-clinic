package com.sergei.petclinic.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vets")
public class Vet extends Person {
    @ManyToMany
    @JoinTable(name = "vet_specialitiy", joinColumns = @JoinColumn(name="vet_id"),
                inverseJoinColumns = @JoinColumn(name="specialitiy_id"))
    private Set<Speciality> specialities = new HashSet<>();

}
