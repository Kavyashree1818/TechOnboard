package animals;

public abstract class Animal {  //Base for other classes to inherit from
    protected String name;
    protected String species;
    protected double age;
    protected String healthStatus;
    protected boolean adoptionStatus;

    public Animal(String name, String species, double age, String healthStatus) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.healthStatus = healthStatus;
        this.adoptionStatus = false;
    }

    public abstract void displayInfo();

    public void updateHealthStatus(String status) {
        this.healthStatus = status; // sets initial health status value for health
    }

    public void markAdopted() {
        this.adoptionStatus = true; // sets initial adoption status value for adoption
    }

    public boolean isAdopted() {
        return adoptionStatus; // returns true or false w.r.t adoption
    }

    public String getName() {
        return name; // get the name of the species
    }

    public String getSpecies() {
        return species; // get the the species
    }
}
