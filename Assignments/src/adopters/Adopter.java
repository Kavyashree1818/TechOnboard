package adopters;

import animals.Animal;
import java.util.ArrayList;
import java.util.List;

public class Adopter {
    private int adopterId;
    private String name;
    private String contactInfo;
    private List<Animal> adoptedAnimals = new ArrayList<>();

    public Adopter(int adopterId, String name, String contactInfo) {
        this.adopterId = adopterId;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public void adoptAnimal(Animal animal) {
        if (!animal.isAdopted()) {
            animal.markAdopted();
            adoptedAnimals.add(animal);
            System.out.println(name + " has adopted " + animal.getName());
        } else {
            System.out.println(animal.getName() + " is already adopted.");
        }
    }

    public void displayAdoptedAnimals() {
        if (adoptedAnimals.isEmpty()) {
            System.out.println("No animals adopted yet.");
        } else {
            System.out.println("\nAdopted Animals by " + name + ":");
            for (Animal a : adoptedAnimals) {
                System.out.println(a.getName() + " (" + a.getSpecies() + ")");
            }
        }
    }

    public List<Animal> getAdoptedAnimals() {
        return adoptedAnimals;
    }
}
