import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The MultiThreadSum class computes the total sum of a 2D array of integers
 * using multithreading. It is compatible with jagged array types. The raw data
 * must be provided in the text file array_input.txt, and the number of rows and
 * the length of the longest column must be provided in the first row of the
 * file. All integers in array_input.txt must be delimited by spaces.
 *
 * @author Sean Loesch
 * Date:   10/25/2023
 * @see ArrSumThread.java
 * @see array_input.txt
 */
public class MultiThreadSum {
    /**
     * Initializes values to populate a jagged 2D array of integers, creates
     * threads that compute the sum of each row of the 2D array, then prints the
     * summation of all the rows.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(
                                 new FileReader("src/array_input.txt"))) {
            // Reading dimensions from first line of text file
            String[] dimensions = br.readLine().split(" ");
            // Number of total rows
            int numRows = Integer.parseInt(dimensions[0]);
            // Length of longest row
            int numCols = Integer.parseInt(dimensions[1]);
            int[][] data = new int[numRows][numCols];
            int[] threadRowSums = new int[numRows];

            // Insert data into 2D array
            for (int i = 0; i < numRows; i++) {
                String[] currRow = br.readLine().split(" ");
                for (int j = 0; j < currRow.length; j++) {
                    data[i][j] = Integer.parseInt(currRow[j]);
                }
            }

            // Create and run threads (1 thread for every row/array)
            Thread[] threads = new Thread[numRows];
            for (int i = 0; i < numRows; i++) {
                // Constructing thread using i as value for custom thread id
                // {0, 1... numRows - 1}
                threads[i] = new ArrSumThread(data[i], threadRowSums, i);
                threads[i].start();
            }

            // Wait for all threads to finish
            try {
                for (Thread thread : threads) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Calculate total sum
            int totalSum = 0;
            for (int sum : threadRowSums) {
                totalSum += sum;
            }

            // Print result
            System.out.println("Total Sum: " + totalSum);

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
