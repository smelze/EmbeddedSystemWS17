import java.util.Random;
import java.util.Date;


public class Wuerfel {

    Random rand = new Random();
    private int Augenzahl;

    

    public int wuerfeln() {
        Augenzahl = ((rand.nextInt()*((int) new Date().getTime())%6)+1);
        
        return Augenzahl;
    }
}
