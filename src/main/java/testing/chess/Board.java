package testing.chess;

public class Board {
    private static final int BOARD_SIZE = 8;

    private final char[][] board;

    public Board() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
    }

    public void resetBoard() {
        // Place black rooks
        board[0][0] = 'r';
        board[0][BOARD_SIZE - 1] = 'r';

        board[BOARD_SIZE - 1][0] = 'R';
        board[BOARD_SIZE - 1][BOARD_SIZE - 1] = 'R';

        // Place black knight
        board[0][1] = 'n';
        board[0][BOARD_SIZE - 2] = 'n';

        board[BOARD_SIZE - 1][1] = 'N';
        board[BOARD_SIZE - 1][BOARD_SIZE - 2] = 'N';

        // Place black bishops
        board[0][2] = 'b';
        board[0][BOARD_SIZE - 3] = 'b';

        board[BOARD_SIZE - 1][2] = 'B';
        board[BOARD_SIZE - 1][BOARD_SIZE - 3] = 'B';

        // Place queen
        board[0][3] = 'q';

        board[BOARD_SIZE - 1][3] = 'Q';

        // Place king
        board[0][BOARD_SIZE - 4] = 'k';

        board[BOARD_SIZE - 1][BOARD_SIZE - 4] = 'K';

        for(int i = 0; i < BOARD_SIZE; i++) {
            // Places black pawn
            board[1][i] = 'p';

            // Places white pawn
            board[BOARD_SIZE - 2][i] = 'P';
        }

        for (int i = 2; i < BOARD_SIZE - 2; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        System.out.print("   ");

        for(int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((char)('a' + i) + " ");
        }

        System.out.println();

        System.out.print("   ");

        for(int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("- ");
        }

        System.out.println();

        for(int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((i + 1) + "| ");

            for(int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }

            System.out.println(" |" + (i + 1));
        }

        System.out.print("   ");

        for(int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("- ");
        }

        System.out.println();

        System.out.print("   ");

        for(int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((char)('a' + i) + " ");
        }

        System.out.println();
    }
}
