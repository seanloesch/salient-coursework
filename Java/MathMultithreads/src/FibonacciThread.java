/**
 * The FibonacciThread class encapsulates a thread that will generate a
 * Fibonacci sequence of integers of a desired length.
 *
 * @author Sean Loesch
 * Date:   10/21/2023
 * @see MultiThreadFibonacci.java
 */
public class FibonacciThread extends Thread {
    /**
     * Array containing the values of the desired length Fibonacci sequence
     */
    private final int[] fibonacciSeries;

    /**
     * Populates the fields with assigned values.
     *
     * @param fibonacciSeries the array containing the Fibonacci sequence
     */
    public FibonacciThread(int[] fibonacciSeries) {
        this.fibonacciSeries = fibonacciSeries;
    }

    /**
     * Runs the thread and populates the fibonacciSeries array with the proper
     * values.
     */
    @Override
    public void run() {
        // Amount of desired values
        int n = fibonacciSeries.length;

        // Initialize first two values to prevent out-of-bounds exception
        fibonacciSeries[0] = 0;
        fibonacciSeries[1] = 1;

        // Generate fibonacci series and insert values into array
        for (int i = 2; i < n; i++) {
            fibonacciSeries[i] = fibonacciSeries[i - 1] +
                                 fibonacciSeries[i - 2];
        }
    }
}
