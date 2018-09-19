package com.solarwindsmsp.chess;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChessBoardTest extends TestCase {

    private ChessBoard testSubject;

    @Before
    @Override
    public void setUp() throws Exception {
        testSubject = new ChessBoard();
    }

    @Test
    public void testHas_MaxBoardWidth_of_7() {
        assertEquals(7, ChessBoard.BOARD_SIZE - 1);
    }

    @Test
    public void testHas_MaxBoardHeight_of_7() {
        assertEquals(7, ChessBoard.BOARD_SIZE - 1);
    }

    @Test
    @SuppressWarnings("static-method")
    public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isValidPosition = ChessBoard.isLegalBoardPosition(0, 0);
        Assert.assertTrue(isValidPosition);
    }

    @Test
    @SuppressWarnings("static-method")
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = ChessBoard.isLegalBoardPosition(5, 5);
        Assert.assertTrue(isValidPosition);
    }
    
    @Test
    @SuppressWarnings("static-method")
    public void testIsLegalBoardPosition_True_X_equals_7_Y_equals_7() {
        boolean isValidPosition = ChessBoard.isLegalBoardPosition(7, 7);
        Assert.assertTrue(isValidPosition);
    }

    @Test
    @SuppressWarnings("static-method")
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
        boolean isValidPosition = ChessBoard.isLegalBoardPosition(11, 5);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
        boolean isValidPosition = ChessBoard.isLegalBoardPosition(0, 9);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isValidPosition = ChessBoard.isLegalBoardPosition(11, 0);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isValidPosition = ChessBoard.isLegalBoardPosition(5, -1);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testAvoids_Duplicate_Positioning() {
        Piece firstPawn = new Pawn(PieceColor.BLACK);
        Piece secondPawn = new Pawn(PieceColor.BLACK);
        testSubject.add(firstPawn, 6, 3);
        try {
            testSubject.add(secondPawn, 6, 3);
        } catch (IllegalArgumentException ise) {
            // expected as cannot add second Pawn at the same position as the first
        }
        assertEquals(6, firstPawn.getXCoordinate().intValue());
        assertEquals(3, firstPawn.getYCoordinate().intValue());
        assertEquals(null, secondPawn.getXCoordinate());
        assertEquals(null, secondPawn.getYCoordinate());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns() {
        for (int i = 0; i < 10; i++) {
            Pawn pawn = new Pawn(PieceColor.BLACK);
            int row = i / ChessBoard.BOARD_SIZE;
            System.out.println("i[" + i + "] row[" + row + "]");
            try {
                testSubject.add(pawn, i % ChessBoard.BOARD_SIZE, 6 + row);
            } catch (IllegalArgumentException iae) {
                 System.out.println("message was ["+iae.getMessage()+"]");
            }
            System.out.println(pawn);
            if (row < 1) {
                assertEquals(i % ChessBoard.BOARD_SIZE, pawn.getXCoordinate().intValue());
                assertEquals(6 + row, pawn.getYCoordinate().intValue());

            } else {
                assertEquals(null, pawn.getXCoordinate());
                Assert.assertEquals(null, pawn.getYCoordinate());
            }
        }
    }
}
