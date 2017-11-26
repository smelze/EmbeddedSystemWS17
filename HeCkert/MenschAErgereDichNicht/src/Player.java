import java.util.*; // includes Scanner and Exceptions
public class Player {

private static final boolean DEBUG_MODE = true;

    private final char color;

    private final String name;

    Player(char color, String name) {
        this.name = name;
        this.color = color;
        // TODO Error Handling
    }
    
    /**
     * Promts a request to the user to roll the die and waits until the user
     * presses the r key
     */
    public void startTurn() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println(name + " it is your turn! (proceed by rolling the die with r + Enter)");
        while(!(sc.nextLine().equalsIgnoreCase("r"))){}
        // TODO Error Handling
    }

    
     /**
     * Promts the playername and asks the player to choose a field 
     * where he wants to put his meeple. Checks if the typed value is an 
     * <code>int</code> and recurses if it is an other datatype until an 
     * <code>int</code> is entered.
     *
     * @param possibleStartPositions
     * @return <code>int</code> value of the position where the Meeple should be placed is returned.
     */
    public int chooseField(int[]possibleStartPositions) {
        System.out.println(this.name + " please choose the field you want to move your meeple from by typing the number.");
        System.out.println("Possible movements start from:");
        for(int i=0; i<possibleStartPositions.length; i++)
        {
            System.out.print(possibleStartPositions[i]);
            if(i!=(possibleStartPositions.length -1))
                System.out.print(" , ");
        }
        Scanner userInput = new Scanner(System.in);
        int choosenField = -1; 
        try{
            boolean loop = true;
            while(loop){
                choosenField=userInput.nextInt();
                for(int i=0; i<possibleStartPositions.length; i++)
                {
                    if(choosenField == possibleStartPositions[i])
                        loop=false;
                }
                if(loop == true){
                    System.out.println(choosenField + " is not an Option at the moment.");
                    System.out.println("Please chose again:");
                }
            }
        }
        catch(InputMismatchException e1){
            choosenField = this.chooseField(possibleStartPositions);
            
            if(DEBUG_MODE){
            System.out.println("InputMismatchException");    
            }
        }
        return choosenField;
    }
    
    /**
     * Returns the name of <code>Player</code>
     * @return 
     * Returns the name of the <code>Player</code> as <code>String</code>
     */
    
    public String getName() {
        return this.name;
        // TODO Error Handling
    }
    
    /**
     * Returns the color of <code>Player</code>
     * @return 
     * Returns the color of the <code>Player</code> as <code>char</code>
     */
    public char getColor(){
        return this.color;
    }
}
