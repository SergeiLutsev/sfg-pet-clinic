package com.sergeiSpring.sfgpetclinic.sevices.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractServiceMap<T,ID> {
    private Map<ID,T> map=new HashMap<>();

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T object){
        map.entrySet().removeIf(e->e.getValue().equals(object));
    }

    T save(ID id,T object){
        map.put(id,object);
        return object;
    }

}
