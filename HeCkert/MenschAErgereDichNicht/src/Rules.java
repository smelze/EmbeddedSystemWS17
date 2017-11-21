//--- imports ----------------------------------------------------------------
import java.util.Scanner;
import java.util.ArrayList;

  
public class Rules {
  
//--- variables --------------------------------------------------------------
    private int diceResult;
    private ArrayList<Player> PlayerList;
    private GameBoard gameBoard;

//--- functions -------------------------------------------------------------
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

    Rules() {
    }

    public void gameFlow() {
        //--- local variables ----------------------------------------
        Scanner scanner = new Scanner(System.in);
        int playerCount,counter;
        PlayerList = new ArrayList<>();
        //------------------------------------------------------------
        
        //Spieler Anzahl bestimmen ... Spiel starten
        System.out.println("Wie viele Spieler wollen Teilnehmen?");
        playerCount = scanner.nextInt();//Eingabe von Buchstaben füht zu Absturz --- Besser allg. lesen und check Data --- auch noch abfangen max 4
        System.out.println("Eingabe:" + String.valueOf(playerCount)); // Check Ausgabe kann später gelöscht werden
       /*for(counter=0;counter<=playerCount;counter++){
            switch(counter){
                case 1:
                    PlayerList.add(new Player("r"));
                    break;
                case 2:
                    PlayerList.add(new Player("g"));
                    break;
                case 3:
                    PlayerList.add(new Player("b"));
                    break;
                case 4:
                    PlayerList.add(new Player("y"));
                    break;
                default:
                    System.out.println("1 bis 4 Spieler möglich");
                    break;
            }    
        }
        gameBoard = new GameBoard(playerCount);
        */
    }
}

