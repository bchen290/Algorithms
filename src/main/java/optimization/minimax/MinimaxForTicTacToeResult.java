package optimization.minimax;

import testing.tictactoe.Move;

@SuppressWarnings("unused")
public class MinimaxForTicTacToeResult {
    private final Move move;
    private final int evaluation;

    MinimaxForTicTacToeResult(Move move, int evaluation) {
        this.move = move;
        this.evaluation = evaluation;
    }

    public Move getMove() {
        return move;
    }

    public int getEvaluation() {
        return evaluation;
    }
}
