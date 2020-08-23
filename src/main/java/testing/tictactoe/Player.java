package testing.tictactoe;

public enum Player {
    HUMAN, COMPUTER;

    private char token;

    public char getToken() {
        return token;
    }

    public void setToken(char token) {
        this.token = token;
    }
}
