package books;
import java.util.ArrayList;

/**
 * This class represents a box that holds Book class objects.
 * 
 * @author Sean Loesch
 * Date:   5/15/2023
 */
public class Box {
    /**
     * A list of all the books contained in the box.
     */
    private ArrayList<Book> contents = new ArrayList<Book>();

    /**
     * A unique integer ID to represent the box.
     */
    private int ID;

    /**
     * The maximum weight capacity of the box in pounds.
     */
    private double capacity;

    /**
     * The constructor for the Box class. Passes an ID and a weight capacity for
     * the box.
     * 
     * @param idIn the ID assigned to the box
     * @param capacityIn the maximum weight capacity of the box
     */
    public Box(int idIn, double capacityIn) {
        ID = idIn;
        capacity = capacityIn;
    }

    /**
     * Returns the {@link #ID} of the box when it is converted into a string.
     * 
     * @return the string representation of the box ID
     */
    public String toString() {
        return "" + ID;
    }

    /**
     * Adds a book to the contents if the new book won't make it exceed
     * capacity. If it will make it exceed capacity, raises a {@link ValueError}
     * with an error message.
     * 
     * @param bk a book to be added to the {@link #contents} of the box
     */
    public void add_book(Book bk) {
        // Measuring total current weight of box
        double currentWeight = 0;

        // If box isn't empty, add each book's weight to total current weight
        if (!contents.isEmpty()) {
            for (Book book: contents) {
                currentWeight += book.weight;
            }
        }
        
        // Raise ValueError if capacity will be exceeded by the book being added
        if (currentWeight + bk.weight > capacity) {
            throw new ValueError("Capacity of " + ID + " exceed");
        }
        // Otherwise add book to contents
        else {
            contents.add(bk);
        }
    }

    /**
     * Prints every book in the {@link #contents} of the box.
     */
    public void print_content() {
        for (Book b: contents) {
            System.out.println(b);
        }
    }
}
