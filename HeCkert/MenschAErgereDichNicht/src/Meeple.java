//    Author  Sebastian Hentges
//    EmbeddedSystemWS17\HeCkert\MenschAErgereDichNicht\src\Meeple.java
//    
//    Meeple class represents tokens of board games.
//

public class Meeple {

    private int position;

    private char color;


    // Made final so it can be called in constructor without later issues
    final public void setPosition(int newPosition) {
        this.position = newPosition;
        // TODO Error Handling
    }

    Meeple(char color, int initialPosition) {
        this.color=color;
        this.setPosition(initialPosition);
        // TODO Error Handling
    }

    public int getPosition() {
        return this.position;
        // TODO Error Handling
    }
}
