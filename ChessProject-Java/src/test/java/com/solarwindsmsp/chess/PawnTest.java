package com.solarwindsmsp.chess;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.add(testSubject, 6, 3);
        assertEquals(6, testSubject.getXCoordinate().intValue());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.add(testSubject, 6, 3);
        assertEquals(3, testSubject.getYCoordinate().intValue());
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.add(testSubject, 6, 3);
        chessBoard.move(testSubject, MovementType.MOVE, 7, 3);
        assertEquals(6, testSubject.getXCoordinate().intValue());
        assertEquals(3, testSubject.getYCoordinate().intValue());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.add(testSubject, 6, 3);
        chessBoard.move(testSubject, MovementType.MOVE, 4, 3);
        assertEquals(6, testSubject.getXCoordinate().intValue());
        assertEquals(3, testSubject.getYCoordinate().intValue());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.add(testSubject, 6, 3);
        chessBoard.move(testSubject, MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate().intValue());
        assertEquals(2, testSubject.getYCoordinate().intValue());
    }

}