/**
 * Sean Loesch
 * 5/10/2022
 * Assignment 6
 * 
 * The purpose of this class is to open a window displaying a randomly generated
 * chessboard that allows for various interactions:
 * 
 * - Clicking on an empty tile: This will display instructions for the movement
 * of the pieces involved in the game.
 * 
 * - Clicking on a tile with a specific piece: This will highlight the possible
 * tiles to which that piece may move. Note that only one piece's possible moves
 * can be highlighted at a time, and each piece has a unique highlight color:
 * 
 * King: Gold/Yellow
 * Queen: Magenta
 * Bishop: Blue
 * Knight: Green
 * Rook: Red
 * Pawn: Pink
 */
public class Main {

	public static void main(String[] args) {
		
		// Instantiation of Chessboard object
		new Chessboard();
		
	}

}