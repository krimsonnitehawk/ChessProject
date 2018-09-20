package com.solarwindsmsp.chess;

public enum PieceType {

    KING(1, null),
    QUEEN(1, 9), 
    ROOK(2, 5),
    BISHOP(2, 3),
    KNIGHT(2, 3),
    PAWN(8, 1);

    /**
     * The max number per type and colour
     */
    int startingNumber;
    
    /**
     * the value of each piece type (could be used during a game, looking at {@link Player#captured}
     */
    Integer points;
    
    private PieceType(int startingNumber, Integer points) {
        this.startingNumber = startingNumber;
        this.points = points;                
    }

    public int getStartingNumber() {
        return startingNumber;
    }

    
}
