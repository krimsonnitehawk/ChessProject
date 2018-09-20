package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.exception.IllegalMoveException;

public class ChessBoard {

    public static final int BOARD_SIZE = 8;

    private final Piece[][] pieces;

    public ChessBoard() {
        // create 8 by 8 board
        pieces = new Piece[BOARD_SIZE][BOARD_SIZE];
    }
    
    /**
     * create the pieces for starting a chess game and positions them in the correct position them using {@link #add(com.solarwindsmsp.chess.Piece, int, int) }
     */
    public void init() {
        // TODO implement
    }
    
    /**
     * To set up a game from a non starting position
     * @param board 
     */
    public void setBoard(Piece[][] board) {
        // TODO implement
    }
    
    /**
     * Check, based on the piece being added, that more than the max for that piece and colour are not added to the board -
     * for example you should not be allowed to add more than 8 black pawns
     * @param piece
     * @return 
     */
    private boolean canAdd(Piece piece) {
        boolean canAdd = true;
        int count = 0;
        for (int file = 0; file < BOARD_SIZE; file++) {
            for (int rank = 0; rank < BOARD_SIZE; rank++) {
                Piece currentPiece = pieces[file][rank];
                if(currentPiece != null &&
                        (currentPiece.getPieceType() == piece.getPieceType() 
                        && currentPiece.getPieceColor() == piece.getPieceColor())) {
                    count++;
                }
            }
        }
        if(count + 1 > piece.getPieceType().getStartingNumber()) {
            canAdd = false;
        }
        return canAdd;
    }

    /**
     * 
     * @param piece
     * @param xCoordinate
     * @param yCoordinate 
     * @exception IllegalArgumentException
     */
    public void add(Piece piece, int xCoordinate, int yCoordinate) {
        
        if(isLegalBoardPosition(xCoordinate, yCoordinate) // is this a legal position and 
                && !piece.added()) { // has this piece not been added already
            Piece existingPiece = getPieceAtPosition(xCoordinate, yCoordinate);
            if(existingPiece != null) {
                // already a piece at this position
                throw new IllegalArgumentException("Piece ["+piece+"] cannot be added at ["+xCoordinate+", "+yCoordinate+"] ["+existingPiece+"] already at that position");
            }
            if(!canAdd(piece)) {
                 throw new IllegalArgumentException("Piece ["+piece+"] cannot be added at ["+xCoordinate+", "+yCoordinate+"] max no of pieces added");
            }
            piece.setCoordinates(xCoordinate, yCoordinate); // set the piece's coordinates
            placePieceOnSquare(piece, xCoordinate, yCoordinate); // and place on the board
        } else {
            throw new IllegalArgumentException("Piece ["+piece+"] cannot be added at ["+xCoordinate+", "+yCoordinate+"]");
        }
    }

    public static boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return !(xCoordinate < 0 || xCoordinate >= BOARD_SIZE || yCoordinate < 0 || yCoordinate >= BOARD_SIZE);
    }
    
    public Piece getPieceAtPosition(int xCoordinate, int yCoordinate) {
        if(isLegalBoardPosition(xCoordinate, yCoordinate)) {
            return pieces[xCoordinate][yCoordinate];
        }
        return null;
    }
    
    public boolean isPieceAtPosition(int xCoordinate, int yCoordinate) {
        if(isLegalBoardPosition(xCoordinate, yCoordinate)) {
            return pieces[xCoordinate][yCoordinate] != null;
        }
        return false;
    }
    
    /**
     * Move a piece to the new position defined by {@code newX} and {@code newY}. Returns the captured piece to be stored in {@link Player#captured}, if capturing.
     * @param piece The piece to move
     * @param movementType either {@link MovementType#MOVE} or {@link MovementType#CAPTURE}. If {@link MovementType#CAPTURE} and successful the captured piece is returne
     * @param newX the new file position
     * @param newY the new rank position
     * @return the captured piece if the movementType was {@link MovementType#CAPTURE}, {@code null} if the movementType was {@link MovementType#MOVE} or throws an exception if the move was not possible
     * @exception IllegalStateException if the piece has not been added to the board yet
     * @throws com.solarwindsmsp.chess.exception.IllegalMoveException  if the move was not possible
     */
    public Piece move(Piece piece, MovementType movementType, int newX, int newY) throws IllegalMoveException {
        Piece captured = null;
        if(piece.isLegalMove(this, movementType, newX, newY)) {
            remove(piece); // piece is moving so clear from current position on board
            piece.setCoordinates(newX, newY); // tell the piece about its new position
            
            if(movementType == MovementType.CAPTURE) {
                 captured = getPieceAtPosition(newX, newY); // there must be a piece at newX, newY
                 captured.setCoordinates(null, null); // no longer on the board
                 remove(captured); // not real need to do this as the next line is sticking the capturing piece in its place
            }

            placePieceOnSquare(piece, newX, newY); // actually move it to the new position on the board, if captured then this will replace the captured piece
        } else {
            throw new IllegalMoveException("Cannot move piece");
        }
        return captured;
    }
    
    /**
     * remove a piece from the board at its current position
     * @param piece 
     */
    private void remove(Piece piece) {
        pieces[piece.getXCoordinate()][piece.getYCoordinate()] = null; 
    }
    
    private void placePieceOnSquare(Piece piece, int newX, int newY) {
        pieces[newX][newY] = piece; 
    }
}