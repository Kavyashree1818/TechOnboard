// Import required classes
import java.util.*;

// Abstract base class for all animals
abstract class Animal {
    protected String name;
    protected String species;
    protected double age;
    protected String healthStatus;
    protected boolean adoptionStatus;

    // Constructor for the animal
    public Animal(String name, String species, double age, String healthStatus) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.healthStatus = healthStatus;
        this.adoptionStatus = false; // Animal is available by default
    }

    // Abstract method to be implemented by subclasses
    public abstract void displayInfo();

    // Method to update health status
    public void updateHealthStatus(String status) {
        this.healthStatus = status;
    }

    // Mark the animal as adopted
    public void markAdopted() {
        this.adoptionStatus = true;
    }

    // Check if animal is adopted
    public boolean isAdopted() {
        return adoptionStatus;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }
}

// Dog class extends Animal
class Dog extends Animal {
    private String breed;
    private boolean trained;

    public Dog(String name, double age, String healthStatus, String breed, boolean trained) {
        super(name, "Dog", age, healthStatus);
        this.breed = breed;
        this.trained = trained;
    }

    // Display information specific to Dog
    public void displayInfo() {
        String status = adoptionStatus ? "Adopted" : "Available";
        System.out.println(name + " - " + species + " - Breed: " + breed + ", Trained: " + trained + ", Status: " + status);
    }
}

// Cat class extends Animal
class Cat extends Animal {
    private String color;
    private boolean indoor;

    public Cat(String name, double age, String healthStatus, String color, boolean indoor) {
        super(name, "Cat", age, healthStatus);
        this.color = color;
        this.indoor = indoor;
    }

    // Display information specific to Cat
    public void displayInfo() {
        String status = adoptionStatus ? "Adopted" : "Available";
        System.out.println(name + " - " + species + " - Color: " + color + ", Indoor: " + indoor + ", Status: " + status);
    }
}

// Bird class extends Animal
class Bird extends Animal {
    private double wingSpan;
    private boolean canFly;

    public Bird(String name, double age, String healthStatus, double wingSpan, boolean canFly) {
        super(name, "Bird", age, healthStatus);
        this.wingSpan = wingSpan;
        this.canFly = canFly;
    }

    // Display information specific to Bird
    public void displayInfo() {
        String status = adoptionStatus ? "Adopted" : "Available";
        System.out.println(name + " - " + species + " - Wing Span: " + wingSpan + "m, Can Fly: " + canFly + ", Status: " + status);
    }
}

// Class representing staff members
class Staff {
    private int staffId;
    private String name;
    private String role;
    private List<String> tasks = new ArrayList<>();

    public Staff(int staffId, String name, String role) {
        this.staffId = staffId;
        this.name = name;
        this.role = role;
    }

    // Assign task to staff
    public void assignTask(String task) {
        tasks.add(task);
        System.out.println("Task assigned to " + name + ": " + task);
    }

    // Display all tasks assigned to staff
    public void displayTasks() {
        System.out.println("\nTasks assigned to " + name + " (" + role + "):");
        if (tasks.isEmpty()) {
            System.out.println("No tasks assigned.");
        } else {
            for (String task : tasks) {
                System.out.println("- " + task);
            }
        }
    }
}

// Class for people adopting animals
class Adopter {
    private int adopterId;
    private String name;
    private String contactInfo;
    private List<Animal> adoptedAnimals = new ArrayList<>();

    public Adopter(int adopterId, String name, String contactInfo) {
        this.adopterId = adopterId;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Adopt an animal
    public void adoptAnimal(Animal animal) {
        if (!animal.isAdopted()) {
            animal.markAdopted();
            adoptedAnimals.add(animal);
            System.out.println(name + " has adopted " + animal.getName());
        } else {
            System.out.println(animal.getName() + " is already adopted.");
        }
    }

    // Display all adopted animals
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

    // Get list of adopted animals
    public List<Animal> getAdoptedAnimals() {
        return adoptedAnimals;
    }
}

// Shelter class to manage all animals
class Shelter {
    private List<Animal> animals = new ArrayList<>();

    // Add animal to shelter
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    // View all animals in shelter
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

    // Find animal by name
    public Animal findAnimalByName(String name) {
        for (Animal a : animals) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    // Update animal health status
    public void updateAnimalHealth(String name, String newStatus) {
        Animal a = findAnimalByName(name);
        if (a != null) {
            a.updateHealthStatus(newStatus);
            System.out.println("Updated " + name + "'s health status to \"" + newStatus + "\".");
        } else {
            System.out.println("Animal not found: " + name);
        }
    }

    // Get all animals
    public List<Animal> getAllAnimals() {
        return animals;
    }
}

// Main class to run the program
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Shelter shelter = new Shelter();

        // --- Add a Dog ---
        System.out.println("Add a Dog");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        double age = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Breed: ");
        String breed = scanner.nextLine();

        // Loop until valid boolean input for trained
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

        // Create and add Dog
        Dog dog = new Dog(name, age, healthStatus, breed, trained);
        shelter.addAnimal(dog);

        // --- Add a Cat ---
        System.out.println("\nAdd a Cat");
        System.out.print("Name: ");
        name = scanner.nextLine();
        System.out.print("Age: ");
        age = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();

        // Loop until valid boolean input for indoor
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

        // Create and add Cat
        Cat cat = new Cat(name, age, healthStatus1, color, indoor);
        shelter.addAnimal(cat);

        // --- Add a Bird ---
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

        // Loop until valid boolean input for canFly
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

        // Create and add Bird
        Bird bird = new Bird(name, age, healthStatus2, wingSpan, canFly);
        shelter.addAnimal(bird);

        // Display all animals
        System.out.println("\n--- All Animals ---");
        shelter.viewAnimals();

        // --- Add a Staff Member ---
        System.out.println("\nAdd a Staff Member");
        System.out.print("Staff ID: ");
        int staffId = Integer.parseInt(scanner.nextLine());
        System.out.print("Name: ");
        String staffName = scanner.nextLine();
        System.out.print("Role: ");
        String role = scanner.nextLine();

        Staff staff = new Staff(staffId, staffName, role);

        // Assign task to staff
        System.out.print("Enter task for staff: ");
        String task = scanner.nextLine();
        staff.assignTask(task);
        staff.displayTasks();

        // --- Add Adopter and Process Adoption ---
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

        // --- Update Health Status of Adopted Animal ---
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

        scanner.close(); // Close scanner
    }
}
