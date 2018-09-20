/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solarwindsmsp.chess.game;

import com.solarwindsmsp.chess.MovementType;
import com.solarwindsmsp.chess.Piece;

/**
 * Idea class about how to record each move within a game
 * @author Andy
 */
class Move {
    Piece piece; // the piece being moved
    int currentX; // where it currently is as its (x,y) changes when it moves
    int currentY;
    int newX; // the new position
    int newY;
    MovementType movementType; // move or capture
    Piece captured; // the piece captured if capturing

    public Move(Piece piece, int currentX, int currentY, int newX, int newY, MovementType movementType) {
        this.piece = piece;
        this.currentX = currentX;
        this.currentY = currentY;
        this.newX = newX;
        this.newY = newY;
        this.movementType = movementType;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }

    public Piece getPiece() {
        return piece;
    }
}
