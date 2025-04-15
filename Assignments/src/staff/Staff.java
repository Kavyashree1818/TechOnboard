package staff;

import java.util.ArrayList;
import java.util.List;

public class Staff {
    private int staffId;
    private String name;
    private String role;
    private List<String> tasks = new ArrayList<>();

    public Staff(int staffId, String name, String role) {
        this.staffId = staffId;
        this.name = name;
        this.role = role;
    }

    public void assignTask(String task) { // method to assign task to staff
        tasks.add(task);
        System.out.println("Task assigned to " + name + ": " + task);
    }

    public void displayTasks() { // method to display task assigned to staff
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
