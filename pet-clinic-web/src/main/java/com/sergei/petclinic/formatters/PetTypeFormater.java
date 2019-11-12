package com.sergei.petclinic.formatters;

import com.sergei.petclinic.models.PetType;
import com.sergei.petclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormater implements Formatter<PetType> {
    private final PetTypeService petTypeService;

    public PetTypeFormater(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String s, Locale locale) throws ParseException {
        Collection<PetType> findPetType=petTypeService.findAll();
        for(PetType type : findPetType){
            if(type.getName().equals(s)){
                return type;
            }
        }
        throw new ParseException("type not found: "+s,0);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
