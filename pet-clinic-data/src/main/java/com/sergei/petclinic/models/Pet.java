package com.sergei.petclinic.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseModel {
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits=new HashSet<>();

    @Builder
    public Pet(Long id, String name, Owner owner, PetType petType, LocalDate birthDate, Set<Visit> visits) {
        super(id);
        this.name = name;
        this.owner = owner;
        this.petType = petType;
        this.birthDate = birthDate;
        if(visits!=null) {
            this.visits = visits;
        }
    }
}
