//--- imports ----------------------------------------------------------------
//import java.awt.EventQueue;
import java.util.Scanner;
import java.util.ArrayList;

  
public class Rules {
  
//--- variables --------------------------------------------------------------
    //private int diceResult;
    private ArrayList<Player> PlayerList;
    private GameBoard gameBoard;

//--- functions -------------------------------------------------------------

/** creates a random value 1<=x<=6 as a diceCount
 * !!ATTENTION!! only returns !!6!! for testing at the moment!!!!!
 * @return a random int 1<=x<=6  
 */    
private int rollDice() {
    //--- local variables --------------------------------
    int diceCount;
    //----------------------------------------------------
    //loop makes sure that 1<=dicecount<=6
    do{
       diceCount = (int)(Math.random()*10);
    }while((diceCount>6)||(diceCount<1));
    return 6;
    //return diceCount
}// End of rollDice()

/**checks if there are Forced Actions for 1<=diceCount<=5
 * Forced Actions are returned within an int[4] wich is filled witch 99s
 * if there are not 4 choosable ForcedActions
 * 
 * @param color color of the active Player
 * @param diceCount
 * @return int[4] with choosable ForcedActions filled with '99's
 */
private int[] checkForcedAction(int playerNumber, int diceCount) {
    //--- local Variables-----------------------------------------------
    int[] meeplePositions, returnPositions={99,99,99,99}, enemyPosition,allEnemyPositions;
    int counter, counterEnemy,returnCounter=0,counterPlayer=0,targetPosition=0;
    char color;
    //------------------------------------------------------------------
    color=PlayerList.get(playerNumber).getColor();
    meeplePositions = gameBoard.checkMeeple(color);
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
        if((meeplePositions[counter]+diceCount)>55){
            //HomeBereich
            if((meeplePositions[counter]+diceCount)<=59){
                targetPosition=meeplePositions[counter]+diceCount+(counterPlayer*4);
            }
            //Überschlag
            else
                targetPosition=meeplePositions[counter]+diceCount-39;
        }
        //Kein Überschlag
        else{
            targetPosition=meeplePositions[counter]+diceCount;
        }
        for (counterEnemy=0;counterEnemy<12;counterEnemy++)
            if(targetPosition == allEnemyPositions[counterEnemy]){
                returnPositions[returnCounter] = meeplePositions[counter];
                returnCounter++;
                counterEnemy=12;
            }


    return returnPositions;
}//End of CheckForcedActions()

/**This function checks if there are Forced Actions for the diceCount 6
 * Forced Actions are returned within an int[4] wich is filled witch 99s
 * if there are not 4 choosable ForcedActions 
 * 
 * @param color The color of the active Player
 * @return int[4] with choosable ForcedActions filled with '99's
 */
private int[] checkForcedActionSix(int counterPlayer) {
    //--- local Variables---------------------------
    int[] meeplePosition,returnPositions={99,99,99,99};
    int counter, returnCounter=0, outPosition,startPosition;
    char color;
    //----------------------------------------------
    
    color=PlayerList.get(counterPlayer).getColor();
    outPosition = gameBoard.getOutPosition(color);
    startPosition = gameBoard.getStartPosition(color);
    meeplePosition = gameBoard.checkMeeple(color);

    //Hier noch fehler bei hohen StartPositionen wg Überschlag möglich!!!
    
    //Abfrage Meeple auf Startposition
    if(this.checkOwnOnTarget(counterPlayer, startPosition))
        if(this.checkOwnOnTarget(counterPlayer, startPosition+6))
           if(this.checkOwnOnTarget(counterPlayer, (startPosition+12)))
               if(this.checkOwnOnTarget(counterPlayer, (startPosition+16)))
                   returnPositions[0]=(startPosition+16);
               else
                   returnPositions[0]=(startPosition+12);
           else
               returnPositions[0]=(startPosition+6);
        else
            returnPositions[0]=startPosition;
    else 
    for (counter =0; counter < 4; counter++)
    {
        if ((meeplePosition[counter] >= outPosition)&&(meeplePosition[counter] <= (outPosition+3))){
            returnPositions[returnCounter] = meeplePosition[counter];
            returnCounter++;
        }
    }
   return returnPositions;
}//End of ForcedActionSix()

/** This function checks wich moves are possible and returns an int Array with the positions of the movable Meeple
 * 
 * @param diceCount
 * @param playerCounter Number of the active Player within the ArrayList PlayerList
 * @return int[] with Positions of movable Meeple
 */    
private int[] checkPossibleMoves(int diceCount,int playerCounter) {
    //--- local variables ------------------------------------------
    int[] possibleMoves, meeplePositions,returnPositions;
    int counter,counterCheck,counterPossible=0,targetPosition,startPosition;
    boolean selbstSchlagen;
    char color;
    //--------------------------------------------------------------

    color = PlayerList.get(playerCounter).getColor();
    meeplePositions=gameBoard.checkMeeple(color);
    startPosition=gameBoard.getStartPosition(color);

    //ForcedActions
    if (diceCount == 6){
        possibleMoves=checkForcedActionSix(playerCounter);
    } 
    else{
         possibleMoves=checkForcedAction(playerCounter, diceCount);                      
    }
    //Possible Actions if no Forced Actions
    //check 1 out einfügen ... die nicht
    if (possibleMoves[0]==99){
        for (counter = 0;counter<4;counter++){
            if (meeplePositions[counter]>startPosition){//ceck ob meeple im out-Bereich
                targetPosition=this.getTargetPosition(meeplePositions[counter], diceCount, playerCounter);
                //Check ob selbst schlagen
                selbstSchlagen=false;
                for (counterCheck=0;counterCheck<4;counterCheck++){
                    if ((targetPosition)==meeplePositions[counterCheck])
                        selbstSchlagen=true;
                }//Ende check selbst schlagen
                if(!selbstSchlagen){
                    possibleMoves[counterPossible]=meeplePositions[counter];
                    counterPossible++;
                }
            }
        }                    
    }
    else{
        do{
            counterPossible++;         
        }while((counterPossible<4)&&(possibleMoves[counterPossible]!=99));
    }
    returnPositions = new int[counterPossible];
    for(counter=0;counter<counterPossible;counter++){
        returnPositions[counter]=possibleMoves[counter];
    }
    return returnPositions;
}//End of checkPossibleMoves()


/** A functon to initialise the game ... this runs once per geame
 * 
 */
public void initGame() {
    //--- local variables ----------------------------------------
    Scanner scannerInt = new Scanner(System.in);
    Scanner scannerLine = new Scanner(System.in);
    int playerCount,counter;
    PlayerList = new ArrayList<>();
    //------------------------------------------------------------
    System.out.println("Wie viele Spieler wollen Teilnehmen?");
    playerCount = scannerInt.nextInt();//Eingabe von Buchstaben füht zu Absturz 
    // --- Besser allg. lesen und check Data 
    //--- auch noch abfangen max 4
    //System.out.println("Eingabe:" + String.valueOf(playerCount)); // Check Ausgabe kann später gelöscht werden
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
    //EventQueue.invokeLater(new Runnable() {
              //  @Override
                //public void run() {
                    gameBoard = new GameBoard(playerCount);
                    gameBoard.setVisible(true);
               // }
            //});
    loopGame();
}//End of function initGame()
 
/** this is the main game loop of the game
 * 
 */
private void loopGame(){
    //--- local variables -----------
    int counterPlayer=0, diceCount, choosedPosition,targetPosition,counterRollDice=0;
    //char color;
    boolean win=false;
    //-------------------------------
    do{
        counterPlayer =0;
        do{//Alle vier Spieler nacheinander bis win
            do{//Bis zu 3 mal Würfeln
                PlayerList.get(counterPlayer).startTurn();
                diceCount=rollDice();
                choosedPosition = PlayerList.get(counterPlayer).chooseField(checkPossibleMoves(diceCount,counterPlayer));
                System.out.println("Du hast folgendes gewählt: "+choosedPosition);
                targetPosition=getTargetPosition(choosedPosition,diceCount,counterPlayer);
                System.out.println("Deine Zielposition ist: "+targetPosition);
                gameBoard.moveMeeple(choosedPosition,targetPosition);
                //schlagen fehlt !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                win=checkWin(counterPlayer);
                if (this.checkAllOut(counterPlayer))
                    counterRollDice++;
                else
                    counterRollDice=3;
            }while(counterRollDice<3);
            counterPlayer++;
            counterRollDice=0;
        }while((counterPlayer < PlayerList.size())&&(!win));     
    }while(!win);
    System.out.println("Herzlichen Glückwunsch "+PlayerList.get(counterPlayer).getName()+" Sie haben gewonnen");
}// End of function loopGame()
 
/**function checks if the active player wins the game
 * 
 * @param counterPlayer Number of the active Pkayer within the ArrayList PlayerList
 * @return win boolean if the active player wins the game
 */
private boolean checkWin(int counterPlayer){
    //--- local variables ---------------------------------
    int[] meeplePositions;
    int counterMeeple,home;
    //-----------------------------------------------------
    meeplePositions =gameBoard.checkMeeple(PlayerList.get(counterPlayer).getColor());
    home=56+(counterPlayer*4);
    for(counterMeeple=0;counterMeeple<4;counterMeeple++){
        if(meeplePositions[counterMeeple]==home){
            home++;
            counterPlayer=0;
        }
    }
        return (home==(56+(counterPlayer*4)+4));
}//End of function win

/**
 * produces a targetPosition ...checks if a Meeple is set into a new Round,
 * the house or the normal corse and returns the correct number of the target Position
 * @param startPosition 
 * @param diceCount
 * @param counterPlayer Number of the active Pkayer within the ArrayList PlayerList
 * @return targetPosition 
 */
private int getTargetPosition(int meeplePosition,int diceCount, int counterPlayer){
    // --- local variables----------------------------
    int targetPosition,startPosition,homePosition,roundEnd;
    // -----------------------------------------------
    startPosition=gameBoard.getStartPosition(PlayerList.get(counterPlayer).getColor());
    homePosition=gameBoard.getHomePosition(PlayerList.get(counterPlayer).getColor());
    roundEnd=gameBoard.getStartPosition(PlayerList.get(0).getColor())+39;
    if(meeplePosition<gameBoard.getStartPosition(PlayerList.get(0).getColor()))//Abfrage ob out
        targetPosition=startPosition;
    else
        if(((meeplePosition)>=homePosition)&&(meeplePosition<(homePosition+4))){//Abfrage ob im Haus
                targetPosition=meeplePosition+diceCount;
        }
        else{    
            if(counterPlayer==0){
                if ((meeplePosition+diceCount)>(startPosition+39)){//Ziel Entweder in neuer Runde oder im Haus
                    if((meeplePosition+diceCount)<=(roundEnd+4))//Ziel im Haus
                        targetPosition=meeplePosition+diceCount;
                    else
                        targetPosition=meeplePosition+diceCount-40;//Ziel neue Runde
                }
                else
                    targetPosition=meeplePosition+diceCount;//gleiche Runde einfach weiter    
            }    
            else{
                if((meeplePosition<startPosition)&&(meeplePosition+diceCount)>startPosition){//Vor dem Haus + Eingang möglich
                    targetPosition=meeplePosition+diceCount;
                }
                else{
                    if((meeplePosition+diceCount)>roundEnd)//neue Runde
                        targetPosition=meeplePosition+diceCount-40;
                    else
                        targetPosition=meeplePosition+diceCount; 
                }
            }
        }
    return targetPosition;
}//End of getTargetPosition()
 
private boolean checkAllOut(int counterPlayer){
    //--- local variables-------------------------------------
    int counter,counterOut=0,outPosition;
    int[] meeplePositions;
    char color;
    //--------------------------------------------------------
    color=PlayerList.get(counterPlayer).getColor();
    meeplePositions=gameBoard.checkMeeple(color);
    outPosition=gameBoard.getOutPosition(color);
    for (counter=0;counter<4;counter++){
        if (meeplePositions[counter]==(outPosition+counterOut)){
            counterOut++;
            if (counterOut!=4)
                counter=0;
            else
                return true;
        }
    }
    return false;
}//End of checkAllOut()

/**checks if there is an ownl Meeple on the target Position
 * 
 * @param counterPlayer Position of the active Player in PlayerList
 * @param targetPosition
 * @return bool true if own meeple on Target position else false
 */
private boolean checkOwnOnTarget(int counterPlayer,int targetPosition){
    //--- local Variables-------------------------------------------------
    int[] meeplePositions;
    int counter;
    //--------------------------------------------------------------------
    meeplePositions=gameBoard.checkMeeple(PlayerList.get(counterPlayer).getColor());
    for(counter=0;counter<4;counter++){
        if(meeplePositions[counter]==targetPosition)
            return true;
    }
    return false;
}//End of checkOwnOnTarget()

}//End of class Rules

