package animals;

public class Dog extends Animal {
    private String breed;
    private boolean trained;

    public Dog(String name, double age, String healthStatus, String breed, boolean trained) {
        super(name, "Dog", age, healthStatus);
        this.breed = breed;
        this.trained = trained;
    }

    public void displayInfo() { // display info of dog
        String status = adoptionStatus ? "Adopted" : "Available";
        System.out.println(name + " - " + species + " - Breed: " + breed + ", Trained: " + trained + ", Status: " + status);
    }
}
