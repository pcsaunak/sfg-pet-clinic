package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.models.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
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
    private final SpecialtyService specialtyService;

    /*No autowired required, Spring will automaitcally configure*/

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }


    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);


        PetType cat = new PetType();
        dog.setName("Cat");
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

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialtyService.save(dentistry);


        Vet vet1 = new Vet();

        vet1.setFirstName("Roman");
        vet1.setLastName("Nermar");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        System.out.println("Saved vet - 1");

        Vet vet2 = new Vet();
        vet2.setFirstName("Suram");
        vet2.setLastName("Zoram");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Saved Vet - 2");
    }
}
