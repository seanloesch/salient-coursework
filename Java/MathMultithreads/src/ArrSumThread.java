/**
 * The ArrSumThread class encapsulates a thread that will compute the sum of an
 * array of integers.
 *
 * @author Sean Loesch
 * Date:   10/25/2023
 * @see MultiThreadSum.java
 */
public class ArrSumThread extends Thread {
    /**
     * This thread's assigned row.
     */
    private final int[] row;
    /**
     * The array containing the sums of all the rows in the input file.
     */
    private final int[] threadRowSums;
    /**
     * Establishes a way to track of the order of the threads in a determined
     * way rather than the pseudo-random ID generation provided by the OS/JVM.
     */
    private final int customThreadID;

    /**
     * Populates the fields with assigned values.
     *
     * @param row the current row of integers from the input file
     * @param threadRowSums the array containing the sums of the rows in the
     * input file
     * @param customThreadID a custom identifier for this thread, allows order
     * tracking
     */
    public ArrSumThread(int[] row, int[] threadRowSums, int customThreadID) {
        this.row = row;
        this.threadRowSums = threadRowSums;
        this.customThreadID = customThreadID;
    }

    /**
     * Runs the thread and computes the sum for its particular assigned row.
     */
    @Override
    public void run() {
        int sum = 0;

        // Sum ints from this row
        for (int value : row) {
            sum += value;
        }

        threadRowSums[customThreadID] = sum;
    }
}
