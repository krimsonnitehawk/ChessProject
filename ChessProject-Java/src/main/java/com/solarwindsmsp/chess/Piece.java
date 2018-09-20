
package com.solarwindsmsp.chess;

/**
 *
 * @author Andy
 */
public abstract class Piece {
    
    private Integer xCoordinate;
    private Integer yCoordinate;
    private final PieceColor pieceColor;
    private final PieceType pieceType;

    public Piece(PieceColor pieceColor, PieceType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }  
    
    public boolean added() {
        return xCoordinate != null;
    }


    public void setCoordinates(Integer xCoordinate, Integer yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Integer getYCoordinate() {
        return yCoordinate;
    }
    
    public Integer getXCoordinate() {
        return xCoordinate;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }
    
    public PieceType getPieceType() {
        return pieceType;
    }
    
    @Override
    public String toString() {
        return currentPositionAsString();
    }

    private String currentPositionAsString() {
        return String.format("%n"+this.getClass().getSimpleName()+" Current X: %1d%n Current Y: %2d%n Piece Colour: %3s", getXCoordinate(), getYCoordinate(), getPieceColor());
    }
    
    /**
     * Depending on the movementType checks to see whether this piece can move to the position (newX,newY).
     * @param board
     * @param movementType
     * @param newX
     * @param newY
     * @return {@code true} if this is a legal move, {@code false} otherwise.
     */
    abstract boolean isLegalMove(ChessBoard board, MovementType movementType, int newX, int newY);

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.xCoordinate != null ? this.xCoordinate.hashCode() : 0);
        hash = 83 * hash + (this.yCoordinate != null ? this.yCoordinate.hashCode() : 0);
        hash = 83 * hash + (this.pieceColor != null ? this.pieceColor.hashCode() : 0);
        hash = 83 * hash + (this.pieceType != null ? this.pieceType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Piece other = (Piece) obj;
        if (this.xCoordinate != other.xCoordinate && (this.xCoordinate == null || !this.xCoordinate.equals(other.xCoordinate))) {
            return false;
        }
        if (this.yCoordinate != other.yCoordinate && (this.yCoordinate == null || !this.yCoordinate.equals(other.yCoordinate))) {
            return false;
        }
        if (this.pieceColor != other.pieceColor) {
            return false;
        }
        if (this.pieceType != other.pieceType) {
            return false;
        }
        return true;
    }

    
}
