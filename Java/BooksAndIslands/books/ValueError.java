package books;

/**
 * This class includes the custom error ValueError, which is an unchecked
 * exception.
 * 
 * @author Sean Loesch
 * Date:   5/15/2023
 */
public class ValueError extends RuntimeException {
    /**
     * Constructor for the ValueError class.
     * 
     * @param message The error message to be displayed
     */
    public ValueError(String message) {
        super(message);
    }
}
