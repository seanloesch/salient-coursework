import java.util.Scanner;
/**
 * The main class for the program - where all inputs occur.
 * 
 * @author Sean Loesch
 * Date:   2/14/2023
 */
public class Main {
    /**
     * The main method for the program.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Playfair crypter = new Playfair();

        // Prompt user for key and store it
        System.out.println("Please enter the key containing only"
            + " non-repeating alphabetical characters you would like to"
            + " use:");
        String key =
            input.nextLine().toLowerCase().trim().replaceAll(" ", "");

        // If there is an "i" and a "j" in the key, remove "j" from key since
        // "j" is often combined with "i"
        if (key.contains("j") && key.contains("i")) {
            key.replaceAll("j","");
        }

        // If key contains non-alphabetical characters, prompt user
        // to restart process
        if (hasNonAlphabetical(key)) {
            System.out.println("Woops! Looks like you entered a key"
            + " with a non-alphabetic character. Please rerun the program"
            + " and start again.");
            
            input.close();
            return;
        }

        // Prompt user for ciphertext and store it
        System.out.println("Please enter ciphertext containing only"
        + " alphabetical characters you would like to decrypt on one"
        + " line:");
        String cipherText = 
            input.nextLine().toLowerCase().trim().replaceAll(" ", "");

        // If ciphertext contains non-alphabetical characters, prompt user
        // to restart process
        if (hasNonAlphabetical(cipherText)) {
            System.out.println("Woops! Looks like you entered a message"
            + " with a non-alphabetic character. Please rerun the program"
            + " and start again.");

            input.close();

            return;
        }

        System.out.println(cipherText);
        System.out.println("Here is your decrypted message using the key \""
            + key + "\": " + crypter.decrypt(cipherText, key));

        input.close();
    }

    /**
     * Asserts whether or not there are non-alphabetical characters in a
     * string.
     * 
     * @param input an input string
     * @return boolean value stating if the input string contains
     * at least one non-alphabetical character (true) or not (false)
     */
    public static boolean hasNonAlphabetical(String input) {
         return input.toLowerCase().matches(".*[^a-z].*");
    }
}
