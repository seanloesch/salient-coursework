package books;

/**
 * This class represents a Book that is designated to be given to someone with a
 * title, weight and a receiver name (the name of the person that is to receive
 * the book). It is displayed as the title of the book followed by its
 * receiver's name when printed in the form "<Title> (<ReceiverName>)".
 * 
 * @author Sean Loesch
 * Date:   5/15/2023
 */
public class ExaminationCopy extends Book {
    /**
     * Name of the person that is to receive this book. 
     */
    private String receiverName;
    
    /**
     * Constructor for the {@link ExaminationCopy} class. Virtually the same
     * constructor as {@link Book#Book}, with receiver name added.
     * 
     * @param titleIn the title of the book
     * @param lbsIn the weight of the book in pounds
     * @param nameIn the name of the person who will be receiving this book
     */
    public ExaminationCopy(String titleIn, double lbsIn, String nameIn) {
        title = titleIn;
        weight = lbsIn;
        receiverName = nameIn;
    }

    /**
     * Returns the title of the book and its receiver's name when it is
     * converted into a string.
     * 
     * @return the string representation of the book title + the receiver's name
     */
    public String toString() {
        return title + " (" + receiverName + ")";
    }
}
