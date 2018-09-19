package com.solarwindsmsp.chess;

public enum PieceType {

    KING(1),
    QUEEN(1), 
    ROOK(2),
    BISHOP(2),
    KNIGHT(2),
    PAWN(8);

    int startingNumber;
    
    private PieceType(int startingNumber) {
        this.startingNumber = startingNumber;
    }

    public int getStartingNumber() {
        return startingNumber;
    }

}
