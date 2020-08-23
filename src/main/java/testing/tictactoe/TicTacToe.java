package testing.tictactoe;

import optimization.minimax.MinimaxForTicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    private static final int BOARD_SIZE = 3;

    private final char[][] board;

    private Player player;
    private static final Move move = new Move();

    private final AIStrategy strategy;

    private final Scanner scanner;

    private boolean gameIsRunning;

    public TicTacToe() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        scanner = new Scanner(System.in);

        strategy = AIStrategy.MINIMAX;
    }

    public void startGame() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = ' ';
            }
        }

        player = Math.random() > 0.5 ? Player.HUMAN : Player.COMPUTER;

        if (player == Player.HUMAN) {
            Player.HUMAN.setToken('X');
            Player.COMPUTER.setToken('O');
        } else {
            Player.HUMAN.setToken('O');
            Player.COMPUTER.setToken('X');
        }

        gameIsRunning = true;

        playGame();
    }

    private void playGame() {
        while(gameIsRunning) {
            printBoard();
            playMove(player == Player.HUMAN ? humanMove() : aiMove());

            if (hasWinner(board)) {
                printBoard();
                System.out.println(player + " has won!");
                gameIsRunning = false;
            } else if (isBoardFull(board)) {
                System.out.println("It's a tie!");
                gameIsRunning = false;
            }

            player = player == Player.HUMAN ? Player.COMPUTER : Player.HUMAN;
        }
    }

    private Move humanMove() {
        System.out.print("Enter your move as row (space) column: ");

        while (true) {
            try {
                String input = scanner.nextLine();
                String[] inputSplit = input.split(" ");

                return new Move(Integer.parseInt(inputSplit[0]), Integer.parseInt(inputSplit[1]));
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Invalid move! Please reenter your move as row (space) column");
            }
        }
    }

    private Move aiMove() {
        if (strategy == AIStrategy.BASIC) {
            return getNextEmptySpace(board);
        } else if (strategy == AIStrategy.MINIMAX){
            return MinimaxForTicTacToe.minimax(board, Integer.MAX_VALUE, true).getMove();
        }

        return move;
    }

    private void playMove(Move move) {
        board[move.getRow()][move.getColumn()] = player.getToken();
    }

    void printBoard() {
        System.out.print("   ");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i);

            if (i != BOARD_SIZE - 1) {
                System.out.print(" | ");
            }
        }

        System.out.println();

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j]);

                if (j != BOARD_SIZE - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();

            if (i != BOARD_SIZE - 1) {
                System.out.println(" -------------");
            }
        }

        System.out.println();
    }

    public static Player checkWinner(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return board[i][0] == Player.HUMAN.getToken() ? Player.HUMAN : Player.COMPUTER;
            }
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ' ) {
                return board[0][i] == Player.HUMAN.getToken() ? Player.HUMAN : Player.COMPUTER;
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return board[0][0] == Player.HUMAN.getToken() ? Player.HUMAN : Player.COMPUTER;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return board[0][2] == Player.HUMAN.getToken() ? Player.HUMAN : Player.COMPUTER;
        }

        return null;
    }

    public static boolean hasWinner(char[][] board) {
        return checkWinner(board) != null;
    }

    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isGameOver(char[][] board) {
        return isBoardFull(board) || hasWinner(board);
    }

    public static Move getNextEmptySpace(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == ' ') {
                    return move.setRow(i).setColumn(j);
                }
            }
        }

        return move;
    }

    public static List<Move> getAllEmptySpace(char[][] board) {
        List<Move> emptySpaces = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == ' ') {
                    emptySpaces.add(new Move(i, j));
                }
            }
        }

        return emptySpaces;
    }
}

enum AIStrategy {
    BASIC, MINIMAX
}
