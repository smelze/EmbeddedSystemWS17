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
			//Überprüfen ob Name und Farbe bereits vorhanden!!
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int anzahl = 0;
			while(anzahl == 0) {
			System.out.println("Geben Sie die Anzahl der Spieler ein.");
			String anzahlHelp = sc.nextLine();
			anzahl = Integer.parseInt(anzahlHelp);
			if(anzahl <= 1 || anzahl > 4) {
				System.out.println("Fehlerhafte Eingabe. Spielerzahl nur zwischen 2 und 4 möglich.");
				anzahl = 0;
			}
		}
		//System.out.println(anzahl);
		
		Verwaltung a = new Verwaltung(anzahl);
		
		sc.close();
		
		System.out.println("Die Spieler lauten:");
		for(int i=0;i<anzahl;i++) {
			String [] details = new String [2];
			details = spielerArray[i].spielerDetailsAnzeigen();
			System.out.println("Spieler"+(i+1)+": "+details[0]+", Farbe: "+details[1]);
		}
		
		int zahl = 0;
		//while(spielBeendet) {
			for(int i=0;i<anzahl;i++) {
				System.out.println("Spieler"+(i+1)+" ist an der Reihe.");
				zahl = spielerArray[i].wuerfeln();
				System.out.println("Spieler"+(i+1)+" hat eine "+zahl+" gewürfelt.");
				//Überprüfe ob Figur auf Feld
				//Falls ja, Figur auswählen und bewegen
				//Falls nein, Neue Figur setzen?
				//Falls ja, Erneut würfeln
				
			}
	}
	
	/*public int bestimmeNaechstenSpieler(int anzahlspieler) {
		
		return ;
	}*/
}