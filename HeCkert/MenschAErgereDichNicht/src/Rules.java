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
    Rules() {
    }//End of Rules() constructor    
    
/* momentan nicht benötigt   
    private boolean checkFieldStart() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private boolean checkFieldEnd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private char checkBase(char color) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
*/
    public int rollDice() {
        return (int)(Math.random()*10);
    }// End of rollDice()
    
    private int[] checkForcedAction(char color, int diceCount) {
        //--- local Variables-----------------------------------------------
        int[] meeplePosition, returnPositions={99,99,99,99}, enemyPosition,allEnemyPositions;
        int counter, counter2,returnCounter=0;
        //------------------------------------------------------------------
        meeplePosition = gameBoard.checkMeeple(color);
        allEnemyPositions = new int[12];
        switch(color){
            case 'r':
                enemyPosition = gameBoard.checkMeeple('g');
                for (counter=0;counter<4;counter++){
                    allEnemyPositions[counter]=enemyPosition[counter];
                }
                enemyPosition = gameBoard.checkMeeple('b');
                for (counter=0;counter<4;counter++){
                    allEnemyPositions[counter+4]=enemyPosition[counter];
                }
                enemyPosition = gameBoard.checkMeeple('y');
                for (counter=0;counter<4;counter++){
                    allEnemyPositions[counter+8]=enemyPosition[counter];
                }
                break;
            case 'g':
                enemyPosition = gameBoard.checkMeeple('r');
                for (counter=0;counter<4;counter++){
                    allEnemyPositions[counter]=enemyPosition[counter];
                }
                enemyPosition = gameBoard.checkMeeple('b');
                for (counter=0;counter<4;counter++){
                    allEnemyPositions[counter+4]=enemyPosition[counter];
                }
                enemyPosition = gameBoard.checkMeeple('y');
                for (counter=0;counter<4;counter++){
                    allEnemyPositions[counter+8]=enemyPosition[counter];
                }
                break;
            case 'b':
                enemyPosition = gameBoard.checkMeeple('g');
                for (counter=0;counter<4;counter++){
                    allEnemyPositions[counter]=enemyPosition[counter];
                }
                enemyPosition = gameBoard.checkMeeple('r');
                for (counter=0;counter<4;counter++){
                    allEnemyPositions[counter+4]=enemyPosition[counter];
                }
                enemyPosition = gameBoard.checkMeeple('y');
                for (counter=0;counter<4;counter++){
                    allEnemyPositions[counter+8]=enemyPosition[counter];
                }
                break;
            case 'y':
                enemyPosition = gameBoard.checkMeeple('g');
                for (counter=0;counter<4;counter++){
                    allEnemyPositions[counter]=enemyPosition[counter];
                }
                enemyPosition = gameBoard.checkMeeple('b');
                for (counter=0;counter<4;counter++){
                    allEnemyPositions[counter+4]=enemyPosition[counter];
                }
                enemyPosition = gameBoard.checkMeeple('r');
                for (counter=0;counter<4;counter++){
                    allEnemyPositions[counter+8]=enemyPosition[counter];
                }
                break;
            default:
                //add error handling
                break;
        }
        
        for (counter=0;counter<4;counter++)
            for (counter2=0;counter2<12;counter2++)
                if((meeplePosition[counter]+diceCount)==allEnemyPositions[counter2]){
                    returnPositions[returnCounter] = meeplePosition[counter];
                    returnCounter++;
                    counter2=12;
                }
        
        
        return returnPositions;
    }

    private int[] checkForcedActionSix(char color) {
        //--- local Variables---------------------------
        int[] meeplePosition,returnPositions={99,99,99,99};
        int counter, returnCounter=0, outPosition,startPosition;
        //----------------------------------------------
        
        outPosition = gameBoard.getOutPosition(color);
        startPosition = gameBoard.getStartPosition(color);
        meeplePosition = gameBoard.checkMeeple(color);
        
        //Abfrage Meeple auf Startposition
        for (counter =0; counter < 4; counter++){
                if (meeplePosition[counter]==startPosition){
                    returnPositions[0]=meeplePosition[counter];
                    counter=4;
                }
        }
        //Wenn keiner Auf Startposition ..Abfrage Meeple Out?
        if (returnPositions [0] == 99)
            for (counter =0; counter < 4; counter++)
            {
                if ((meeplePosition[counter] >= outPosition)&&(meeplePosition[counter] <= (outPosition+3))){
                    returnPositions[returnCounter] = meeplePosition[counter];
                    returnCounter++;
                }
            }
        /*In checkPossibleMoves ausgelagert
        //Wenn keiner auf Star oder im Out, dann freie Wahl
        if (returnPositions [0]== 99)
            for (counter =0; counter < 4; counter++){
                returnPositions[counter]=meeplePosition[counter];
            }
        */
        
        return returnPositions;
    }

    public int[] checkPossibleMoves(int diceCount,int playerCounter) {
        //--- local variables ------------------------------------------
        int[] possibleMoves, meeplePositions,returnPositions;
        int counter,counter2;
        char color;
        //--------------------------------------------------------------
        
        color = PlayerList.get(playerCounter).getColor();
        meeplePositions=gameBoard.checkMeeple(color);
        
        //ForcedActions
        if (diceCount == 6){
            possibleMoves=checkForcedActionSix(color);
        } 
        else{
             possibleMoves=checkForcedAction(color, diceCount);                      
        }
        //Possible Actions if no Forced Actions
        if (possibleMoves[0]==99){
            for (counter = 0;counter<4;counter++){
                possibleMoves[counter]=meeplePositions[counter];
            }                    
        }
        /*Array verkürzen ...erstmal unwichtig ...vllt kontraproduktiv
        for(counter=1;counter<4;counter++)
            if (possibleMoves[counter]==99){
                returnPositions= new int[counter+1];
                for(counter2=0;counter<=counter;counter++)
            }
        */        
        //Selbst schlagen muss noch abgefangen werden
        return possibleMoves;
    }//End of checkPossibleMoves()



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
 
    public void loopGame(){
        //--- local variables -----------
        int counter=0, diceCount, choosedPosition;
        boolean win=false;
        //-------------------------------
        do{
            do{
                //System.out.println("Player " + (counter+1) + " heißt " + PlayerList.get(counter).getName());
                PlayerList.get(counter).startTurn();
                diceCount=rollDice();
                choosedPosition = PlayerList.get(counter).chooseField(checkPossibleMoves(diceCount,counter));
                gameBoard.moveMeeple(choosedPosition,(choosedPosition + diceCount));

                //Hier check win conditions?


                counter++;
            }while(counter < PlayerList.size());
            counter =0;
        }while(!win);
        System.out.println("Herzlichen Glückwunsch "+PlayerList.get(counter).getName()+" Sie haben gewonnen");
    }// End of function loopGame()
}

