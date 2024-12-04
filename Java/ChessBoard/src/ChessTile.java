import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import pieces.*;

/**
 * Sean Loesch
 * 5/12/2022
 * Assignment 6
 * 
 * The purpose of this class is to establish a system of "tiles" that belong on a chessboard that contain
 * directional references to one another so that a particular piece's possible moves can be highlighted
 * upon the click of the button on a particular tile
 */
public class ChessTile implements ActionListener {
	
	/**
	 *  
	 */
	private Chessboard parentBoard;
	
	/**
	 * Natural color of this tile. Used to restore original color when unhighlighted
	 */
	private Color naturalColor;
	
	/**
	 * References to the corresponding adjacent tiles in every direction
	 */
	private ChessTile n;
	private ChessTile s;
	private ChessTile e;
	private ChessTile w;
	private ChessTile nw;
	private ChessTile ne;
	private ChessTile sw;
	private ChessTile se;
	
	/**
	 * ChessPiece present in this tile
	 */
	private ChessPiece chessPiece;
	
	/**
	 * Instantiating button so that it can be used in the actionPerformed method
	 */
	private JButton button;
	
	/**
	 * JPanel that contains the button
	 */
	private JPanel tile;
	
	
	/**
	 * Passed string (either "light" or "dark") determines the shade of this tile, so that a chessboard created
	 * with these tiles can have a base pattern
	 */
	ChessTile(String shade) throws IllegalArgumentException {
		
		tile = new JPanel(new GridLayout());
		
		button = new JButton();
		button.addActionListener(this);
		button.setFocusable(false);
		
		// Setting color according to value passed in as a parameter
		if (shade.toLowerCase().equals("light")) {
			naturalColor = new Color(250, 234, 218);
			button.setBackground(naturalColor);
			
		} else if (shade.toLowerCase().equals("dark")) {
			naturalColor = new Color(123, 129, 125);
			button.setBackground(naturalColor);
			
		} else {
			throw new IllegalArgumentException("Incorrect input - color can only be either \"light\" or \"dark\"");
		}
		
		tile.setBorder(BorderFactory.createLineBorder(Color.black));
		
		tile.add(button);
		
	}

	
	/**
	 * Makes the icon of the passed ChessPiece this panel's button. If the passed ChessPiece is null, does nothing
	 */
	public void add(ChessPiece pieceIn) {
		
		try {
			
			button.setIcon(pieceIn.getIcon());
			
			chessPiece = pieceIn;
			
		} catch (NullPointerException e) {}
		
	}
	
	
	
