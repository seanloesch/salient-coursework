import java.util.InputMismatchException;
import java.util.Scanner;
import books.*;
import islands.*;

/**
 * This class runs testing for the BooleanMatrix and Book, ForSaleBook,
 * ExaminationCopy, Box, and ValueError classes. Prompts user input for the
 * number of rows and colums the BooleanMatrix will have, and automatically
 * generates a result from a randomly populated BooleanMatrix. Then prints
 * results for the testing of the Book (and its child classes), Box class
 * and ValueError class. See code for details.
 * 
 * @author Sean Loesch
 * Date:   5/15/2023
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        // -------------------- BEGIN TESTING FOR MATRIX -----------------------

        // Initializing row and column to default values
        int rows = -1;
        int cols = -1;
        // Prompt user for input on how many rows and columns the matrix should
        // have
        try {
            System.out.println("Please enter the number of rows you would like: ");
            rows = input.nextInt();
            System.out.println("Please enter the number of columns you would like: ");
            cols = input.nextInt();
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println("You entered an invalid number. Please try " +
                               "again and enter an integer greater than 0.");
            input.close();

            // End program
            return;
        }
        input.close();

        // Initializing matrix using user-defined values
        BooleanMatrix matrix = new BooleanMatrix(rows, cols);

        // MATRIX PROBLEM CODE EXECUTION
        System.out.println("This is the generated matrix:");
        System.out.println(matrix.toString());
        // Run depth-first search
        System.out.println("\nThe number of islands in this matrix is: "
                           + matrix.islandDFS());


        // -------------------- BEGIN TESTING FOR BOOKS ------------------------
        
        // Testing Book class
        Book bookTest = new Book("The Odyssey" , 1.15);
        System.out.println("\n\nTesting Book class:\n" + bookTest);

        // Testing ForSaleBook class
        ForSaleBook saleBookTest = new ForSaleBook("Moby Dick", 0.65, 2.891);
        System.out.println("\nTesting ForSaleBook class:\n" + saleBookTest);

        // Testing ExaminationCopy class
        ExaminationCopy examCopyTest = new ExaminationCopy("The Laws of Nature", 3.00, "George Orwell");
        System.out.println("\nTesting ExaminationCopy class:\n" + examCopyTest);

        // Testing Box class
        Box boxTest = new Box(1, 5.00);
        System.out.println("\nTesting Box Class:\nBox ID: " + boxTest);

        boxTest.add_book(bookTest);
        boxTest.add_book(saleBookTest);
        boxTest.add_book(examCopyTest);

        System.out.println("\nBox contents:");
        boxTest.print_content();

        // Testing ValueError class and the add_book method of the Box class
        System.out.println("\nAttemping to add book that exceeds capacity:");
        boxTest.add_book(examCopyTest);
    }
}
