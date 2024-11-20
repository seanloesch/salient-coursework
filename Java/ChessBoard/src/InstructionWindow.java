import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Sean Loesch
 * 5/10/2022
 * Assignment 6
 * 
 * The purpose of this class is to display a window listing all the basic movement rules of chess.
 */
public class InstructionWindow {
	
	/**
	 * Constructor for this class. All of the construction of the rules window is here
	 */
	InstructionWindow() {
		
		// Making background black so text is more easily readable
		UIManager.put("OptionPane.background", Color.BLACK);
		UIManager.put("Panel.background", Color.BLACK);
           
		// A html-formatted string containing all the movement instructions for the game of chess.
		String instructions = 
			"<html>" +
			"<head>" +
			"<style>" +
			"h1 {text-align: center;}" +
			"</style>" +
			"</head>" +
			"<body>" +
			
			// -------------------------------------------------------------------------- MOVEMENT SECTION --------------------------------------------------------------------------
			"<h1>Movement</h1>" +
			
			// Beginning of bulleted list
			"<ul>" + 
			
			// KING
			"<li>A <b>king</b> may move one square in any direction.</li><br>" +
			
			// ROOK
			"<li>A <b>rook</b> can move any number of squares along a rank (row) or file (column), but cannot leap over other pieces.</li><br>" +
			
			// BISHOP
			"<li>A <b>bishop</b> can move any number of squares diagonally, but cannot leap over other pieces.</li><br>" +
			
			// QUEEN
			"<li>A <b>queen</b> combines the power of a rook and bishop and can move any number of squares along a rank, file, or diagonal, but cannot leap over other pieces.</li><br>" +
			
			// KNIGHT
			"<li>A <b>knight</b> moves to any of the closest squares that are not on the same rank, file, or diagonal. (Thus the move forms an \"L\"-shape: two squares vertically<br>" +
			"and one square horizontally, or two squares horizontally and one square vertically.) The knight is the only piece that can leap over other pieces.</li><br>" +
			
			// PAWN
			"<li>A <b>pawn</b> can move forward to the unoccupied square immediately in front of it on the same file. A black pawn may only move downwards, and a white<br>" +
			"pawn may only move upwards.</li><br>" +
			//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			
			// End of bulleted list
			"</ul>" +
			"</body>" +
			"</html>";
		
		// Display the basic movement rules of chess through a JOptionPanel using a JLabel as the parent component
		JLabel label = new JLabel(instructions);
		label.setForeground(Color.white);
		label.setSize(new Dimension(300, 200));
		label.setFont(new Font("serif", Font.PLAIN, 14));
		
		// Opening the window
		JOptionPane.showMessageDialog(null, label);
		
	}
	
}
