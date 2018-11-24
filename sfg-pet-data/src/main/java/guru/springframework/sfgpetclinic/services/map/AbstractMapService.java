package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.models.BaseEntity;

import java.util.*;


/*
* entity type must extends Base Entity
* ID type must extend Long
* */


public abstract class AbstractMapService<T extends BaseEntity,ID extends Long> {
    protected Map<Long,T> map = new HashMap<>(); // adding and removing properties

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    T save(T object){
        if(object != null){
            if(object.getId() == null){
                object.setId(getNextId());
            }
            map.put(object.getId(),object);
        }else {
            throw new RuntimeException("Object cannot be null");
        }

        return object;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete (T object){
        // An interface should contain only one unimplemented function for that to work.
        // In that case we can have lambda expression.
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    Long getNextId(){
        Long nextId = null;
        try {
            nextId = Collections.max(map.keySet()) + 1;
        }catch (NoSuchElementException e){
            nextId=1l;
        }
        return nextId + 1;
    }
}
