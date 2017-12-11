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
 * @return a random int 1<=x<=6  
 */    
private int rollDice() {
    //--- local variables --------------------------------
    int diceCount;
    //---------------------------------------------------- 
    System.out.println("Funktion rollDice gestartet");
    do{//loop makes sure that 1<=dicecount<=6
       diceCount = (int)(Math.random()*10);
    }while((diceCount>6)||(diceCount<1));
    return diceCount;
}// End of rollDice()


//Arbeitet aktuell als check schlagen??
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
    int counter,returnCounter=0,counterEnemy,counterMeeple,targetPosition;
    char color;
    //------------------------------------------------------------------
    System.out.println("Funktion checkForcedAction gestartet");
    color=PlayerList.get(playerNumber).getColor();
    meeplePositions = gameBoard.checkMeeple(color);
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
    for(counterMeeple=0;counterMeeple<4;counterMeeple++){
        targetPosition=this.getTargetPosition(meeplePositions[counterMeeple], diceCount, playerNumber);
        for (counterEnemy=0;counterEnemy<12;counterEnemy++)
            if(targetPosition == allEnemyPositions[counterEnemy]){
                returnPositions[returnCounter] = meeplePositions[counterMeeple];
                returnCounter++;
                counterEnemy=12;
            }
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
    System.out.println("Funktion checkForcedActionSix gestartet");
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
    System.out.println("Funktion checkPossibleMoves gestartet");
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
        System.out.println("Keine ForcedActions");
        for (counter = 0;counter<4;counter++){
            if (meeplePositions[counter]>startPosition){//ceck ob meeple im out-Bereich
                targetPosition=this.getTargetPosition(meeplePositions[counter], diceCount, playerCounter);
                //Check ob selbst schlagen
                selbstSchlagen=false;
                for (counterCheck=0;counterCheck<4;counterCheck++){
                    if ((targetPosition)==meeplePositions[counterCheck]){
                        selbstSchlagen=true;
                        System.out.println("Ausschluss durch selbst schlagen...Das sollte noch eine funkt. bekommen");
                    }
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
    if (counterPossible==0)
        System.out.println("Problem keine möglichen Züge...Fehler in checkPossibleMoves");
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
    System.out.println("Funktion initGame gestartet");
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
    int counterPlayer=0;
    //char color;
    boolean win=false;
    //-------------------------------
    System.out.println("Funktion loopGame gestartet");
    do{//Spielschleife bis einer gewonnen hat
        counterPlayer =0;
        do{//Alle  Spieler nacheinander 
            if (this.checkAllOut(counterPlayer))
                this.rollThreeTimes(counterPlayer);     
            else{
                this.turn(counterPlayer);
                win=checkWin(counterPlayer);
            }
            counterPlayer++;
            this.debugAusgabeMeeple();
        }while((counterPlayer < PlayerList.size())&&(!win));     
    }while(!win);
    System.out.println("Herzlichen Glückwunsch "+PlayerList.get(counterPlayer).getName()+" Sie haben gewonnen");
}// End of function loopGame()

/** function writes all Meeple Positions on the screen
 * 
 */
private void debugAusgabeMeeple(){
    //--- local variables ------------------------
    int[] meeplePositions;
    int counterMeeple,counterPlayer;
    //--------------------------------------------
    System.out.println("Funktion debugAusgabeMeeple gestartet");
    for(counterPlayer=0;counterPlayer<PlayerList.size();counterPlayer++){
        meeplePositions=gameBoard.checkMeeple(PlayerList.get(counterPlayer).getColor());
        System.out.print("Positionen Meeple Spieler "+counterPlayer+":");
        for(counterMeeple=0;counterMeeple<4;counterMeeple++){
            System.out.print(" "+meeplePositions[counterMeeple]+",");
        }
        System.out.println(".");
    }
}//end of debugAusgabeMeeple()

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
    System.out.println("Funktion checkWin gestartet");
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
    System.out.println("Funktion getTargetPosition gestartet");
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

/** function checks if all Meeple of one color 
 * (depending on the Number of the Player) are in the Out Area
 * 
 * @param counterPlayer Number of the active Pkayer within the ArrayList PlayerList
 * @return true if all in out area ...false if not
 */
private boolean checkAllOut(int counterPlayer){
    //--- local variables-------------------------------------
    int counter,counterOut=0,outPosition;
    int[] meeplePositions;
    char color;
    //--------------------------------------------------------
    System.out.println("Funktion checkAllOut gestartet");
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
 * //!!!!CHECK SCHLAGEN FEHLT!!!!!!!!!!
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
    System.out.println("Funktion checkOwnOnTarget gestartet");
    meeplePositions=gameBoard.checkMeeple(PlayerList.get(counterPlayer).getColor());
    for(counter=0;counter<4;counter++){
        if(meeplePositions[counter]==targetPosition)
            return true;
    }
    return false;
}//End of checkOwnOnTarget()

/** You are allowed to roll the dice up to three times if all your Meeple are in the "out-section"
 *  function implements the complete turn for one Player with all Meeple out
 * 
 * @param counterPlayer Position of the active Player in PlayerList
 */
private void rollThreeTimes(int counterPlayer){
    //--- local Variables------------------------------------------------------
    int diceCount,counter,choosedPosition,targetPosition;
    //-------------------------------------------------------------------------
    System.out.println("Funktion rollThreeTimes gestartet");
    if(this.checkAllOut(counterPlayer)){
        counter=0;
        do{
            PlayerList.get(counterPlayer).startTurn();
            diceCount=this.rollDice();
            System.out.println("Du hast "+diceCount+" gewürfelt.");
            counter++;
        }while((counter<3)&&diceCount!=6);
        if(diceCount==6){
            choosedPosition = PlayerList.get(counterPlayer).chooseField(checkPossibleMoves(6,counterPlayer));
            //System.out.println("Du hast folgendes gewählt: "+choosedPosition);//DEBUG Ausgabe
            targetPosition=getTargetPosition(choosedPosition,6,counterPlayer);
            //System.out.println("Deine Zielposition ist: "+targetPosition);//DEBUG Ausgabe
            gameBoard.moveMeeple(choosedPosition,targetPosition);
        }
        else
            System.out.println("Das war wohl nichts, vielleicht nächste Runde.");//DEBUG Ausgabe
    }
}//end of rollThreeTimes()

/**this function implements a normal turn of one Player
 * 
 * @param counterPlayer Position of the active Player within the PlayerList
 */
private void turn(int counterPlayer){
    //--- local variables ----------------------
    int diceCount, choosedPosition, targetPosition;
    //------------------------------------------
    System.out.println("Funktion turn gestartet");
    PlayerList.get(counterPlayer).startTurn();
    diceCount=rollDice();
    System.out.println("Du hast "+diceCount+" gewürfelt.");
    choosedPosition = PlayerList.get(counterPlayer).chooseField(checkPossibleMoves(diceCount,counterPlayer));
    //System.out.println("Du hast folgendes gewählt: "+choosedPosition);
    targetPosition=getTargetPosition(choosedPosition,diceCount,counterPlayer);
    //System.out.println("Deine Zielposition ist: "+targetPosition);
    gameBoard.moveMeeple(choosedPosition,targetPosition);
    //schlagen fehlt !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! vllt in eigener funkt???
}//end of turn()

}//End of class Rules

