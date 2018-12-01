package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.models.Owner;
import guru.springframework.sfgpetclinic.models.Pet;
import guru.springframework.sfgpetclinic.models.PetType;
import guru.springframework.sfgpetclinic.models.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    /*
    * The basic idea is to initiate some data when the application loads.
    *
    * After the spring application boots up, this will execute
    * */

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    /*No autowired required, Spring will automaitcally configure*/

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }


    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setPetType("Dog");
        PetType saveDogPetType = petTypeService.save(dog);


        PetType cat = new PetType();
        dog.setPetType("Cat");
        PetType saveCatPetType = petTypeService.save(cat);


        Owner owner1 = new Owner();
        owner1.setFirstName("John");
        owner1.setLastName("Doe");
        owner1.setAddress("1231");
        owner1.setCity("Bangalore");
        owner1.setTelephone("8283184712");

        Pet johnsPet = new Pet();
        johnsPet.setPetType(saveDogPetType);
        johnsPet.setOwner(owner1);
        johnsPet.setBirthdate(LocalDate.now());
        johnsPet.setName("Cosco");
        owner1.getPets().add(johnsPet);

        ownerService.save(owner1);

        System.out.println("Saved Owner 1");

        Owner owner2 = new Owner();
        owner2.setFirstName("Shemrock");
        owner2.setLastName("Fine");
        owner2.setAddress("1231");
        owner2.setCity("Bangalore");
        owner2.setTelephone("8283184712");


        Pet shemrockPet = new Pet();
        shemrockPet.setPetType(saveCatPetType);
        shemrockPet.setName("Nika");
        shemrockPet.setOwner(owner2);
        shemrockPet.setBirthdate(LocalDate.now());
        owner2.getPets().add(shemrockPet);

        ownerService.save(owner2);
        System.out.println("Saved Owner 2");

        Vet vet1 = new Vet();

        vet1.setFirstName("Roman");
        vet1.setLastName("Nermar");

        vetService.save(vet1);

        System.out.println("Saved vet - 1");

        Vet vet2 = new Vet();
        vet2.setFirstName("Suram");
        vet2.setLastName("Zoram");

        vetService.save(vet2);

        System.out.println("Saved Vet - 2");
    }
}
