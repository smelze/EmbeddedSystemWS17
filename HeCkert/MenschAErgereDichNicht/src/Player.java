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
     * @return <code>int</code> value of the position where the Meeple should be placed is returned.
     */
    public int chooseField() {
        System.out.println(this.name + " please choose the field you want to move your meeple to by typing a number");
        Scanner userInput = new Scanner(System.in);
        int choosenField = -1; 
        try{
            choosenField=userInput.nextInt();
        }
        catch(InputMismatchException e1){
            choosenField = this.chooseField();
            
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
}
