package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.models.*;
import guru.springframework.sfgpetclinic.services.*;
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
    private final VisitService visitService;

    /*No autowired required, Spring will automaitcally configure*/

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }



    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }

    }

    private void loadData() {
        PetType petType = new PetType();
        petType.setName("Dog");
        PetType dog = petTypeService.save(petType);


        PetType petType1 = new PetType();
        petType1.setName("Cat");
        PetType cat = petTypeService.save(petType1);


        Owner owner1 = new Owner();
        owner1.setFirstName("John");
        owner1.setLastName("Doe");
        owner1.setAddress("1231");
        owner1.setCity("Bangalore");
        owner1.setTelephone("8283184712");

        Pet johnsPet = new Pet();
        johnsPet.setPetType(dog);
        johnsPet.setOwner(owner1);
        johnsPet.setBirthdate(LocalDate.now());
        johnsPet.setName("Cosco");
        owner1.getPets().add(johnsPet);
        ownerService.save(owner1);


        Visit visit1 = new Visit();
        visit1.setPet(johnsPet);
        visit1.setDescription("Its a sneeze dog");
        visitService.save(visit1);
        System.out.println("Saved Owner 1");

        Owner owner2 = new Owner();
        owner2.setFirstName("Shemrock");
        owner2.setLastName("Fine");
        owner2.setAddress("1231");
        owner2.setCity("Bangalore");
        owner2.setTelephone("8283184712");


        Pet shemrockPet = new Pet();
        shemrockPet.setPetType(cat);
        shemrockPet.setName("Nikai");
        shemrockPet.setOwner(owner2);
        shemrockPet.setBirthdate(LocalDate.now());
        owner2.getPets().add(shemrockPet);

        ownerService.save(owner2);

        Visit visit2 = new Visit();
        visit2.setPet(shemrockPet);
        visit2.setDescription("Its a sneeze cat !!");
        visitService.save(visit2);
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
