package testing.chess.pieces;

import testing.chess.Board;
import testing.chess.Position;

public class Pawn implements ChessPiece {
    @Override
    public boolean checkValidMove(Board board, Position moveLocation) {
        return false;
    }
}
