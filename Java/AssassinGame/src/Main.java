import java.io.File;
import java.io.FileNotFoundException;

/*
 * Sean Loesch
 * 4/12/2022
 * Assignment 4
 * The purpose of this class is to run an instance of AssassinGame using an input file including
 * the names of those who will participate in the game.
 */
public class Main {

	public static void main(String[] args) {

		// Instantiating AssassinGame object using player_names.txt file
		File names = new File("src/player_names.txt");
		
		// If the file is not detected or is not accesible, throw an exception
		AssassinGame g1 = null;
		try {
			g1 = new AssassinGame(names);
		} catch (FileNotFoundException e) {
			System.out.println("File not found or file is not accessible. "
					+ "Please enter the correct file name and try again.");
		}
		
		// Running the game with the names in player_names.txt
		g1.run();
	}

}