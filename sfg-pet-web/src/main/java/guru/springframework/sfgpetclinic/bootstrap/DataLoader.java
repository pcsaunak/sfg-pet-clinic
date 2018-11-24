package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.models.Owner;
import guru.springframework.sfgpetclinic.models.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;

public class DataLoader implements CommandLineRunner {

    /*
    * The basic idea is to initate some data when the application loads.
    *
    * After the spring application boots up, this will execute
    * */

    private final OwnerService ownerService;
    private final VetService vetService;


    /*No autowired required, Spring will automaitcally configure*/

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }


    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("John");
        owner1.setLastName("Doe");
        owner1.setId(1l);

        ownerService.save(owner1);

        System.out.println("Saved Owner 1");

        Owner owner2 = new Owner();
        owner2.setId(2l);
        owner2.setFirstName("Shemrock");
        owner2.setLastName("Fine");

        ownerService.save(owner2);
        System.out.println("Saved Owner 2");

        Vet vet1 = new Vet();
        vet1.setId(1l);
        vet1.setFirstName("Roman");
        vet1.setLastName("Nermar");

        vetService.save(vet1);

        System.out.println("Saved vet - 1");

        Vet vet2 = new Vet();
        vet2.setId(2l);
        vet2.setFirstName("Suram");
        vet2.setLastName("Zoram");

        vetService.save(vet2);

        System.out.println("Saved Vet - 2");
    }
}
