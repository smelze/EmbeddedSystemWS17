public class Rules {

    private int diceResult;
    private GameBoard board;

    private boolean checkFieldStart() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private boolean checkFieldEnd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private char checkBase(char color) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private char checkForcedAction(char color) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public char rollDice(char color) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private char checkForcedActionSix(char color) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int[] checkPossibleMoves() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Rules() {
        int players = 4;
        board = new GameBoard(players);
        System.out.println("Rules finsihed");
        Player player = new Player('r', "Peter");
        player.startTurn();
    }

    public void gameFlow() {
    }
}
