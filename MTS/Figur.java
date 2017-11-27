
public class Figur{
	private int status;
	private int position;

	public Figur(){
		status = 0;
		position = 0;
	}


	public int [] figurAuswerten(){
		int [] auswertung = new int [2];
		auswertung[0] = status;
		auswertung[1] = position;
		return auswertung;
	}
	
	public int figurBewegen(int schritte){
		position = position + schritte;
		return position;
	}
	
	public int figurStatusAendern(int statusNeu){
		status = statusNeu;
		return status;
	}
}