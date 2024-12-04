package books;

/**
 * This class represnts a Book that is for sale with a title, weight and a
 * price. It is displayed as the title of the book followed by its price to the
 * cent when printed in the form "<Title> (<Price>)".
 * 
 * @author Sean Loesch
 * Date:   5/15/2023
 */
public class ForSaleBook extends Book {
    /**
     * The price of the book in USD.
     */
    private double price;

    /**
     * Constructor for the {@link ForSaleBook} class. Virtually the same
     * constructor as {@link Book#Book}, with price added.
     * 
     * @param titleIn The title of the book
     * @param lbsIn The weight of the book in pounds
     * @param price The price of the book in USD
     */
    public ForSaleBook(String titleIn, double lbsIn, double priceIn) {
        title = titleIn;
        weight = lbsIn;
        price = priceIn;
    }
    
    /**
     * Returns the title of the book and its price when it is converted into a
     * string.
     * 
     * @return the string representation of the book title and its price
     */
    public String toString() {
        return String.format(title + " ($%.2f)", price);
    }
}
