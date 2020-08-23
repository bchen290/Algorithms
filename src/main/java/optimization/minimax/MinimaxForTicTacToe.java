package optimization.minimax;

import testing.tictactoe.Move;
import testing.tictactoe.Player;
import testing.tictactoe.TicTacToe;

import java.util.List;

public class MinimaxForTicTacToe {
    public static MinimaxForTicTacToeResult minimax(char[][] position, int depth, boolean maximizingPlayer) {
        if (depth == 0 || TicTacToe.isGameOver(position)) {
            return new MinimaxForTicTacToeResult(null, evaluatePosition(position));
        }

        if (maximizingPlayer) {
            int maxEvaluation = Integer.MIN_VALUE;
            Move bestMove = new Move();

            List<Move> moves = TicTacToe.getAllEmptySpace(position);

            for (Move move : moves) {
                position[move.getRow()][move.getColumn()] = Player.COMPUTER.getToken();
                MinimaxForTicTacToeResult result = minimax(position, depth - 1, false);
                position[move.getRow()][move.getColumn()] = ' ';
                if (result.getEvaluation() > maxEvaluation) {
                    maxEvaluation = result.getEvaluation();
                    bestMove = move;
                }
            }

            return new MinimaxForTicTacToeResult(bestMove, maxEvaluation);
        } else {
            int minEvaluation = Integer.MAX_VALUE;
            Move worstMove = new Move();

            List<Move> moves = TicTacToe.getAllEmptySpace(position);

            for (Move move : moves) {
                position[move.getRow()][move.getColumn()] = Player.HUMAN.getToken();
                MinimaxForTicTacToeResult result = minimax(position, depth - 1, true);
                position[move.getRow()][move.getColumn()] = ' ';
                if (result.getEvaluation() < minEvaluation) {
                    minEvaluation = result.getEvaluation();
                    worstMove = move;
                }
            }

            return new MinimaxForTicTacToeResult(worstMove, minEvaluation);
        }
    }

    public static MinimaxForTicTacToeResult minimaxWithAlphaBetaPruning(char[][] position, int depth, int alpha, int beta, boolean maximizingPlayer) {
        if (depth == 0 || TicTacToe.isGameOver(position)) {
            return new MinimaxForTicTacToeResult(null, evaluatePosition(position));
        }

        if (maximizingPlayer) {
            int maxEvaluation = Integer.MIN_VALUE;
            Move bestMove = new Move();

            List<Move> moves = TicTacToe.getAllEmptySpace(position);

            for (Move move : moves) {
                position[move.getRow()][move.getColumn()] = Player.COMPUTER.getToken();
                MinimaxForTicTacToeResult result = minimaxWithAlphaBetaPruning(position, depth - 1, alpha, beta, false);
                position[move.getRow()][move.getColumn()] = ' ';
                if (result.getEvaluation() > maxEvaluation) {
                    maxEvaluation = result.getEvaluation();
                    bestMove = move;

                    alpha = Math.max(alpha, maxEvaluation);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }

            return new MinimaxForTicTacToeResult(bestMove, maxEvaluation);
        } else {
            int minEvaluation = Integer.MAX_VALUE;
            Move worstMove = new Move();

            List<Move> moves = TicTacToe.getAllEmptySpace(position);

            for (Move move : moves) {
                position[move.getRow()][move.getColumn()] = Player.HUMAN.getToken();
                MinimaxForTicTacToeResult result = minimaxWithAlphaBetaPruning(position, depth - 1, alpha, beta, true);
                position[move.getRow()][move.getColumn()] = ' ';
                if (result.getEvaluation() < minEvaluation) {
                    minEvaluation = result.getEvaluation();
                    worstMove = move;

                    beta = Math.min(beta, minEvaluation);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }

            return new MinimaxForTicTacToeResult(worstMove, minEvaluation);
        }
    }

    private static int evaluatePosition(char[][] position) {
        if (TicTacToe.checkWinner(position) == Player.COMPUTER) {
            return 1;
        } else if (TicTacToe.checkWinner(position) == Player.HUMAN) {
            return -1;
        } else {
            return 0;
        }
    }
}
