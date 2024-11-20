import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The MultiThreadFibonacci class generates the desired amount of Fibonacci
 * numbers using multithreading. The program accepts values between 2 and 47,
 * inclusive, as the 48th Fibonacci number (2971215073) cannot be stored by the
 * int primitive, and there is no special case for 1 as the Fibonacci sequence
 * index begins at 1 in this case instead of 0.
 * 
 * The program generates a child thread to construct the sequence and insert the
 * values into an array, whose contents are then printed.
 * 
 * To expand the program's capable range, the long primitive could be used
 * instead.
 *
 * @author Sean Loesch
 * Date:   10/21/2023
 * @see FibonacciThread.java
 */
public class MultiThreadFibonacci {
    /**
     * The main method prompts the user for the desired number of Fibonacci
     * sequence values, generates a thread to accomplish the task, waits for the
     * thread to finish, then displays the result.
     *
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt user for number of desired amount of fibonacci numbers
        System.out.println("Please enter the desired amount of Fibonacci " +
                           "numbers (between 2-47 inclusive):");
        try {
            int num = input.nextInt();
            input.close();

            if (num < 2 || num > 47) {
                throw new InputMismatchException();
            }
            else {
                int[] fibonacciSeries = new int[num];

                // Create and run thread to construct the desired Fibonacci
                // sequence
                Thread fibonacciThread = new FibonacciThread(fibonacciSeries);
                fibonacciThread.start();

                // Wait for FibonacciThread to finish
                try {
                    fibonacciThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Print result
                System.out.println("Generated Fibonacci Series:");
                for (int value : fibonacciSeries) {
                    System.out.print(value + " ");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Looks like you didn't enter an acceptable " +
                               "integer. Please restart the program and try " +
                               "again.");
        }
    }
}
