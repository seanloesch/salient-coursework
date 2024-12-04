package pieces;
import javax.swing.ImageIcon;

/**
 * Sean Loesch
 * 5/10/2022
 * Assignment 6
 * 
 * The purpose of this class is to serve as an parent class that includes all chess pieces,
 * both white and black.
 */
public interface ChessPiece {
	
	public ImageIcon getIcon();

	boolean equals(ChessPiece pieceIn);
	
}
