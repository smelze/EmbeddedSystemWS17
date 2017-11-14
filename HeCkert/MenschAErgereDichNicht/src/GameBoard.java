import java.util.*;

public class GameBoard {

    ArrayList<Meeple> MeepleList;
    ListIterator<Meeple> MeepleIterator;
    
    public void GameBoard(int playerCount) {
        // TODO Errorhandling
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
    }

    public void moveMeeple(int startposition, int newPosition) {
        
    }

    public int[] checkMeeple() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void showGameBoard() {
    }
}
