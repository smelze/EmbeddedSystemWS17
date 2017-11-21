//    Author  Sebastian Hentges
//    EmbeddedSystemWS17\HeCkert\MenschAErgereDichNicht\src\Meeple.java
//    
//    Meeple class represents tokens of board games.
//

public class Meeple {

    private int position;

    private final char color;

    public Meeple(char color, int initialPosition) {
        this.color=color;
        this.setPosition(initialPosition);
    }

    /**
     * Assigns a new Position to <code>Meeple</code>
     * @param newPosition <code>int</code> value where <code>Meeple</code>
     * shall be positioned
     */
    // Made final so it can be called in constructor without issues
    final public void setPosition(int newPosition) {
        this.position = newPosition;
    }
    
    /**
     * Returns the current position of this <code>Meeple</code>
     * @return 
     * Returns current Position as <code>int</code> value
     */
    public int getPosition() {
        return this.position;
    }
}
