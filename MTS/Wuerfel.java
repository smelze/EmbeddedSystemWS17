import java.util.Random;

public class Wuerfel{
	private Random zufallsgenerator = new Random();
	private int augenzahl;
	
	public Wuerfel(){}
	
	public int wuerfeln(){	
         augenzahl = zufallsgenerator.nextInt(6)+1;
        //System.out.println(augenzahl);
        return augenzahl;
	}
}