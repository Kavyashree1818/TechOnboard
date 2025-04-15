package main;

import java.util.*;
import animals.*;
import shelter.Shelter;
import staff.Staff;
import adopters.Adopter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Shelter shelter = new Shelter();

        // Add Dog details
        System.out.println("Add a Dog");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        double age = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Breed: ");
        String breed = scanner.nextLine();

        boolean trained = false;
        while (true) {
            System.out.print("Trained (true/false): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                trained = Boolean.parseBoolean(input);
                break;
            } else {
                System.out.println("Please enter 'true' or 'false'.");
            }
        }
        System.out.print("Health Status: ");
        String healthStatus = scanner.nextLine();

        Dog dog = new Dog(name, age, healthStatus, breed, trained);
        shelter.addAnimal(dog);

        // Add Cat details
        System.out.println("\nAdd a Cat");
        System.out.print("Name: ");
        name = scanner.nextLine();
        System.out.print("Age: ");
        age = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();

        boolean indoor = false;
        while (true) {
            System.out.print("Indoor (true/false): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                indoor = Boolean.parseBoolean(input);
                break;
            } else {
                System.out.println("Please enter 'true' or 'false'.");
            }
        }
        System.out.print("Health Status: ");
        String healthStatus1 = scanner.nextLine();

        Cat cat = new Cat(name, age, healthStatus1, color, indoor);
        shelter.addAnimal(cat);

        // Add Bird details
        System.out.println("\nAdd a Bird");
        System.out.print("Name: ");
        name = scanner.nextLine();
        System.out.print("Age: ");
        age = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Wing Span (in meters): ");
        double wingSpan = Double.parseDouble(scanner.nextLine());
        System.out.print("Health Status: ");
        String healthStatus2 = scanner.nextLine();

        boolean canFly = false;
        while (true) {
            System.out.print("Can Fly (true/false): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                canFly = Boolean.parseBoolean(input);
                break;
            } else {
                System.out.println("Please enter 'true' or 'false'.");
            }
        }

        Bird bird = new Bird(name, age, healthStatus2, wingSpan, canFly);
        shelter.addAnimal(bird);

        // Display all animals details
        System.out.println("\n--- All Animals ---");
        shelter.viewAnimals();

        // Add Staff member
        System.out.println("\nAdd a Staff Member");
        System.out.print("Staff ID: ");
        int staffId = Integer.parseInt(scanner.nextLine());
        System.out.print("Name: ");
        String staffName = scanner.nextLine();
        System.out.print("Role: ");
        String role = scanner.nextLine();

        Staff staff = new Staff(staffId, staffName, role);
        System.out.print("Enter task for staff: ");
        String task = scanner.nextLine();
        staff.assignTask(task);
        staff.displayTasks();

        // Add Adopter and Process Adoption
        System.out.println("\nAdd an Adopter");
        System.out.print("Adopter ID: ");
        int adopterId = Integer.parseInt(scanner.nextLine());
        System.out.print("Name: ");
        String adopterName = scanner.nextLine();
        System.out.print("Contact Info: ");
        String contactInfo = scanner.nextLine();

        Adopter adopter = new Adopter(adopterId, adopterName, contactInfo);

        System.out.print("Enter name of animal to adopt: ");
        String animalToAdopt = scanner.nextLine();
        Animal animal = shelter.findAnimalByName(animalToAdopt);

        if (animal != null) {
            adopter.adoptAnimal(animal);
        } else {
            System.out.println("Animal not found.");
        }

        // Display adopted animals
        adopter.displayAdoptedAnimals();

        // Update Health Status of Adopted Animal
        if (!adopter.getAdoptedAnimals().isEmpty()) {
            Animal adoptedAnimal = adopter.getAdoptedAnimals().get(0);
            System.out.print("\nEnter new health status for " + adoptedAnimal.getName() + ": ");
            String newHealth = scanner.nextLine();
            shelter.updateAnimalHealth(adoptedAnimal.getName(), newHealth);
        }

        // Display updated animal statuses
        System.out.println("\n--- Updated Animal Statuses ---");
        for (Animal a : shelter.getAllAnimals()) {
            String status = a.isAdopted() ? "Adopted" : "Available";
            System.out.println(a.getName() + " (" + a.getSpecies() + ") - " + status);
        }

        scanner.close();
    }
}
