package testing.chess;

public class Chess {
    private static final int BOARD_SIZE = 9;

    private final char[][] board;

    public Chess() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
    }
}
