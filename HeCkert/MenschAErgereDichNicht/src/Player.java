public class Player {

    private char color;

    private String name;

    Player(char color, String name) {
        this.name = name;
        this.color = color;
        // TODO Error Handling
    }
    
    public void startTurn() {
    }

    public int chooseField() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public String getName() {
        return this.name;
        // TODO Error Handling
    }
}
