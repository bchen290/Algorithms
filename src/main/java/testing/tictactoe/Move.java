package testing.tictactoe;

public class Move {
    private int row;
    private int column;

    public Move() {
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
