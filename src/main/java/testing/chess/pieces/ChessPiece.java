package testing.chess.pieces;

import testing.chess.Board;
import testing.chess.Position;

public interface ChessPiece {
    boolean checkValidMove(Board board, Position moveLocation);
}
