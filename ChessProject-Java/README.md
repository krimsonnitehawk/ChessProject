# ChessProject - Java

# Implementation so far:

Create super abstract class Piece with one abstract method isLegalMove() which each piece type implements.
 
Implemented the Pawn Piece isLegalMove() - TODO deal with first move (2 squares not 1). This structure will allow new pieces to be easily implemented.

Removed the coupling between the Piece and the ChessBoard.

Changed the ChessBoard move() method to return the captured piece if capturing, or null if a move. This is a refactor with consideration for future game implementation.

Create a game package with skeleton classes to show future game ideas.

Created Position class in view to replacing integer x, y fields in Piece - Didn't have time to refactor.

Introduced idea of max number of piece type and colour to be used during ChessBoard add method to restrict the number of pieces added. This was an attempt to
get the test testLimits_The_Number_Of_Pawns in ChessBoard to pass. 

More tests for Pawn moving with black and white pawns (black going south and white north).

Never been a fan of using -1 to represent an invalid selection, changed to Integer and therefore null could be stored for a piece's coordinates before being added to the board.
Refactored tests for this change.

Added tests in ChessBoard to test legal and illegal moves (using pawns).



 


