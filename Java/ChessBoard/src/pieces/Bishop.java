package pieces;
import javax.swing.ImageIcon;

/**
 * Sean Loesch
 * 5/10/2022
 * Assignment 6
 * 
 * The purpose of this class is to represent the Bishop piece of the game of chess.
 */
public class Bishop implements ChessPiece {

	ImageIcon icon;
	
	/**
	 * Constructor parameter determines the color of the image
	 */
	public Bishop(String colorIn) {

		if (colorIn.toLowerCase().equals("white")) {

			icon = new ImageIcon("src/images/WhiteBishop.png");
		} else if (colorIn.toLowerCase().equals("black")) {

			icon = new ImageIcon("src/images/BlackBishop.png");
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