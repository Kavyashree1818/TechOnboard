package animals;

public class Bird extends Animal {
	private double wingSpan;
	private boolean canFly;

	public Bird(String name, double age, String healthStatus, double wingSpan, boolean canFly) {
		super(name, "Bird", age, healthStatus);
		this.wingSpan = wingSpan;
		this.canFly = canFly;
	}

	public void displayInfo() { // display info of birds
		String status = adoptionStatus ? "Adopted" : "Available";
		System.out.println(
				name + " - " + species + " - Wing Span: " + wingSpan + "m, Can Fly: " + canFly + ", Status: " + status);
	}
}
