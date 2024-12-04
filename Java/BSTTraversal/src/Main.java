/*
 * Sean Loesch
 * Assignment 5
 * 4/27/2022
 * The purpose of this class is to empircally compare the time it takes to generate 500 integers in the range 1-1000 inclusive,
 * insert them into a integer binary search tree (IntBST), and traverse the IntBST in four different traversion types.
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws IOException {
		
		// ArrayList of IntBSTs that will be used for the trials of each traversal type
		ArrayList<IntBST> bstArray = new ArrayList<IntBST>();
		
		// ArrayList that will contain the times it takes to randomly generate 500 integers within the range 1-1000 and insert them into a BST
		ArrayList<Long> generationTimes = new ArrayList<Long>();
		
		for (int i = 0; i < 10; i++) {
			
			ArrayList<Integer> numsAdded = new ArrayList<Integer>();
			IntBST nums = new IntBST();
			Random generator = new Random();
			
			// Log start time
			long startTime = System.nanoTime();
				
			// Insert 500 pseudo-random ints into the BST "nums"
			while (numsAdded.size() < 500) {
					
				int randomInt = generator.nextInt(1000) + 1;
					
				if (!numsAdded.contains(randomInt)) {
						numsAdded.add(randomInt);
						nums.insert(randomInt);
				}
					
			}
			
			// Logs end time after 500 integers are generated and inserted into an IntBST
			long endTime = System.nanoTime();
			
			generationTimes.add(endTime - startTime);
			
			// Adding current randomly generated IntBST into bstArray
			bstArray.add(nums);
			
		}
			
		// Finding the average of the times it took to generate the 500 unique integers and insert them into IntBSTs
		long avgGenerationTime = avg(generationTimes);
			
			
			
		// RUNNING 10 PREORDER TRIALS AND STORING RUNTIME DATA ----------------------------------------
		ArrayList<Long> preorderRunTimes = new ArrayList<Long>();
		FileWriter preorderWr = new FileWriter("src/see_results/preorderTrials.txt");
		
		for (int i = 0; i < bstArray.size(); i++) {
			
			preorderWr.write("Results for trial #" + (i + 1) + ": ----------------------------------------------------------------------------------------------\n");
			
			preorderRunTimes.add(preorderTrial(bstArray.get(i), preorderWr));
			
			preorderWr.write("\n");
			
		}
		
		preorderWr.close();
		
		// RUNNING 10 INORDER TRIALS AND STORING RUNTIME DATA ----------------------------------------
		ArrayList<Long> inorderRunTimes = new ArrayList<Long>();
		FileWriter inorderWr = new FileWriter("src/see_results/inorderTrials.txt");
				
		for (int i = 0; i < bstArray.size(); i++) {
					
			inorderWr.write("Results for trial #" + (i + 1) + ": ----------------------------------------------------------------------------------------------\n");
					
			inorderRunTimes.add(inorderTrial(bstArray.get(i), inorderWr));
					
			inorderWr.write("\n");
					
		}
				
		inorderWr.close();
		
		// RUNNING 10 INORDER TRIALS AND STORING RUNTIME DATA ----------------------------------------
		ArrayList<Long> postorderRunTimes = new ArrayList<Long>();
		FileWriter postorderWr = new FileWriter("src/see_results/postorderTrials.txt");
						
		for (int i = 0; i < bstArray.size(); i++) {
							
			postorderWr.write("Results for trial #" + (i + 1) + ": ----------------------------------------------------------------------------------------------\n");
							
			postorderRunTimes.add(postorderTrial(bstArray.get(i), postorderWr));
							
			postorderWr.write("\n");
							
		}
						
		postorderWr.close();
		
		// RUNNING 10 INORDER TRIALS AND STORING RUNTIME DATA ----------------------------------------
		ArrayList<Long> levelorderRunTimes = new ArrayList<Long>();
		FileWriter levelorderWr = new FileWriter("src/see_results/levelorderTrials.txt");
								
		for (int i = 0; i < bstArray.size(); i++) {
									
			levelorderWr.write("Results for trial #" + (i + 1) + ": ----------------------------------------------------------------------------------------------\n");
									
			levelorderRunTimes.add(levelorderTrial(bstArray.get(i), levelorderWr));
									
			levelorderWr.write("\n");
									
		}
								
		levelorderWr.close();
		
		
		
		
		// RESULTS SECTION

		System.out.println("Average time for pre-order traversal: "
				+ (avgGenerationTime + avg(preorderRunTimes)) + " ns");
		System.out.println("Average time for in-order traversal: "
				+ (avgGenerationTime + avg(inorderRunTimes)) + " ns");
		System.out.println("Average time for post-order traversal: "
				+ (avgGenerationTime + avg(postorderRunTimes)) + " ns");
		System.out.println("Average time for level-order traversal: "
				+ (avgGenerationTime + avg(levelorderRunTimes)) + " ns");
	}

	// Runs one trial in which a preorder traversal of a BST is written into its respective text file.
	// Returns a long that represents the time in nanoseconds that it takes to write the results of the 
	// preorder traversal to an output file.
	public static long preorderTrial(IntBST treeIn, FileWriter wr) throws IOException {
		
		// Logs start time
		long startTime = System.nanoTime();
		
		// Writers inorder traversal results to preorderTrials.txt
		treeIn.preorder(wr);
		
		// Logs end time
		long endTime = System.nanoTime();
		
		// Returns the differnce between the start and end time in nanoseconds - in other words, how long in nanoseconds the trial took to run
		return endTime - startTime;
	}
	
	// Runs one trial in which a inorder traversal of a BST is written into its respective text file.
	// Returns a long that represents the time in nanoseconds that it takes to write the results of the 
	// inorder traversal to an output file.
	public static long inorderTrial(IntBST treeIn, FileWriter wr) throws IOException {
		
		// Logs start time
		long startTime = System.nanoTime();
		
		// Writers inorder traversal results to inorderTrials.txt
		treeIn.inorder(wr);
		
		// Logs end time
		long endTime = System.nanoTime();
		
		// Returns the differnce between the start and end time in nanoseconds - in other words, how long in nanoseconds the trial took to run
		return endTime - startTime;
	}
	
	// Runs one trial in which a postorder traversal of a BST is written into its respective text file.
	// Returns a long that represents the time in nanoseconds that it takes to write the results of the 
	// postorder traversal to an output file.
	public static long postorderTrial(IntBST treeIn, FileWriter wr) throws IOException {
			
		// Logs start time
		long startTime = System.nanoTime();
			
		// Writers inorder traversal results to postorderTrials.txt
		treeIn.postorder(wr);
			
		// Logs end time
		long endTime = System.nanoTime();
			
		// Returns the differnce between the start and end time in nanoseconds - in other words, how long in nanoseconds the trial took to run
		return endTime - startTime;
	}
	
	// Runs one trial in which a levelorder traversal of a BST is written into its respective text file.
	// Returns a long that represents the time in nanoseconds that it takes to write the results of the 
	// levelorder traversal to an output file.
	public static long levelorderTrial(IntBST treeIn, FileWriter wr) throws IOException {
			
		// Logs start time
		long startTime = System.nanoTime();
		
		// Writers inorder traversal results to levelorderTrials.txt
		treeIn.levelorder(wr);
			
		// Logs end time
		long endTime = System.nanoTime();
			
		// Returns the differnce between the start and end time in nanoseconds - in other words, how long in nanoseconds the trial took to run
		return endTime - startTime;
	}
	
	// Returns the average value of a ArrayList of longs
	public static long avg(ArrayList<Long> longArray) {
		
		long sum = 0;
		
		// Compute sum of all elements
		for (int i = 0; i < longArray.size(); i++) {
			
			sum += longArray.get(i);
		}
		
		// Compute average and return
		return sum / longArray.size();
	}
	
}