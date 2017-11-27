import java.util.Scanner;

public class Verwaltung {

	int anzahlSpieler;
	boolean spielBeendet;
	int anDerReihe;
	static Spieler[] spielerArray;
	static Scanner sc = new Scanner(System.in);
	// boolean Regeln;
	
	public Verwaltung(int anzahlSpieler) {
		
		spielerArray = new Spieler[anzahlSpieler];

		for(int i=0;i<anzahlSpieler;i++){
			System.out.println("Geben Sie den Namen des Spielers ein.");
			String name = sc.nextLine();
			System.out.println("Geben Sie die Farbe des Spielers ein.");
			String farbe = sc.nextLine();
			spielerArray[i] = new Spieler(name,farbe);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Geben Sie die Anzahl der Spieler ein.");
		String anzahlHelp = sc.nextLine();
		int anzahl = Integer.parseInt(anzahlHelp);
		//System.out.println(anzahl);
		
		
		Verwaltung a = new Verwaltung(anzahl);
		
		sc.close();
		
		for(int i=0;i<anzahl;i++) {
			String [] d = new String [2];
			d = spielerArray[i].spielerDetailsAnzeigen();
			System.out.println("Spieler"+(i+1)+": "+d[0]+", Farbe: "+d[1]);
		}
		
		
	}
	
	/*public int bestimmeNaechstenSpieler(int anzahlspieler) {
		
		return ;
	}*/
}