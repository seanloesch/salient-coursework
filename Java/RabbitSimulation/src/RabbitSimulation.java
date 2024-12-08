/*
 * Sean Loesch
 * 3/7/2022
 * CS 245-001
 * Assignment 1
 * 
 * The purpose of this class is to run a rabbit simulation given a seed file
 * that keeps track of the population as well as demographical data (on sex and 
 * total population) and compiles/prints results using said data.
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
public class RabbitSimulation {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner reader = new Scanner(new File("src/RabbitSeeds.txt"));
		ArrayList<Integer> seeds = new ArrayList<Integer>();
		
		// Reading in seed info from file with two tab-delimited numbers
		// per row representing the number of starting does and bucks
		// for each simulation
		while(reader.hasNext()) {
			seeds.add(Integer.parseInt(reader.next()));
		}
		
		
		// Running Tests
		for (int i = 0; i < seeds.size(); i++) {
			
			int startingDoes = seeds.get(i);
			int startingBucks = seeds.get(i + 1);
			i++;
			
			System.out.println("Starting with " + startingDoes + " doe(s) and " 
					+ startingBucks + " buck(s):");
			
			// Results from the simulation ran from the current row of the read-in file
			ArrayList<ArrayList<Integer>> currentSimData = new ArrayList<ArrayList<Integer>>();
			
			// Running 10 trials per simulation and storing the results in a 2-D ArrayList
			for (int j = 0; j < 10; j++) {
				currentSimData.add(rabbitSimulation(startingDoes,startingBucks));
			}
			

			// Arrays to contain separated data for each simulation so that
			// the averages and standard deviations may be computed
			ArrayList<Integer> totalPop = new ArrayList<Integer>();
			ArrayList<Integer> numOfDoes = new ArrayList<Integer>();
			ArrayList<Integer> numOfBucks = new ArrayList<Integer>();
			
			for (int j = 0; j < currentSimData.size(); j++) {
				
				// Array that contains the results from a single trial of this simulation
				ArrayList<Integer> currentTrialData = currentSimData.get(j);
				
				// Integer data values that are taken from the current trial
				int currentTrialPop = currentTrialData.get(0);
				int currentTrialDoes = currentTrialData.get(1);
				int currentTrialBucks = currentTrialData.get(2);
				
				// Adding data values from current trial to ArrayLists to compile
				// similar data from every trial
				totalPop.add(currentTrialPop);
				numOfDoes.add(currentTrialDoes);
				numOfBucks.add(currentTrialBucks);
				
				// Print statement for every trial
				System.out.println("Trial " + (j + 1) + ": " + currentTrialPop + 
						" was the final population of rabbits; " + currentTrialDoes +
							" does, " + currentTrialBucks + " bucks.");
			}
			
			// Concluding print statements including averages and standard
			// deviations for each simulation
			System.out.printf("Average number of rabbits: %.3f with standard deviation"
					+ " of %.3f.\n", avg(totalPop), stdDev(totalPop));
			System.out.printf("Average number of does: %.3f with standard" 
					+ " deviation of %.3f.\n", avg(numOfDoes), stdDev(numOfDoes));
			System.out.printf("Average number of bucks: %.3f with standard" 
					+ " deviation of %.3f.\n\n", avg(numOfBucks), stdDev(numOfBucks));
		}
	}
	
	
	
	
	
	// Runs a trial of the rabbit simulation. Returns an ArrayList that contains the demographic
	// information (total population, number of does, and number of bucks in that order) from the trial
	public static ArrayList<Integer> rabbitSimulation(int does, int bucks) {
		
		// Random object used to randomize the number of rabbits in every new litter
		Random generator = new Random();
		
		// ArrayList to hold the rabbits
		ArrayList<Rabbit> rabbitHolder = new ArrayList<Rabbit>();

		// Inserting the original does/bucks into the ArrayList
		for (int j = 0; j < does; j++) {
			rabbitHolder.add(new Rabbit("Female"));
		}
		for (int j = 0; j < bucks; j++) {
			rabbitHolder.add(new Rabbit("Male"));
		}

		// Iterating over the rabbitHolder ArrayList of Rabbits every day
		// for 365 days
		for (int j = 0; j < 365; j++) {
			for (int k = 0; k < rabbitHolder.size(); k++) {
				Rabbit currentRabbit = rabbitHolder.get(k);
				currentRabbit.incrementAge();

				// Can this doe breed? If so, it will be impregnated. If not (it has
				// given birth recently), decrement the days until the doe will be fertile
				// by one day
				if (currentRabbit.getSex() == "Female"
						&& currentRabbit.getAge() >= 100
							&& currentRabbit.getGestTime() == -1) {
					if (currentRabbit.getDUF() > 0) {
						currentRabbit.decrementDUF();
					}
					else {
						currentRabbit.impregnate();
					}
				}

				// If the doe is pregnant, reduce the days it has until birth
				if (currentRabbit.getSex() == "Female"
						&& currentRabbit.getGestTime() > 0) {
					currentRabbit.decrementGestTime();
				}

				// Doe gives birth to a litter of 3-8 kits when the gestation time hits 0.
				if (currentRabbit.getSex() == "Female" && 
						currentRabbit.getGestTime() == 0) {
					for (int l = 0; l < 3 + generator.nextInt(6); l++) {
						rabbitHolder.add(new Rabbit());
					}
					currentRabbit.resetGestTime();
					currentRabbit.resetDUF();
				}
			}
		}

		// ArrayList containing the number of does, bucks, and total population for this trial
		ArrayList<Integer> trialData = new ArrayList<Integer>();

		// Counting the number of does and bucks in this trial
		int numOfDoes = 0;
		int numOfBucks = 0;
		for (int j = 0; j < rabbitHolder.size(); j++) {
			if (rabbitHolder.get(j).getSex() == "Female") {
				numOfDoes += 1;
			}
			else {
				numOfBucks += 1;
			}
		}

		// Adding the values from this trial to the trialData ArrayList
		trialData.add(rabbitHolder.size());
		trialData.add(numOfDoes);
		trialData.add(numOfBucks);
		
		// Return statement
		return trialData;
	}
	
	
	
	
	
	// Returns a double representing the average of the integers in an array
	public static double avg(ArrayList<Integer> arrayIn) {
		int sum = 0;
		for (int i = 0; i < arrayIn.size(); i++) {
			sum += arrayIn.get(i);
		}
		
		return (double) sum / arrayIn.size();
	}
	
	
	
	
	
	// Returns a double representing the standard deviation of the integers in an array
	public static double stdDev(ArrayList<Integer> arrayIn) {
		int sumOfSquares = 0;
		double avg = avg(arrayIn);
		
		for (int i = 0; i < arrayIn.size(); i++) {
			sumOfSquares += Math.pow(arrayIn.get(i) - avg, 2);  
		}
		
		return Math.sqrt(sumOfSquares / arrayIn.size());
	}

}
