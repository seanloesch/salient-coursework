/*
 * Sean Loesch 
 * 3/7/2022
 * CS 245-001
 * Assignment 1
 * 
 * The purpose of this class is to define the rabbit object used in the simulation.
 * Each rabbit has an inherent age, sex, and default gestation times and days until 
 * fertility for child-bearing in females (does).
 */
import java.util.Random;
public class Rabbit {
	private String sex;
	private int age = 0;
	private int gestationTime = -1;
	private int daysUntilFertile = 0;
	
	// Constructor used to set sex for seed rabbits
	public Rabbit(String sexIn) {
		sex = sexIn;
	}
	
	// Constructor used for rabbits with randomly assigned sex
	public Rabbit() {
		Random generator = new Random();
		if (generator.nextInt(2) == 0) {
			sex = "Male";
		}
		else {
			sex = "Female";
		}
	}
	
	// Returns the sex of the rabbit
	public String getSex() {
		return sex;
	}
	
	// Returns the age of the rabbit
	public int getAge() {
		return age;
	}
	
	// Increments the age of the rabbit by one day
	public void incrementAge() {
		age += 1;
	}

	// Returns the amount of gestation time left before birth.
	// The rabbit gives birth when gestationTime is 0
	public int getGestTime() {
		return gestationTime;
	}
	
	// The value -1 is used to represent fertility (able to be impregnated)
	public void resetGestTime() {
		gestationTime = -1;
	}
	
	// Decrements the gestation time by one day
	public void decrementGestTime() {
		gestationTime -= 1;
	}
	
	// Returns the number of days a rabbit has spent since its last birth
	public int getDUF() {
		return daysUntilFertile;
	}
	
	// Resets the days until fertile to 7 (used after a doe gives birth)
	public void resetDUF() {
		daysUntilFertile = 6;
	}
	
	// Decrements the days a doe has until it can breed again by one day
	public void decrementDUF() {
		daysUntilFertile -= 1;
	}
	
	// Rabbit becomes pregnant. A random value between 28-32 inclusive
	// is chosen for the amount of days the rabbit will be pregnant until birth
	public void impregnate() {
		Random generator = new Random();
		
		gestationTime = 28 + generator.nextInt(5);
	}
}
