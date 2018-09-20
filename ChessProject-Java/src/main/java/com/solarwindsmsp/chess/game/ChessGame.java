
package com.solarwindsmsp.chess.game;

import com.solarwindsmsp.chess.ChessBoard;
import java.util.List;

/**
 *
 * @author Andy
 */
public class ChessGame {
    
    Player white;
    Player black;
    
    ChessBoard board;
    
    /**
     * An ordered list of each successful move. Could be used to undo within a game situation, replay the game etc
     * index of list defines whether its the white players move or black (white odd, black even)
     */
    List<Move> moves;
}
