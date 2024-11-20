/**
 * This class encapsulates an order of chicks from Doolittle Farms. It includes
 * the customer name, number of chicks requested and the number of days since
 * the last event.
 * 
 * @author Sean Loesch
 * Date:   5/9/2023
 */
public class Order {
    private String custName;
    private int chicksRequested;
    private int daysSinceLastEvent;

    /**
     * Creates a new instance of Order, and initializes values for the name,
     * number of chicks requested, and days since the last event for the Order.
     * 
     * @param name the name of the customer
     * @param chicks the number of chicks requested in the order
     * @param days the number of days since the last event
     */
    public Order(String name, int chicks, int days) {
        custName = name;
        chicksRequested = chicks;
        daysSinceLastEvent = days;
    }

    /**
     * Returns the name of the customer for the order.
     * 
     * @return the name of the customer
     */
    public String getName() {
        return custName;
    }

    /**
     * Returns the number of chicks requested in the order.
     * 
     * @return the number of chicks requested
     */
    public int getChicksRequested() {
        return chicksRequested;
    }

    /**
     * Sets the number of chicks in the request.
     * 
     * @param chicks the new number of chicks in the request
     */
    public void setChicksRequested(int numChicks) {
        chicksRequested = numChicks;
    }

    /**
     * Returns the number of days since the last event occured involving the
     * order.
     * 
     * @return the number of days since last event
     */
    public int getDays() {
        return daysSinceLastEvent;
    }
}
