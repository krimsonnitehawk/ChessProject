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
    @SuppressWarnings("empty-statement")
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.add(blackPawn, 6, 3);
        try {
            Piece captured = chessBoard.move(blackPawn, MovementType.MOVE, 7, 3);
        } catch (IllegalMoveException ime) {
        };
        assertEquals(6, blackPawn.getXCoordinate().intValue());
        assertEquals(3, blackPawn.getYCoordinate().intValue());
        assertFalse(chessBoard.isPieceAtPosition(7, 3));
    }

    @Test
    @SuppressWarnings("empty-statement")
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.add(blackPawn, 6, 3);
        try {
            chessBoard.move(blackPawn, MovementType.MOVE, 4, 3);
        } catch (IllegalMoveException ime) {
        };
        assertEquals(6, blackPawn.getXCoordinate().intValue());
        assertEquals(3, blackPawn.getYCoordinate().intValue());
        assertFalse(chessBoard.isPieceAtPosition(4, 3));
    }

    @Test
    @SuppressWarnings("empty-statement")
    public void testBlackPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.add(blackPawn, 6, 3);
        try {
            chessBoard.move(blackPawn, MovementType.MOVE, 6, 2);
        } catch (IllegalMoveException ime) {
        };
        assertEquals(6, blackPawn.getXCoordinate().intValue());
        assertEquals(2, blackPawn.getYCoordinate().intValue());
        assertTrue(chessBoard.isPieceAtPosition(6, 2));
    }

    @Test
    @SuppressWarnings("empty-statement")
    public void testWhitePawn_Move_IlLegalCoordinates_Backwards_DoesNotMove() {
        chessBoard.add(whitePawn, 6, 3);
        try {
            chessBoard.move(whitePawn, MovementType.MOVE, 6, 2);
        } catch (IllegalMoveException ime) {
        };
        assertEquals(6, whitePawn.getXCoordinate().intValue());
        assertEquals(3, whitePawn.getYCoordinate().intValue());
    }

    @Test
    public void testWhitePawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.add(whitePawn, 6, 3);
        try {
            chessBoard.move(whitePawn, MovementType.MOVE, 6, 4);
        } catch (IllegalMoveException ime) {
        };
        assertEquals(6, whitePawn.getXCoordinate().intValue());
        assertEquals(4, whitePawn.getYCoordinate().intValue());
    }

    @Test
    @SuppressWarnings("empty-statement")
    public void testWhitePawn_Move_IlLegalCoordinates_Forward_Off_Board() {
        chessBoard.add(whitePawn, 6, 7);
        try {
            chessBoard.move(whitePawn, MovementType.MOVE, 6, 8);
        } catch (IllegalMoveException ime) {
        };
        assertEquals(6, whitePawn.getXCoordinate().intValue());
        assertEquals(7, whitePawn.getYCoordinate().intValue());
    }

    @Test
    @SuppressWarnings("empty-statement")
    public void testBlackPawn_Move_IlLegalCoordinates_Forward_Off_Board() {
        chessBoard.add(blackPawn, 6, 0);
        try {
            chessBoard.move(blackPawn, MovementType.MOVE, 6, -1);
        } catch (IllegalMoveException ime) {
        };
        assertEquals(6, blackPawn.getXCoordinate().intValue());
        assertEquals(0, blackPawn.getYCoordinate().intValue());
    }

    @Test
    @SuppressWarnings("empty-statement")
    public void testBlackPawn_Move_IlLegalCoordinates_Backwards_DoesNotMove() {
        chessBoard.add(blackPawn, 6, 3);
        try {
            chessBoard.move(blackPawn, MovementType.MOVE, 6, 4);
        } catch (IllegalMoveException ime) {
        };
        assertEquals(6, blackPawn.getXCoordinate().intValue());
        assertEquals(3, blackPawn.getYCoordinate().intValue());
    }

    @Test
    @SuppressWarnings("empty-statement")
    public void testPawn_Move_LegalCoordinates_Updates_Board() {
        chessBoard.add(blackPawn, 6, 3);
        try {
            chessBoard.move(blackPawn, MovementType.MOVE, 6, 2);
        } catch (IllegalMoveException ime) {
        };
        assertEquals(6, blackPawn.getXCoordinate().intValue());
        assertEquals(2, blackPawn.getYCoordinate().intValue());
        assertFalse(chessBoard.isPieceAtPosition(6, 3)); // the black pawn is not longer at 6,3
        assertTrue(chessBoard.isPieceAtPosition(6, 2)); // there is a piece at 6,2
        assertEquals(blackPawn, chessBoard.getPieceAtPosition(6, 2)); // and its the black pawn!
    }
}
