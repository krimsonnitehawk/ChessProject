package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.exception.IllegalMoveException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * TODO write more tests for move with new piece types and to test capture
 * @author Andy
 */
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
        assertTrue(isValidPosition);
    }

    @Test
    @SuppressWarnings("static-method")
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = ChessBoard.isLegalBoardPosition(5, 5);
        assertTrue(isValidPosition);
    }
    
    @Test
    @SuppressWarnings("static-method")
    public void testIsLegalBoardPosition_True_X_equals_7_Y_equals_7() {
        boolean isValidPosition = ChessBoard.isLegalBoardPosition(7, 7);
        assertTrue(isValidPosition);
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
        assertFalse(isValidPosition);
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

    /**
     * Wasn't 100% sure on this test.
     * Updated this test to work with the following assumptions:
     * 1. you can only have 8 pawns of a single colour on the board
     * 2. The ChessBoard checks on a add call how many pieces that
     *    match the colour and type and will not allow an add if there
     *    are already 8 pawns of that colour
     */
    @Test
    public void testLimits_The_Number_Of_Pawns() {
        for (int i = 0; i < 10; i++) {
            Pawn pawn = new Pawn(PieceColor.BLACK);
            int row = i / ChessBoard.BOARD_SIZE;
            try {
                // add from (0,6) -> (7,6) then next rank which should fail
                testSubject.add(pawn, i % ChessBoard.BOARD_SIZE, 6 + row);
            } catch (IllegalArgumentException iae) {
                // starts firing when we have reached max
                 System.out.println("message was ["+iae.getMessage()+"]");
            }
            if (row < 1) {
                assertEquals(i % ChessBoard.BOARD_SIZE, pawn.getXCoordinate().intValue());
                assertEquals(6 + row, pawn.getYCoordinate().intValue());

            } else {
                assertEquals(null, pawn.getXCoordinate());
                Assert.assertEquals(null, pawn.getYCoordinate());
            }
        }
    }
    
    @Test
    public void test_Move_LegalCoordinates_Updates_Board() {
        Piece blackPawn = new Pawn(PieceColor.BLACK);
        testSubject.add(blackPawn, 6, 3);
        boolean exception = false;
        Piece captured = null;
        try {
            captured = testSubject.move(blackPawn, MovementType.MOVE, 6, 2);
        } catch (IllegalMoveException ime) {
            exception = true;
        }
        assertNull(captured); // capture means this should be null
        assertFalse(exception); // move was valid so no exception
        assertEquals(6, blackPawn.getXCoordinate().intValue());
        assertEquals(2, blackPawn.getYCoordinate().intValue());
        assertFalse(testSubject.isPieceAtPosition(6, 3)); // the black pawn is not longer at 6,3
        assertTrue(testSubject.isPieceAtPosition(6, 2)); // there is a piece at 6,2
        assertEquals(blackPawn, testSubject.getPieceAtPosition(6, 2)); // and its the black pawn!
    }
    
    @Test
    public void test_Move_IllegalCoordinates_Does_Not_Update_Board() {
        Piece blackPawn = new Pawn(PieceColor.BLACK);
        testSubject.add(blackPawn, 6, 3);
        boolean exception = false;
        Piece captured = null;
        try {
            captured = testSubject.move(blackPawn, MovementType.MOVE, 6, 4);
        } catch (IllegalMoveException ime) {
            exception = true;
        }
        
        assertTrue(exception); // move was invalid so exception
        assertEquals(6, blackPawn.getXCoordinate().intValue()); // not gone anywhere
        assertEquals(3, blackPawn.getYCoordinate().intValue());
        assertTrue(testSubject.isPieceAtPosition(6, 3)); // the black pawn is still at 6,3
        assertEquals(blackPawn, testSubject.getPieceAtPosition(6, 3)); // and its the black pawn!
        assertFalse(testSubject.isPieceAtPosition(6, 2)); // there is no piece at 6,2
        
    }
}
