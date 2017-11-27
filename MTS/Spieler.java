
public class Spieler{
	private String spielerName;
	private String spielerFarbe;
	
	private Wuerfel a;
	private Figur [] figur;
	
	
	public Spieler(String name, String farbe){
		this.spielerName = name;
		this.spielerFarbe = farbe;
		
		a = new Wuerfel();
		
		figur = new Figur[4];

	}

	public int wuerfeln(){
		return(a.wuerfeln());
	}
	
	public Figur figurWaehlen(int figurNummer){
		Figur b = null;
		
		if(figurNummer < 1 || figurNummer > 4){
			System.out.println("Fehler! Figurnummer nicht korrekt.");
		}else{
			b = figur[figurNummer-1];
		}
		
		return b;
	}
	
	public String [] spielerDetailsAnzeigen(){
		String [] auswertung = new String [2];
		auswertung[0] = spielerName;
		auswertung[1] = spielerFarbe;
		return auswertung;
	}
}
