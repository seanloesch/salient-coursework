package islands;
import java.util.Random;

/**
 * This class is designed to count the number of connected subgraphs (islands)
 * in a pseudo-randomly generated 2D matrix consisting of boolean integer values
 * (0s and 1s).
 * 
 * @author Sean Loesch
 * Date:   5/13/2023
 */
public class BooleanMatrix {
    /**
     * The contents of the matrix.
     */
    private int[][] matrix;

    /**
     * Auxiliary matrix used in the depth-first search to represent
     * corresponding visited cells.
     */
    private boolean[][] visited;

    /**
     * The constructor for the {@link BooleanMatrix} class. Initializes the matrix to
     * have a specified number of rows and columns.
     * 
     * @param rows the number of rows the matrix will have
     * @param cols the number of columns the matrix will have
     */
    public BooleanMatrix(int rows, int cols) {
        // Make matrix field the specified size
        matrix = new int[rows][cols];

        // Insert random boolean values into the matrix
        generateMatrix();
    }

    /**
     * Fills the {@link #matrix} field with pseudo-randomly generated boolean
     * integers (0s and 1s).
     */
    public void generateMatrix() {
        Random rand = new Random();
        
        // Insert boolean integer values into matrix pseudo-randomly
        for (int i = 0; i < matrix.length; i++) {
            int[] currRow = matrix[i];

            for (int j = 0; j < currRow.length; j++) {
                currRow[j] = rand.nextInt(2);
            }
        }
    }

    /**
     * Converts the {@link #matrix} field into a human-readable string so that
     * it may be printed.
     * 
     * @return the string representation of the matrix
     */
    public String toString() {
        String res = "{{";
        int[] currRow = {};

        for (int i = 0; i < matrix.length; i++) {
            currRow = matrix[i];

            // Appending first element early to avoid extra comma
            res += currRow[0];

            // Iterating starting from second element and until second to last
            // element
            for (int j = 1; j < currRow.length; j++) {
                res += ", " + currRow[j];
            }

            // If the end of the last row has been reached, finish the structure
            if (i == matrix.length - 1) {
                res += "}}";
            }
            // Otherwise, finish the current row add a new line for the next row
            else {
                res += "},\n {";
            }
        }
        return res;
    }

    /**
     * Performs a depth-first search (DFS) on the {@link #matrix} and returns
     * the number of islands or connected components (a subgraph in which every
     * two vertices are connected to each other by a path(s), and which is
     * connected to no other vertices outside the subgraph) in the matrix.
     * 
     * @return the number of islands in the matrix
     */
    public int islandDFS() {
        // Creating auxiliary matrix to represent visited cells
        visited = new boolean[matrix.length][matrix[0].length];

        // Tracks number of islands visited
        int islandCount = 0;

        // Iterate over every cell in the matrix
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                // If a piece of an island has been found and it hasn't already
                // been visited
                if (matrix[row][col] == 1 && !visited[row][col]) {
                    islandCount++; // Increment island count
                    dfsHelper(row, col); // Check all adjacent cells
                }
            }
        }

        return islandCount;
    }

    /**
     * A helper method for the {@link #islandDFS()} function that recursively
     * visits all the adjacent cells (including diagonals) to the current cell
     * and marks them as visited. This continues until the current island has
     * been completely explored.
     * 
     * @param row the row number of the cell
     * @param col the column number of the cell
     */
    private void dfsHelper(int row, int col) {
        // Check if indices are within bounds and cell is not visited
        if (row < 0 ||
            row >= matrix.length ||
            col < 0 ||
            col >= matrix[0].length ||
            visited[row][col] ||
            // Reached "water"
            matrix[row][col] == 0) {
            return;
        }
        
        // Mark this cell as visited
        visited[row][col] = true;
        
        // Recursively visit neighboring cells
        dfsHelper(row - 1, col - 1); // Up-Left diagonal
        dfsHelper(row - 1, col);     // Up
        dfsHelper(row - 1, col + 1); // Up-Right diagonal
        dfsHelper(row, col - 1);     // Left
        dfsHelper(row, col + 1);     // Right
        dfsHelper(row + 1, col - 1); // Down-Left diagonal
        dfsHelper(row + 1, col);     // Down
        dfsHelper(row + 1, col + 1); // Down-Right diagonal
    }
}
