package com.sergei.petclinic.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "types")
public class PetType extends BaseModel {
    private String name;


    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }




    @Override
    public String toString() {
        return getName();
    }
}
