package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.models.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetypeRepository extends CrudRepository<PetType,Long> {
}