	/**
	 * Highlights the moves that the piece in this tile can make. Only allows one piece's moveset to be highlighted
	 * at any time.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// Restoring all previously highlighted tiles to their natural color so that only one piece's
		// available moves are highlighted at a time
		for (int i = 0; i < parentBoard.getHighlightedTiles().size(); i++) {
			
			parentBoard.getHighlightedTiles().get(i).unHighlight();
		}
		
		// Emptying getHighlightedTiles() after previously highlighted tiles have been unhightlighted
		// to conserve space
		parentBoard.getHighlightedTiles().clear();
		
		
		// If this "tile" is empty, display instruction window
		if (chessPiece == null) {
			new InstructionWindow();
			return;
		} 
		
		
		
		if (e.getSource() == button) {
			
			// KING
			if (chessPiece.getClass() == King.class) {
				
				// Possible tiles this piece can move to
				ChessTile[] moveTiles = {this.nw, this.n, this.ne, this.w, this.e, this.sw, this.s, this.se};
				
				for (int i = 0; i < moveTiles.length; i++) {
					
					ChessTile currTile = moveTiles[i];
					
					// If the targeted tile exists and is empty, highlight it
					if (currTile != null && currTile.chessPiece == null) {
						
						parentBoard.getHighlightedTiles().add(currTile);
						
						// King's highlight color is yellow
						currTile.highlight(Color.YELLOW);
					}
					
				}
				
			}
			
			// QUEEN
			if (chessPiece.getClass() == Queen.class) {
				
				ChessTile currTile = this;
				
				// NORHTWEST
				while (currTile.nw != null && currTile.nw.chessPiece == null) {
					
					currTile = currTile.nw;
					
					// Queen's highlight color is magenta
					currTile.highlight(Color.MAGENTA);
					parentBoard.getHighlightedTiles().add(currTile);
				}
				
				// Resetting pointer to current tile
				currTile = this;
				
				// NORTH
				while (currTile.n != null && currTile.n.chessPiece == null) {
					
					currTile = currTile.n;
					
					// Queen's highlight color is magenta
					currTile.highlight(Color.MAGENTA);
					parentBoard.getHighlightedTiles().add(currTile);
				}

				// Resetting pointer to current tile
				currTile = this;
				
				// NORTHEAST
				while (currTile.ne != null && currTile.ne.chessPiece == null) {
					
					currTile = currTile.ne;
					
					// Queen's highlight color is magenta
					currTile.highlight(Color.MAGENTA);
					parentBoard.getHighlightedTiles().add(currTile);
				}

				// Resetting pointer to current tile
				currTile = this;
				
				// WEST
				while (currTile.w != null && currTile.w.chessPiece == null) {
					
					currTile = currTile.w;
					
					// Queen's highlight color is magenta
					currTile.highlight(Color.MAGENTA);
					parentBoard.getHighlightedTiles().add(currTile);
				}

				// Resetting pointer to current tile
				currTile = this;
				
				// EAST
				while (currTile.e != null && currTile.e.chessPiece == null) {
					
					currTile = currTile.e;
					
					// Queen's highlight color is magenta
					currTile.highlight(Color.MAGENTA);
					parentBoard.getHighlightedTiles().add(currTile);
				}

				// Resetting pointer to current tile
				currTile = this;
				
				// SOUTHWEST
				while (currTile.sw != null && currTile.sw.chessPiece == null) {
					
					currTile = currTile.sw;
					
					// Queen's highlight color is magenta
					currTile.highlight(Color.MAGENTA);
					parentBoard.getHighlightedTiles().add(currTile);
				}

				// Resetting pointer to current tile
				currTile = this;
				
				// SOUTH
				while (currTile.s != null && currTile.s.chessPiece == null) {
					
					currTile = currTile.s;
					
					// Queen's highlight color is magenta
					currTile.highlight(Color.MAGENTA);
					parentBoard.getHighlightedTiles().add(currTile);
				}

				// Resetting pointer to current tile
				currTile = this;
				
				// SOUTHEAST
				while (currTile.se != null && currTile.se.chessPiece == null) {
					
					currTile = currTile.se;
					
					// Queen's highlight color is magenta
					currTile.highlight(Color.MAGENTA);
					parentBoard.getHighlightedTiles().add(currTile);
				}
				
			}
			
			// BISHOP
			if (chessPiece.getClass() == Bishop.class) {
				
				ChessTile currTile = this;
				
				// NORHTWEST
				while (currTile.nw != null && currTile.nw.chessPiece == null) {
					
					currTile = currTile.nw;
					
					// Bishop's highlight color is blue
					currTile.highlight(Color.BLUE);
					parentBoard.getHighlightedTiles().add(currTile);
				}
				
				// Resetting pointer to current tile
				currTile = this;
				
				// NORTHEAST
				while (currTile.ne != null && currTile.ne.chessPiece == null) {
					
					currTile = currTile.ne;
					
					// Bishop's highlight color is blue
					currTile.highlight(Color.BLUE);
					parentBoard.getHighlightedTiles().add(currTile);
				}

				// Resetting pointer to current tile
				currTile = this;
				
				// SOUTHWEST
				while (currTile.sw != null && currTile.sw.chessPiece == null) {
					
					currTile = currTile.sw;
					
					// Bishop's highlight color is blue
					currTile.highlight(Color.BLUE);
					parentBoard.getHighlightedTiles().add(currTile);
				}

				// Resetting pointer to current tile
				currTile = this;
				
				// SOUTHEAST
				while (currTile.se != null && currTile.se.chessPiece == null) {
					
					currTile = currTile.se;
					
					// Bishop's highlight color is blue
					currTile.highlight(Color.BLUE);
					parentBoard.getHighlightedTiles().add(currTile);
				}
				
			}
			
			// KNIGHT
			if (chessPiece.getClass() == Knight.class) {
				
				// Possible tiles this piece can move
				ChessTile[] moveTiles = new ChessTile[8];
				
				// Since the knight is the only piece that can move more than 2 tiles at once whose moveset programming
				// does not require a while loop, null cases are not easily avoided. Thus, this solution is necessary to
				// keep the programming running smoothly.
				try {moveTiles[0] = this.nw.n;}
				catch (NullPointerException exc) {}
				
				try {moveTiles[1] = this.ne.n;}
				catch (NullPointerException exc) {}
				
				try {moveTiles[2] = this.nw.w;}
				catch (NullPointerException exc) {}
				
				try {moveTiles[3] = this.ne.e;}
				catch (NullPointerException exc) {}
				
				try {moveTiles[4] = this.sw.w;}
				catch (NullPointerException exc) {}
				
				try {moveTiles[5] = this.se.e;}
				catch (NullPointerException exc) {}
				
				try {moveTiles[6] = this.sw.s;}
				catch (NullPointerException exc) {}
				
				try {moveTiles[7] = this.se.s;}
				catch (NullPointerException exc) {}
				// -----------------------------------------------------------------------------------------------------
				
				for (int i = 0; i < moveTiles.length; i++) {
					
					ChessTile currTile = moveTiles[i];
					
					// If the targeted tile exists and is empty, highlight it
					if (currTile != null && currTile.chessPiece == null) {
						
						parentBoard.getHighlightedTiles().add(currTile);
						
						// Knight's highlight color is green
						currTile.highlight(Color.GREEN);
					}
					
				}
				
			}
			
			// ROOK
			if (chessPiece.getClass() == Rook.class) {
				
				ChessTile currTile = this;
				
				// NORTH
				while (currTile.n != null && currTile.n.chessPiece == null) {
					
					currTile = currTile.n;
					
					// Rook's highlight color is red
					currTile.highlight(Color.RED);
					parentBoard.getHighlightedTiles().add(currTile);
				}

				// Resetting pointer to current tile
				currTile = this;
				
				// SOUTH
				while (currTile.s != null && currTile.s.chessPiece == null) {
					
					currTile = currTile.s;
					
					// Rook's highlight color is red
					currTile.highlight(Color.RED);
					
					parentBoard.getHighlightedTiles().add(currTile);
				}

				// Resetting pointer to current tile
				currTile = this;
				
				// EAST
				while (currTile.e != null && currTile.e.chessPiece == null) {
					
					currTile = currTile.e;
					
					// Rook's highlight color is red
					currTile.highlight(Color.RED);
					
					parentBoard.getHighlightedTiles().add(currTile);
				}

				// Resetting pointer to current tile
				currTile = this;
				
				// WEST
				while (currTile.w != null && currTile.w.chessPiece == null) {
					
					currTile = currTile.w;
					
					// Rook's highlight color is red
					currTile.highlight(Color.RED);
					
					parentBoard.getHighlightedTiles().add(currTile);
				}

				// Resetting pointer to current tile
				currTile = this;
				
			}
			
			// BLACK PAWN
			if (chessPiece.equals(new Pawn("black"))) {
				
				// The only direction a black pawn can move is down (south)
				ChessTile southTile = this.s;
				
				// If the tile below (south of) this black pawn is empty and exists, highlight it
				if (southTile != null && southTile.chessPiece == null) {
					
					parentBoard.getHighlightedTiles().add(southTile);
					
					// Pawn's highlight color is pink
					southTile.highlight(Color.PINK);
				}
				
			}
			
			// WHITE PAWN
			if (chessPiece.equals(new Pawn("white"))) {
				
				// The only direction a white pawen can move is up (north)
				ChessTile northTile = this.n;
				
				// If the tile above (north of) this black pawn is empty and exists, highlight it
				if (northTile != null && northTile.chessPiece == null) {
					
					parentBoard.getHighlightedTiles().add(northTile);
					
					// Pawn's highlight color is pink
					northTile.highlight(Color.PINK);
				}
				
			}
			
		}
		
	}
	
	
	
	/**
	 * Returns this JPanel
	 */
	public JPanel getTile() {
		
		return tile;
	}
	
