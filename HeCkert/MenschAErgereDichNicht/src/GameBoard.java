/*
Gameboard defines the number of meeple as well as the color of all meeple.
For every Player of the game, 4 meeple are created.

Meeple are set to positions 0 - 15 (the "out" areas) of the board.
Positions 0-3(r), 4-7(g), 8-11(b) and 12-15(y) represent the out areas of 
red, green, blue and yellow respectively

Positions 16 - 56 represent the circle track.
Positions 16(r), 26(b), 36(g) and 46(y) represent the start points of 
red, blue, green and yellow respectively.

Positions 47 - 63 represent the "home" areas of the board.
Positions 47-50(r), 51-54(g), 55-58(b) and 59-62(y) represent the home areas of 
red, green, blue and yellow respectively
*/

import java.util.*;
import javax.swing.JFrame;

public class GameBoard extends JFrame{

    private ArrayList<Meeple> MeepleList;
    private ListIterator<Meeple> MeepleIterator;
    
    /**
     * <code>GameBoard</code> has to be initialized with 2 - 4 Players.
     * Gameboard does set the Startposition for Meeple and their color.
     * @param playerCount 
     * <code>int</code> value, indicating the number of players to be initialized
     */
    public GameBoard(int playerCount) {
        if(playerCount >= 2 && playerCount <= 4){
            
            MeepleList = new ArrayList<>();
            MeepleIterator = MeepleList.listIterator(0);
            
            char color = 'y';
            
            for(int i=0; i<(4*playerCount); i++){
                if(i<=4) color = 'r';
                else if(i<=8) color = 'g';
                else if (i<=12) color = 'b';
                MeepleList.add(new Meeple(color,i));
            }
        }
        initUI();
    }
    
        private void initUI() {

        add(new MaenschAErgereDichNichtPanel());

        pack();

        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
        
    /**
     * Returns the first position of the out area for the specified class.
     * @param color has to match one of the players colors.
     * Possible colors are <code>r, g, b, y</code>
     * @return returns the first position of the out area as <code>int</code> value.
     * Returns <code>-1</code> if the specified color does not exist.
     */
    public int getOutPosition(char color){
        int position;
        switch(color){
            case 'r':
                position = 0;
                break;
            case 'g':
                position = 4;
                break;
            case 'b':
                position = 8;
                break;
            case 'y':
                position = 12;
                break;
            default:
                position = -1;
        }
        return position;
    }
    
    /**
     * Returns the first position of the home area for the specified class.
     * @param color has to match one of the players colors.
     * Possible colors are <code>r, g, b, y</code>
     * @return returns the first position of the home area as <code>int</code> value.
     * Returns <code>-1</code> if the specified color does not exist.
     */
    public int getHomePosition(char color){
        int position;
        switch(color){
            case 'r':
                position = 47;
                break;
            case 'g':
                position = 51;
                break;
            case 'b':
                position = 55;
                break;
            case 'y':
                position = 59;
                break;
            default:
                position = -1;
        }
        return position;
    }
    
    /**
     * Returns the start position for the specified class.
     * @param color has to match one of the players colors.
     * Possible colors are <code>r, g, b, y</code>
     * @return returns start position as <code>int</code> value.
     * Returns <code>-1</code> if the specified color does not exist.
     */
    public int getStartPosition(char color){
        int position;
        switch(color){
            case 'r':
                position = 16;
                break;
            case 'g':
                position = 26;
                break;
            case 'b':
                position = 46;
                break;
            case 'y':
                position = 36;
                break;
            default:
                position = -1;
        }
        return position;
    }
    
    /**
     * 
     * @param startposition
     * @param newPosition 
     */
    public void moveMeeple(int startposition, int newPosition) {
        while(MeepleIterator.hasNext()){
            Meeple m = MeepleIterator.next();
            if(m.getPosition() == startposition ){
                m.setPosition(newPosition) ;
            }            
        }
    }

    public int[] checkMeeple(char color) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void showGameBoard() {
    }
}
