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
    private int[] checkOut(char color){
        int[] blocked={0,0,0,0};
        return blocked;
    }
    
    public int rollDice() {
        return (int)(Math.random()*10);
    }// End of rollDice()
    
    private int[] checkForcedAction(char color, int diceCount) {
        //--- local Variables-----------------------------------------------
        int[] meeplePosition, returnPositions={99,99,99,99}, enemyPosition,allEnemyPositions;
        int counter, counterEnemy,returnCounter=0,counterPlayer=0,targetPosition=0;
        //------------------------------------------------------------------
        meeplePosition = gameBoard.checkMeeple(color);
        allEnemyPositions = new int[12];
        switch(color){
            case 'r':
                counterPlayer=0;
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
                counterPlayer=1;
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
                counterPlayer=2;
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
                counterPlayer=3;
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
            //Überschlag abfangen
            if((meeplePosition[counter]+diceCount)>55){
                //HomeBereich
                if((meeplePosition[counter]+diceCount)<=59){
                    targetPosition=meeplePosition[counter]+diceCount+(counterPlayer*4);
                }
                //Überschlag
                else
                    targetPosition=meeplePosition[counter]+diceCount-39;
            }
            //Kein Überschlag
            else{
                targetPosition=meeplePosition[counter]+diceCount;
            }
            for (counterEnemy=0;counterEnemy<12;counterEnemy++)
                if(targetPosition == allEnemyPositions[counterEnemy]){
                    returnPositions[returnCounter] = meeplePosition[counter];
                    returnCounter++;
                    counterEnemy=12;
                }
        
        
        return returnPositions;
    }

    private int[] checkForcedActionSix(char color) {
        //--- local Variables---------------------------
        int[] meeplePosition,returnPositions={99,99,99,99};
        int counter,counterTargetPosition, returnCounter=0, outPosition,startPosition;
        //----------------------------------------------
        
        outPosition = gameBoard.getOutPosition(color);
        startPosition = gameBoard.getStartPosition(color);
        meeplePosition = gameBoard.checkMeeple(color);
        
        //Abfrage Meeple auf Startposition
        for (counter =0; counter < 4; counter++){
            if (meeplePosition[counter]==startPosition){
                returnPositions[0]=meeplePosition[counter];
                counter=4;
                //Abfrage ob eigener Meeple 6 vor StartPosition
                for(counterTargetPosition=0;counterTargetPosition<4;counterTargetPosition++){
                    if (meeplePosition[counterTargetPosition]==(startPosition+6)){
                        returnPositions[0]=meeplePosition[counterTargetPosition];
                        //Abfrage ob Meeple 12 vor Startposition 
                        for(counterTargetPosition=0;counterTargetPosition<4;counterTargetPosition++){
                            if (meeplePosition[counterTargetPosition]==(startPosition+12)){
                                returnPositions[0]=meeplePosition[counterTargetPosition];
                                //Abfrage ob Meeple 18 vor Startposition 
                                for(counterTargetPosition=0;counterTargetPosition<4;counterTargetPosition++){
                                    if (meeplePosition[counterTargetPosition]==(startPosition+18)){
                                        returnPositions[0]=meeplePosition[counterTargetPosition];
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //Wenn keiner Auf Startposition ..Abfrage Meeple im Out?
        if (returnPositions [0] == 99)
            for (counter =0; counter < 4; counter++)
            {
                if ((meeplePosition[counter] >= outPosition)&&(meeplePosition[counter] <= (outPosition+3))){
                    returnPositions[returnCounter] = meeplePosition[counter];
                    returnCounter++;
                }
            }
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
        /*
        for (counter = 0;counter<4;counter++){
            if((possibleMoves[counter]+diceCount)>55){
                if((choosedPosition+diceCount)<=59){
                    targetPosition=choosedPosition+diceCount+(counterPlayer*4);
                }
                else
                    targetPosition=choosedPosition+diceCount-39;//falsch
            }
            else{
                targetPosition=choosedPosition+diceCount;
            }
        }
        */
        
        
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
        int counterPlayer=0, counterColor, counterMeeple,counterMeeple2, diceCount, choosedPosition,free=0,targetPosition;
        int[] meeplePositions;
        char color;
        boolean win=false;
        //-------------------------------
        do{
            do{
                PlayerList.get(counterPlayer).startTurn();
                diceCount=rollDice();
                choosedPosition = PlayerList.get(counterPlayer).chooseField(checkPossibleMoves(diceCount,counterPlayer));
                if((choosedPosition+diceCount)>55){
                    if((choosedPosition+diceCount)<=59){
                        targetPosition=choosedPosition+diceCount+(counterPlayer*4);
                    }
                    else
                        targetPosition=choosedPosition+diceCount-39;//falsch
                }
                else{
                    targetPosition=choosedPosition+diceCount;
                }
                for(counterColor=0;counterColor<4;counterColor++){
                    meeplePositions=gameBoard.checkMeeple(PlayerList.get(counterColor).getColor());
                    free=0+counterColor*4;
                    for(counterMeeple=0;counterMeeple<4;counterMeeple++){
                        if (meeplePositions[counterMeeple]==targetPosition){
                            for(counterMeeple2=0;counterMeeple2<4;counterMeeple2++){
                                if(meeplePositions[counterMeeple2]==free){
                                    free++;
                                    counterMeeple2=0;
                                }
                            }
                            gameBoard.moveMeeple(meeplePositions[counterMeeple], free);
                            counterMeeple=4;
                        }
                    }
                }
                gameBoard.moveMeeple(choosedPosition,(targetPosition));

                //Hier check win conditions?


                counterPlayer++;
            }while(counterPlayer < PlayerList.size());
            counterPlayer =0;
        }while(!win);
        System.out.println("Herzlichen Glückwunsch "+PlayerList.get(counterPlayer).getName()+" Sie haben gewonnen");
    }// End of function loopGame()
}

