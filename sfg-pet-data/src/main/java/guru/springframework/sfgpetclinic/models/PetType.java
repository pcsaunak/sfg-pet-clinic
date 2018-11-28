package guru.springframework.sfgpetclinic.models;

public class PetType extends BaseEntity{
    private String petType;

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petName) {
        this.petType = petName;
    }
}
