package com.solarwindsmsp.chess;

import static com.solarwindsmsp.chess.PieceType.PAWN;

public class Pawn extends Piece {
    
    public Pawn(PieceColor pieceColor) {
       super(pieceColor, PAWN);
    }
    
    /**
     * 
     * @param board
     * @param movementType
     * @param newX
     * @param newY
     * @return 
     */
    @Override
    protected boolean isLegalMove(ChessBoard board, MovementType movementType, int newX, int newY) {
        
        if(!ChessBoard.isLegalBoardPosition(newX, newY)) {
            return false; // new position is completely invalid
        }
        if(!added()) {
            throw new IllegalStateException("Cannot move ["+this+"] - has not been added to the board");
        }
        // check that newX and newY is possible at all from current x and y (for this piece)
        // then need to check the board to see if it's even possible (is there another piece in the way etc)
        
        // x must be the same 
        // assume WHITE therefore moving up the board
        int adjustY = getYCoordinate() + 1;
        if(getPieceColor() == PieceColor.BLACK) {
            // is black - moving down the board : y one less
            adjustY = getYCoordinate() - 1;
        }
        switch(movementType) {
            case MOVE: 
                if (getXCoordinate() == newX && (adjustY == newY)) {
                    // move is valid, check there's no piece at the new position
                    if(!board.isPieceAtPosition(newX, newY)) {
                        // whoop, whoop its ok to move here
                        return true;
                    }
                }
                break;
            case CAPTURE: // TODO implement
                
                break;
        }
        return false;
    }
}
