import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;

import pieces.*;

/**
 * Sean Loesch
 * 5/12/2022
 * Assignment 6
 * 
 * The purpose of this class is to create a randomized, working chessboard that both highlights where
 * pieces can move when selected and display the basic movement rules.
 */
public class Chessboard extends JFrame {
	
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	* ArrayList of ChessTiles used to save the tiles highlighted as a consequence of a button press 
	* so that they be restore efficiently upon a subsequent button press
	*/
	private ArrayList<ChessTile> highlightedTiles = new ArrayList<ChessTile>();
	
	Chessboard() {
		
		// Instantiation of the frame that will contain the chessboard
		JFrame chessBoard = new JFrame();
		
		// Adjusting settings/format of the board
		chessBoard.setSize(new Dimension(896, 895));
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setDefaultCloseOperation(EXIT_ON_CLOSE);
		chessBoard.setResizable(false);
		
		// ArrayList that contains all of the pieces on the board along with the number of empty slots that will be on the board 
		// that will be on the board
		ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>(64);
		
		// ArrayList that contains all of the tiles that will be present on the board
		ArrayList<ChessTile> tiles = new ArrayList<ChessTile>(64);
		
		// ----------------------------------------------- PIECES INSERTION -----------------------------------------------
		
		// ADDING ALL WHITE PIECES
		pieces.add(new King("white"));
		pieces.add(new Queen("white"));
		
		for (int i = 1; i <= 2; i++) {
			
			pieces.add(new Bishop("white"));
			pieces.add(new Knight("white"));
			pieces.add(new Rook("white"));
			
			for (int j = 1; j <= 4; j++) {
				
				pieces.add(new Pawn("white"));
			}
		}
		// -----------------------
		
		// ADDING ALL BLACK PIECES
		pieces.add(new King("black"));
		pieces.add(new Queen("black"));
		
		for (int i = 1; i <= 2; i++) {
			
			pieces.add(new Bishop("black"));
			pieces.add(new Knight("black"));
			pieces.add(new Rook("black"));
			
			for (int j = 1; j <= 4; j++) {
				
				pieces.add(new Pawn("black"));
			}
		}
		// -----------------------
		
		// Adding the empty spaces to the pieces ArrayList
		for (int i = 1; i <= 32; i++) {
					
			pieces.add(null);
		}
		
		// ----------------------------------------------------------------------------------------------------------------
	
				
		// ----------------------------------------------- TILES INSERTION ------------------------------------------------
				
		// Inserting board pattern into tiles ArrayList
		for (int i = 0; i < 8; i++) {
			
			if (i %  2 == 0) {
				for (int j = 0; j < 8; j++) {
					
					if (j % 2 == 0) {
						tiles.add(new ChessTile("light"));
					} else {
						tiles.add(new ChessTile("dark"));
					}
					
				}
			} else {
				for (int j = 0; j < 8; j++) {
					
					if (j % 2 == 0) {
						tiles.add(new ChessTile("dark"));
					} else {
						tiles.add(new ChessTile("light"));
					}
					
				}
			}
			
		}
		
		// 2-Dimensional array containing all tiles in the chessboard
		ChessTile[][] layout = {new ChessTile[8], new ChessTile[8], new ChessTile[8], new ChessTile[8], 
							  new ChessTile[8], new ChessTile[8], new ChessTile[8], new ChessTile[8]}; 
		
		// Insertion of tiles into layout array
		for (int i = 0; i < 64; i++) {
			
			int currRow = i / 8;
			int currColumn = i % 8;
			
			layout[currColumn][currRow] = tiles.get(i);
			
		}
		
		// Iterates down every column. After entire column of tiles has been iterated over, moves over a row.
		// This loop establishes the relationships between the tiles, allowing them to reference one another
		for (int i = 0; i < layout.length; i++) {
			
			for (int j = 0; j < layout[i].length; j++) {
				
				ChessTile currTile = layout[i][j];
				
				// Northwest
				try {
				currTile.setNW(layout[i-1][j-1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					currTile.setNW(null);
				}
				
				// North
				try {
				currTile.setN(layout[i][j-1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					currTile.setN(null);
				}
				
				// Northeast
				try {
					currTile.setNE(layout[i+1][j-1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					currTile.setNE(null);
				}
				
				// East
				try {
				currTile.setE(layout[i+1][j]);
				} catch (ArrayIndexOutOfBoundsException e) {
					currTile.setE(null);
				}
				
				// West
				try {
				currTile.setW(layout[i-1][j]);
				} catch (ArrayIndexOutOfBoundsException e) {
					currTile.setW(null);
				}
				
				// Southwest
				try {
				currTile.setSW(layout[i-1][j+1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					currTile.setSW(null);
				}
				
				// South
				try {
				currTile.setS(layout[i][j+1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					currTile.setS(null);
				}
				
				// Southeast
				try {
					currTile.setSE(layout[i+1][j+1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					currTile.setSE(null);
				}
				
			}
			
		}
		
		// ----------------------------------------------------------------------------------------------------------------
		
		// Shuffling pieces
		Collections.shuffle(pieces);
		
		for (int i = 0 ; i < 64; i++) {
			
			ChessTile currTile = tiles.get(i);
			currTile.add(pieces.get(i));
			
			chessBoard.add(currTile.getTile());
			
			// Make this board the "parent" of every previously generated tile
			currTile.setParentBoard(this);
		}
		
		chessBoard.setVisible(true);
		
	}
	
	
	/**
	 * Allows retrieval of array of highlighted tiles from parent chessboard
	 */
	public ArrayList<ChessTile> getHighlightedTiles() {
		
		return this.highlightedTiles;
	}
	
	
	
}
