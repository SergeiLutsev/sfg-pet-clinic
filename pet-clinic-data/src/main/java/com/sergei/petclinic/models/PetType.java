package com.sergei.petclinic.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "types")
public class PetType extends BaseModel {
    private String name;

    @Override
    public String toString() {
        return getName();
    }
}
