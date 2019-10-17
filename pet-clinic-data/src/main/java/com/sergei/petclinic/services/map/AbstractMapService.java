package com.sergei.petclinic.services.map;

import com.sergei.petclinic.models.BaseModel;

import java.util.*;

public abstract class AbstractMapService<T extends BaseModel, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    protected Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    protected T findById(ID id) {
        return map.get(id);
    }

    protected T save(T object) {
        if(object!=null){
            if(object.getId()==null){
                object.setId(getNextId());
            }
            map.put(object.getId(), object);

        }else {
            throw new RuntimeException("Object can't be null");
        }


        return object;
    }
    protected void deleteById(ID id) {
        map.remove(id);
    }

    protected void delete(T object) {
        map.entrySet().removeIf(e -> e.getValue().equals(object));
    }

    private Long getNextId(){
        if(map.size()==0){
            return 1L;
        }else{
            return Collections.max(map.keySet())+1;
        }
    }
}
