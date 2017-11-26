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

    public int rollDice() {
        return (int)(Math.random()*10);
    }// End of rollDice()

    private char checkForcedActionSix(char color) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int[] checkPossibleMoves(int diceCount,int playerCounter) {
        int[] output={1,2,3};
//    checkForcedAction();
        return output;
    }//End of checkPossibleMoves()

    Rules() {
    }//End of Rules() constructor

    public void initGame() {
        //--- local variables ----------------------------------------
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerLine = new Scanner(System.in);
        int playerCount,counter;
        PlayerList = new ArrayList<>();
        //------------------------------------------------------------
        System.out.println("Wie viele Spieler wollen Teilnehmen?");
        playerCount = scannerInt.nextInt();//Eingabe von Buchstaben füht zu Absturz --- Besser allg. lesen und check Data --- auch noch abfangen max 4
        System.out.println("Eingabe:" + String.valueOf(playerCount)); // Check Ausgabe kann später gelöscht werden
        for(counter=1;counter<=playerCount;counter++){
            switch(counter){
                case 1:
                    System.out.println("Bitte einen Namen für Spieler " + counter + " eingeben:");
                    PlayerList.add(new Player('r',scannerLine.nextLine()));
                    break;
                case 2:
                    System.out.println("Bitte einen Namen für Spieler " + counter + " eingeben:");
                    PlayerList.add(new Player('g',scannerLine.nextLine()));
                    break;
                case 3:
                    System.out.println("Bitte einen Namen für Spieler " + counter + " eingeben:");
                    PlayerList.add(new Player('b',scannerLine.nextLine()));
                    break;
                case 4:
                    System.out.println("Bitte einen Namen für Spieler " + counter + " eingeben:");
                    PlayerList.add(new Player('y',scannerLine.nextLine()));
                    break;
                default:
                    System.out.println("1 bis 4 Spieler möglich...Hier lief etwas falsch...suche den Fehler ;-P");
                    break;
            }    
        }
        gameBoard = new GameBoard(playerCount);
        loopGame();
    }//End of function initGame()
    private void turn(){
    
    }//End of function turn()
    
    public void loopGame(){
        //--- local variables -----------
        int counter=0, diceCount, choosedPosition;
        //-------------------------------
        do{
            //System.out.println("Player " + (counter+1) + " heißt " + PlayerList.get(counter).getName());
            PlayerList.get(counter).startTurn();
            diceCount=rollDice();
            //choosedPosition = PlayerList.get(counter).chooseField(checkPossibleMoves(diceCount,counter));
            //gameBoard.moveMeeple(choosedPosition, (choosedPosition + diceCount));
            counter++;
        }while(counter < PlayerList.size());
    }// End of function loopGame()
}

