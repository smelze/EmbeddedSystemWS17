//--- imports ----------------------------------------------------------------
import java.awt.EventQueue;
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

    public int rollDice() {
        return (int)(Math.random()*10);
    }// End of rollDice()
    
    private int[] checkForcedAction(char color, int diceCount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private int[] checkForcedActionSix(char color) {
        //--- local Variables---------------------------
        int[] meeplePosition={99,99,99,99},returnPositions={99,99,99,99};
        int counter, returnCounter=0, setPosition=0,startPosition=16;
        //----------------------------------------------
        
        //ersetzen durch getFunktion... wird noch erstellt
        switch (color){
            case 'r':
                setPosition = 0;
                startPosition = 16;
                break;
            case 'g':
                setPosition = 4;
                startPosition = 26;
                break;
            case 'b':
                setPosition = 8;
                startPosition = 36;
                break;
            case 'y':
                setPosition = 12;
                startPosition = 46;
                break;
            default:
                //Ikmprove Error handeling
                break;
        }

        meeplePosition = gameBoard.checkMeeple(color);
        for (counter =0; counter < 4; counter++)
        {
            if ((meeplePosition[counter] >= setPosition)&&(meeplePosition[counter] <= (setPosition+3))){
                returnPositions[returnCounter] = meeplePosition[counter];
                returnCounter++;
            }
        }
        if (returnPositions [0]!= 99){
            for (counter =0; counter < 4; counter++){
                if (meeplePosition[counter]==startPosition){
                    returnPositions[0]=returnPositions[1]=returnPositions[2]=returnPositions[3]=99;
                }
            }
        }
        else{
            for (counter =0; counter < 4; counter++){
                returnPositions[counter]=meeplePosition[counter];
            }
        }
        return returnPositions;
    }

    public int[] checkPossibleMoves(int diceCount,int playerCounter) {
        //--- local variables ------------------------------------------
        int[] possibleMoves;
        char color='r';
        //--------------------------------------------------------------
        
        //Ersetzen durch getfunktion wird noch erstellt
        switch (playerCounter){
            case 0:
                color ='r';
                break;
            case 1:
                color ='g';
                break;
            case 2:
                color ='b';
                break;
            case 3:
                color ='y';
                break;
            default:
                //implement Error handeling
                break;
        }
        if (diceCount == 6){
            possibleMoves=checkForcedActionSix(color);
        } 
        else{
             possibleMoves=checkForcedAction(color, diceCount);                      
        }
            return possibleMoves;
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
        EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        gameBoard = new GameBoard(playerCount);
                        gameBoard.setVisible(true);
                    }
                });
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
            choosedPosition = PlayerList.get(counter).chooseField(checkPossibleMoves(diceCount,counter));
            gameBoard.moveMeeple(choosedPosition, (choosedPosition + diceCount));
            
            //Hier check win conditions?
           
            
            counter++;
        }while(counter < PlayerList.size());
    }// End of function loopGame()
}

