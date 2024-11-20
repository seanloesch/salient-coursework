package books;

/**
 * This class represnts a Book with a title and a weight. It is displayed as the
 * title of the book when printed.
 * 
 * @author Sean Loesch
 * Date:   5/15/2023
 */
public class Book {
    /**
     * Title of the book.
     */
    protected String title;

    /**
     * Weight of the book in pounds.
     */
    protected double weight;

    /**
     * Default constructor.
     */
    public Book() {}

    /**
     * Constructor for the {@link Book} class. Sets values for the title of the
     * book and the weight of the book in pounds.
     * 
     * @param titleIn the title of the book
     * @param lbsIn the weight of the book in pounds
     */
    public Book (String titleIn, double lbsIn) {
        title = titleIn;
        weight = lbsIn;
    }

    /**
     * Returns the title of the book when it is converted into a string.
     * 
     * @return the title
     */
    public String toString() {
        return title;
    }
}