	/**
	 * Returns the ChessPiece present in this tile, returns null if no ChessPiece is present
	 */
	public ChessPiece getChessPiece() {
		
		return this.chessPiece;
	}
	
	/**
	 * Highlights this tile with specified color
	 */
	public void highlight(Color color) {
		
		button.setBackground(color);
		
	}
	
	/**
	 * Unhighlights this tile and restores its original color
	 */
	public void unHighlight() {
		
		button.setBackground(naturalColor);
	}
	
	/**
	 * Sets the north of this tile
	 */
	public void setN(ChessTile tileIn) {
		
		this.n = tileIn;
	}
	
	/**
	 * Sets the south of this tile
	 */
	public void setS(ChessTile tileIn) {
		
		this.s = tileIn;
	}
	
	/**
	 * Sets the east of this tile
	 */
	public void setE(ChessTile tileIn) {
		
		this.e = tileIn;
	}
	
	/**
	 * Sets the west of this tile
	 */
	public void setW(ChessTile tileIn) {
		
		this.w = tileIn;
	}
	
	/**
	 * Sets the northwest of this tile
	 */
	public void setNW(ChessTile tileIn) {
		
		this.nw = tileIn;
	}
	
	/**
	 * Sets the northeast of this tile
	 */
	public void setNE(ChessTile tileIn) {
		
		this.ne = tileIn;
	}
	
	/**
	 * Sets the southwest of this tile
	 */
	public void setSW(ChessTile tileIn) {
		
		this.sw = tileIn;
	}
	
	/**
	 * Sets the southeast of this tile
	 */
	public void setSE(ChessTile tileIn) {
		
		this.se = tileIn;
	}
	
	/**
	 * Allows connection from a tile to its parent board 
	 */
	public void setParentBoard(Chessboard parentIn) {
		
		parentBoard = parentIn;
	}
	
}

