import java.util.ArrayList;

/**
 * This class provides the framework for a queue data structure constructed
 * using the ArrayList class. It only stores Orders.
 * 
 * @author Sean Loesch
 * Date:   5/9/2023
 */
public class ALQueue {
    
    /**
     * The contents of the queue.
     */
    public ArrayList<Order> contents;

    /**
     * Creates a new instance of ALQueue and assigns an empty ArrayList to
     * {@link #contents}.
     */
    public ALQueue() {
        contents = new ArrayList<Order>();
    }

    /**
     * Adds an order to the queue.
     * 
     * @param obj the order that is added
     */
    public void add(Order obj) {
        contents.add(obj);
    }

    /**
     * Returns the head of the queue. Returns null if the queue is empty.
     * 
     * @return the order at the head of the queue, otherwise null if queue is
     * empty
     */
    public Order peek() {
        if (contents.isEmpty()) {
            return null;
        }
        else {
            return contents.get(0);
        }
    }

    /**
     * Returns and removes the head of the queue. Returns null if the queue is
     * empty.
     * 
     * @return head of the queue
     */
    public Order poll() {
        Order temp = contents.get(0); 
        contents.remove(0);

        return temp;
    }

    /**
     * Returns true if the queue is empty.
     * 
     * @return True if {@link #contents} is empty, False otherwise
     */
    public boolean isEmpty() {
        return contents.isEmpty();
    }
}
