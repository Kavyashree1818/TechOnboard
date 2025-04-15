package shelter;

import animals.Animal;
import java.util.ArrayList;
import java.util.List;

public class Shelter {
    private List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void viewAnimals() {
        if (animals.isEmpty()) {
            System.out.println("No animals in the shelter.");
            return;
        }
        System.out.println("\nList of Animals in Shelter:");
        for (Animal a : animals) {
            a.displayInfo();
        }
    }

    public Animal findAnimalByName(String name) {
        for (Animal a : animals) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    public void updateAnimalHealth(String name, String newStatus) {  // update adoption status after adoption 
        Animal a = findAnimalByName(name);
        if (a != null) {
            a.updateHealthStatus(newStatus);
            System.out.println("Updated " + name + "'s health status to \"" + newStatus + "\".");
        } else {
            System.out.println("Animal not found: " + name);
        }
    }

    public List<Animal> getAllAnimals() {
        return animals;
    }
}
