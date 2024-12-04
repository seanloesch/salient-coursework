import java.util.ArrayList;

/**
 * This is a stack data structure constructed using the ArrayList class. It only
 * stores Shipments.
 * 
 * @author Sean Loesch
 * Date:   5/9/2023
 */
public class ALStack {

    /**
     * The contents of the stack.
     */
    public ArrayList<Shipment> contents;

    /**
     * The number of total chicks in the stack.
     */
    private int totalChicks = 0;

    /**
     * Creates a new instance of ALStack and assigns an empty ArrayList to
     * {@link #contents}.
     */
    public ALStack() {
        contents = new ArrayList<Shipment>();
    }

    /**
     * Return the number of chicks in the stack.
     * 
     * @return number of chicks in the stack
     */
    public int getNumChicks() {
        return totalChicks;
    }

    /**
     * Adds an shipment to the stack and adds the number of chicks in the
     * shipment to the total.
     * 
     * @param obj the shipment that is added
     */
    public void push(Shipment obj) {
        totalChicks += obj.getNumChicks();
        contents.add(obj);
    }

    /**
     * Pops the topmost shipment off the stack and removes the number of chicks
     * in that shipment from the total.
     * 
     * @return the popped shipment
     */
    public Shipment pop() {

        this.updateNumChicks();

        Shipment temp = contents.get(contents.size() - 1);
        totalChicks -= temp.getNumChicks();
        contents.remove(contents.size() - 1);
        return temp;
    }

    /**
     * Returns the topmost shipment in the stack.
     * 
     * @return the topmost shipment in the stack
     */
    public Shipment peek() {
        return contents.get(contents.size() - 1);
    }

    /**
     * Returns the position of the topmost shipment in the stack. 
     * 
     * @return the position of the topmost shipment in the stack
     */
    public int search() {
        return contents.size();
    }

    /**
     * Returns true if the stack is empty.
     * 
     * @return True if {@link #contents} is empty, False otherwise
     */
    public boolean isEmpty() {
        return contents.isEmpty();
    }

    /**
     * Increments the age of all the shipments in the stack.
     * 
     * @param days the number of days to be added to the age of all the
     * shipments
     */
    public void incrementContentsAge(int days) {
        for (int i = 0; i < contents.size(); i++) {
            Shipment currShipment = contents.get(i);
            int currShipmentAge = currShipment.getDays();

            currShipment.setDays(currShipmentAge + days);
        }
    }

    /**
     * Updates the total number of chicks in the stack.
     */
    public void updateNumChicks() {
        int total = 0;
        for (int i = 0; i < contents.size(); i++) {
            total += contents.get(i).getNumChicks();
        }

        totalChicks = total;
    }
}
