/**
 * This class encapsulates a shipment of chicks to Doolittle Farms. It includes
 * the number of chicks in the shipment, the particular price per chick of the
 * shipment, and the number of days since the last event.
 * 
 * @author Sean Loesch
 * Date:   5/9/2023
 */
public class Shipment {
    private int numChicks;
    private double pricePerChick;
    private int daysSinceLastEvent;

    /**
     * Creates a new instance of Shipment, and initializes values for the number
     * of chicks, the price per chick, and the days since the last event for the
     * Shipment.
     * 
     * @param chicks the number of chicks
     * @param price the price per chick
     * @param days the number of days since the last event
     */
    public Shipment(int chicks, double price, int days) {
        numChicks = chicks;
        pricePerChick = price;
        daysSinceLastEvent = days;
    }

    /**
     * Returns number of chicks in the shipment. 
     * 
     * @return the number of chicks in the shipment
     */
    public int getNumChicks() {
        return numChicks;
    }

    /**
     * Changes the value for the number of chicks in the shipment.
     * 
     * @param newValue the value that will replace the current number of chicks
     */
    public void setNumChicks(int newValue) {
        numChicks = newValue;
    }

    /**
     * Returns the price per chick in the shipment.
     * 
     * @return the price per chick
     */
    public double getPrice() {
        return pricePerChick;
    }

    /**
     * Returns the number of days since the last event occured involving the
     * shipment.
     * 
     * @return the number of days since last event
     */
    public int getDays() {
        return daysSinceLastEvent;
    }

    /**
     * Set the number of days the shipment has been sitting in storage.
     * 
     * @param days the new day value
     */
    public void setDays(int days) {
        daysSinceLastEvent = days;
    }
}
