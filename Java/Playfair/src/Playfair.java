import java.util.ArrayList;
/**
 * Decrypts a message provided a key using the Playfair cipher
 * and a key with non-repeating letters.
 * 
 * @author Sean Loesch
 * Date:   2/14/2023
 */
public class Playfair {
    
    /**
     * Decrypts a message encrypted using the Playfair cipher.
     * 
     * @param cipherText the message you are intending to decrypt. Must be
     * comprised of alphabetical characters
     * @param key the key used to decrypt the message. Must be comprised of
     * alphabetical characters
     * @return the decrypted message (plaintext)
     */
    public String decrypt(String cipherText, String key) {
        // Construct 5x5 matrix based on user-provided key
        String[][] matrix = makeMatrix(key);

        // Split ciphertext into pairs and store them
        ArrayList<String> pairs = splitIntoPairs(cipherText);

        // Decrypt each pair and add the results together
        String resText = "";
        for (int i = 0; i < pairs.size(); i++) {
            String char1 = "" + pairs.get(i).charAt(0);
            String char2 = "" + pairs.get(i).charAt(1);
            // Get coordinates of each character in matrix
            int[] char1Coords = findCoordinate(char1, matrix);
            int[] char2Coords = findCoordinate(char2, matrix);

            String decrypted1 = "";
            String decrypted2 = "";
            // Case 1: Same row. Go back 1 character for each in row
            if (char1Coords[0] == char2Coords[0]) { 
                decrypted1 =
                    matrix[char1Coords[0]][(char1Coords[1] - 1 + 5) % 5];
                decrypted2 =
                    matrix[char2Coords[0]][(char2Coords[1] - 1 + 5) % 5];
            }
            // Case 2: Same column. Go back 1 character for each in column
            else if (char1Coords[1] == char2Coords[1]) {
                decrypted1 =
                    matrix[(char1Coords[0] - 1 + 5) % 5][char1Coords[1]];
                decrypted2 =
                    matrix[(char2Coords[0] - 1 + 5) % 5][char2Coords[1]];
            }
            // Case 3: Different rows and columns. Go to opposite corners in
            // same row
            else {
                decrypted1 = matrix[char1Coords[0]][char2Coords[1]];
                decrypted2 = matrix[char2Coords[0]][char1Coords[1]];
            }

            resText += decrypted1;
            resText += decrypted2;
        }

        return resText;
    }

    /**
     * Constructs 5x5 matrix based on user-provided key based on the rules of
     * the Playfair cipher.
     * 
     * @param key to construct matrix with
     * @return 5x5 matrix of strings comprised of 1 character each
     */
    private static String[][] makeMatrix(String key) {
        // Storing characters for preprocessing before matrix insertion
        ArrayList<String> pre = new ArrayList<String>();

        // Insert key characters into pre
        for (int i = 0; i < key.length(); i++) {
            pre.add("" + key.charAt(i));
        }

        // Doesn't include "j" since "j" is usually combined with "i"
        String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "k",
        "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
        "y", "z"};

        // Add rest of alphabet to pre
        for (int i = 0; i < alphabet.length; i++) {
            if (key.contains(alphabet[i])) {
                continue;
            }
            else {
                pre.add(alphabet[i]);
            }
        }

        String[][] matrix = new String[5][5];

        // Filling matrix
        for (int i = 0; i < pre.size(); i++) {
            matrix[i / 5][i % 5] = pre.get(i);
        }

        return matrix;
    }

    /**
     * Splits a plaintext or ciphertext into pairs of characters. Adds "x" on
     * the end if there is an odd number of characters.
     * 
     * @param text the text (plaintext or ciphertext) that will be encrypted or
     * decrypted
     * @return the text split into pairs of characters
     */
    private static ArrayList<String> splitIntoPairs(String text) {
        ArrayList<String> pairs = new ArrayList<String>();

        // If there is an odd number of characters, add x (dummy character) on
        // the end
        if (text.length() % 2 == 1) {
            text += "x";
        }

        // Split text into pairs
        for (int i = 0; i < text.length(); i += 2) {
            pairs.add(text.substring(i, i + 2));
        }

        return pairs;
    }

    /**
     * Finds the coordinate of a string in a 2D matrix.
     * 
     * @param s a string to be found in the matrix
     * @param matrix a matrix to be searched
     * @return int array displaying coordinates in the form {row, columm}
     */
    private static int[] findCoordinate(String s, String[][] matrix) {
        // Stores coordinates of s in the matrix (-1s are placeholders) 
        int[] coords = {-1, -1};

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // If string is here, store this location
                if (matrix[i][j].equals(s)) {
                    coords[0] = i;
                    coords[1] = j;
                }
            }
        }

        return coords;
    }

}
