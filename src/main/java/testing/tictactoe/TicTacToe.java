package testing.tictactoe;

import java.util.Scanner;

public class TicTacToe {
    private static final int BOARD_SIZE = 3;

    private final char[][] board;
    private Player player;
    private Move move;

    private AIStrategy strategy;

    private final Scanner scanner;

    private boolean gameIsRunning;

    public TicTacToe() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        scanner = new Scanner(System.in);

        move = new Move();

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

            if (hasWinner()) {
                printBoard();
                System.out.println(player + " has won!");
                gameIsRunning = false;
            } else if (isBoardFull()) {
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
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (board[i][j] == ' ') {
                        return move.setRow(i).setColumn(j);
                    }
                }
            }
        } else if (strategy == AIStrategy.MINIMAX){
            return move;
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

    public boolean hasWinner() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ')
                return true;
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ' )
                return true;
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return true;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return true;
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }
}

enum Player {
    HUMAN, COMPUTER;

    private char token;

    public char getToken() {
        return token;
    }

    public void setToken(char token) {
        this.token = token;
    }
}

enum AIStrategy {
    BASIC, MINIMAX
}

class Move {
    private int row;
    private int column;

    Move() {
        this.row = -1;
        this.column = -1;
    }

    Move(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public Move setRow(int row) {
        this.row = row;
        return this;
    }

    public int getColumn() {
        return column;
    }

    public Move setColumn(int column) {
        this.column = column;
        return this;
    }
}
