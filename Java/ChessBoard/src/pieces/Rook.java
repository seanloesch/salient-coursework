package pieces;
import javax.swing.ImageIcon;

/**
 * Sean Loesch
 * 5/10/2022
 * Assignment 6
 * 
 * The purpose of this class is to represent the Rook piece of the game of chess.
 */
public class Rook implements ChessPiece {

	ImageIcon icon;
	
	/**
	 * Constructor parameter determines the color of the image
	 */
	public Rook(String colorIn) {

		if (colorIn.toLowerCase().equals("white")) {

			icon = new ImageIcon("src/images/WhiteRook.png");
		} else if (colorIn.toLowerCase().equals("black")) {

			icon = new ImageIcon("src/images/BlackRook.png");
		}
	}

	public ImageIcon getIcon() {
		
		return icon;
	}

	/**
	 * Asserts whether or not the passed ChessPiece object is equivalent to this ChessPiece object
	 */
	@Override
	public boolean equals(ChessPiece pieceIn) {

		return icon.toString().equals(pieceIn.getIcon().toString());
	}
	
}