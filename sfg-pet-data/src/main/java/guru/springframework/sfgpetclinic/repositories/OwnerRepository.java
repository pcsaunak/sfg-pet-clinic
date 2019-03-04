package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.models.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner,Long> {
    Owner findByLastName(String lastName);
    // This is going to be using the dynamic query of JPA
}

