package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.exception.IllegalMoveException;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn blackPawn;
    private Pawn whitePawn;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.blackPawn = new Pawn(PieceColor.BLACK);
        this.whitePawn = new Pawn(PieceColor.WHITE);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.add(blackPawn, 6, 3);
        assertEquals(6, blackPawn.getXCoordinate().intValue());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.add(blackPawn, 6, 3);
        assertEquals(3, blackPawn.getYCoordinate().intValue());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.add(blackPawn, 6, 3);
        boolean exception = false;
        Piece captured = null;
        try {
            captured = chessBoard.move(blackPawn, MovementType.MOVE, 7, 3);
        } catch (IllegalMoveException ime) {
            exception = true;
        }
        assertTrue(exception);
        assertEquals(6, blackPawn.getXCoordinate().intValue());
        assertEquals(3, blackPawn.getYCoordinate().intValue());
        assertFalse(chessBoard.isPieceAtPosition(7, 3));
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.add(blackPawn, 6, 3);
        boolean exception = false;
        Piece captured = null;
        try {
            captured = chessBoard.move(blackPawn, MovementType.MOVE, 4, 3);
        } catch (IllegalMoveException ime) {
            exception = true;
        }
        assertTrue(exception);
        assertEquals(6, blackPawn.getXCoordinate().intValue());
        assertEquals(3, blackPawn.getYCoordinate().intValue());
        assertFalse(chessBoard.isPieceAtPosition(4, 3));
    }

    @Test
    public void testBlackPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.add(blackPawn, 6, 3);
        boolean exception = false;
        Piece captured = null;
        try {
            captured = chessBoard.move(blackPawn, MovementType.MOVE, 6, 2);
        } catch (IllegalMoveException ime) {
            exception = true;
        }
        assertNull(captured);
        assertFalse(exception);
        assertEquals(6, blackPawn.getXCoordinate().intValue());
        assertEquals(2, blackPawn.getYCoordinate().intValue());
        assertTrue(chessBoard.isPieceAtPosition(6, 2));
    }

    @Test
    public void testWhitePawn_Move_IlLegalCoordinates_Backwards_DoesNotMove() {
        chessBoard.add(whitePawn, 6, 3);
        boolean exception = false;
        Piece captured = null;
        try {
            captured = chessBoard.move(whitePawn, MovementType.MOVE, 6, 2);
        } catch (IllegalMoveException ime) {
            exception = true;
        }
        assertTrue(exception);
        assertEquals(6, whitePawn.getXCoordinate().intValue());
        assertEquals(3, whitePawn.getYCoordinate().intValue());
    }

    @Test
    public void testWhitePawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.add(whitePawn, 6, 3);
        boolean exception = false;
        Piece captured = null;
        try {
            captured = chessBoard.move(whitePawn, MovementType.MOVE, 6, 4);
        } catch (IllegalMoveException ime) {
            exception = true;
        }
        assertNull(captured);
        assertFalse(exception);
        assertEquals(6, whitePawn.getXCoordinate().intValue());
        assertEquals(4, whitePawn.getYCoordinate().intValue());
    }

    @Test
    public void testWhitePawn_Move_IlLegalCoordinates_Forward_Off_Board() {
        chessBoard.add(whitePawn, 6, 7);
        boolean exception = false;
        Piece captured = null;
        try {
            captured = chessBoard.move(whitePawn, MovementType.MOVE, 6, 8);
        } catch (IllegalMoveException ime) {
            exception = true;
        }
        assertTrue(exception);
        assertEquals(6, whitePawn.getXCoordinate().intValue());
        assertEquals(7, whitePawn.getYCoordinate().intValue());
    }

    @Test
    public void testBlackPawn_Move_IlLegalCoordinates_Forward_Off_Board() {
        chessBoard.add(blackPawn, 6, 0);
        boolean exception = false;
        Piece captured = null;
        try {
            captured = chessBoard.move(blackPawn, MovementType.MOVE, 6, -1);
        } catch (IllegalMoveException ime) {
            exception = true;
        }
        assertTrue(exception);
        assertEquals(6, blackPawn.getXCoordinate().intValue());
        assertEquals(0, blackPawn.getYCoordinate().intValue());
    }

    @Test
    public void testBlackPawn_Move_IlLegalCoordinates_Backwards_DoesNotMove() {
        chessBoard.add(blackPawn, 6, 3);
        boolean exception = false;
        Piece captured = null;
        try {
            captured = chessBoard.move(blackPawn, MovementType.MOVE, 6, 4);
        } catch (IllegalMoveException ime) {
            exception = true;
        }
        assertTrue(exception);
        assertEquals(6, blackPawn.getXCoordinate().intValue());
        assertEquals(3, blackPawn.getYCoordinate().intValue());
    }
    
    @Test
    public void testHasMoved() {
        chessBoard.add(blackPawn, 6, 3);
        assertTrue(blackPawn.hasMoved());
    }
    
    @Test
    public void testHasNotMoved() {
        chessBoard.add(blackPawn, 0, 6);
        assertFalse(blackPawn.hasMoved());
    }
}
