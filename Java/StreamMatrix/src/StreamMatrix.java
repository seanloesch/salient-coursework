/**
 * StreamMatrix.java
 * 
 * Prints only the unique rows of a binary matrix populated by the user.
 * Utilizes streams to accomplish this.
 * 
 * @author Sean Loesch
 * Date:   2023-03-31
 */

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StreamMatrix {
    
    // Runs the program
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numRows;
        int numCols;
        
        // Prompt user for number of rows and columns in matrix
        try {
            System.out.println("How many rows will your matrix have?:");
            numRows = input.nextInt();
            System.out.println("How many columns will your matrix have?:");
            numCols = input.nextInt();

            // Runs the program function
            printUniqueRows(numRows, numCols, input);

        // Exception is caught if user did not enter an integer
        } catch (InputMismatchException e) {
            System.out.println(
                "You did not enter an integer greater than 0. Please rerun " +
                "the program and try again.");
            input.close();
        }

        // Close scanner
        input.close();
    }

    /**
     * Prints all of the unique rows in a binary matrix populated by the user.
     * The minimum allows size for a matrix is 1x1, and the maximum allowed size
     * is 64x64.
     * 
     * @param numRows the number of rows in the matrix to be populated
     * @param numCols the number of columns in the matrix to be populated
     * @param input A Scanner object that prompts the user for input to populate
     * the matrix
     */
    public static void printUniqueRows(int numRows, int numCols, Scanner input) {

        // If numRows and numCols aren't between 1-64 inclusive, send message
        // and end program
        if (1 > numRows || numRows > 64) {
            System.out.println(
                "This program only accepts numbers from 1-64 for row and " +
                "column sizes. Rerun the program and try again.");
                return;
        }
        else if (1 > numCols || numCols > 64) {
            System.out.println(
                "This program only accepts numbers from 1-64 for row and " +
                "column sizes. Rerun the program and try again.");
                return;
        }
        else {
            int matrix[][] = new int[numRows][numCols];

            // Prompt user to populate matrix
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    // Prompt user for binary value
                    System.out.println(
                        "Enter binary value for position " +
                        "{" + (row + 1) + "," + (col + 1) + "}:");

                    int num;
                    try {
                        // Convert user input to int
                        num = input.nextInt();

                        // If number isn't a 0 or 1, display message and end program
                        if (num != 0 && num != 1) {
                            System.out.println(
                                "You did not enter a binary value (0 or 1). " +
                                "Please rerun the program and try again.");
                            return;
                        }
                    } 
                    // Exception is caught if user did not enter an integer
                    catch (InputMismatchException e) {
                        System.out.println(
                            "You did not enter a binary value (0 or 1). " +
                            "Please rerun the program and try again.");
                        return;
                    }
                    // Assign num to current position in matrix
                    matrix[row][col] = num;
                }
            }

            // Printing array built by user
            System.out.println("\nYour matrix is:");
            System.out.println(Arrays.deepToString(matrix)
                .replace("], ", "\n")
                // RegEx to remove all [, ], and ,
                .replaceAll("\\[|\\]|,", ""));

            // Using streams to remove duplicates
            int[][] uniqueMatrix = Arrays.stream(matrix)
                // Making each row a string so they can be compared as a whole
                // without comparing individual integer values
                .map(Arrays::toString)

                // Eliminates duplicates based on string equality
                .distinct()

                // Turns each row back into an array of strings that contain a
                // binary integer and whitespace
                .map(rowStr -> rowStr.substring(1, rowStr.length() - 1)
                     .split(","))

                // Trims whitespace off each string, converts back into integer,
                // then back into array
                .map(row -> Arrays.stream(row)
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray())

                // Takes stream of int arrays and morphs it back into 2d array
                .toArray(int[][]::new);

            // Printing array with only unique rows
            System.out.println("\nYour new matrix with only unique rows is:");    
            System.out.println(
                Arrays.deepToString(uniqueMatrix)
                      .replace("], ", "\n")

                    // RegEx to remove all square brackets and commas
                    .replaceAll("\\[|\\]|,", ""));
        }
    }
}
