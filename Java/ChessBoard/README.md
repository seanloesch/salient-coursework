### About This Project
This program reflects the culmination of my second semester of object-oriented
coding experience - it was the final project for the primary coding course for
the semester.

This program was originally written in the Eclipse IDE for Java Developers.

_Last Updated: May 12th, 2022_

# OVERVIEW
This program builds a chessboard GUI out of `JPanel`s with with randomly placed
pieces allowing for various interactions. The primary function of the board is
to highlight the possible legal moves (minus capturing) of any piece on the
board. This is achieved through the assembly of a data structure comprised of 
`JPanel` objects repurposed to `ChessTile`s mapped to one another.

## HOW TO USE
Run the program from `Main.java`. The chessboard will then be displayed.

Clicking on an empty tile: This will display instructions for the movement of
the pieces involved in the game.

Clicking on a tile with a specific piece: This will highlight the possible tiles
to which that piece may move. Note that only one piece's possible moves can be
highlighted at a time, and each type has a unique highlight color:

- **King**: Gold
- **Queen**: Magenta
- **Bishop**: Blue
- **Knight**: Green
- **Rook**: Red
- **Pawn**: Pink

## LIMITATIONS
- Pieces cannot move.
- Special movement cases such as en passant and castling are ignored due to
context and movement dependency.
- Capturable tiles occupied by enemy pieces are not highlighted.
