package menschaergeredichnicht;

public abstract class Spieler {

    public String Name;

    public int[] StartHaus = new int [4];   //Position IM StartHaus

    public int[] ZielHaus = new int [4];    //Position IM ZielHaus

    public int PositionZielhaus;            //Position WO das ZielHaus auf dem Spielfeld angesiedelt ist
   
    public int PositionStarthaus;           //Position WO das StartHaus auf dem Spielfeld angesiedelt ist
    
    private int SpielerNummer ;

    public Figur[] Figuren;
    

    public Spieler(String name, int spielernummer) 
    {
        Name = name;
        SpielerNummer = spielernummer;
        
        switch(SpielerNummer)
        {
            case 1:
                PositionStarthaus = 1;
                PositionZielhaus = 40;

            break;

            case 2:
                PositionStarthaus = 11;
                PositionZielhaus = 10;

            break;

            case 3:
                PositionStarthaus = 21;
                PositionZielhaus = 20;

            break;

            case 4:
                PositionStarthaus = 31;
                PositionZielhaus = 30;

            break;
            
            default:
                System.out.print("Fehler bei der Ziel- und Starthaus-Zuweisung!");
            break;
        };
         
        for (int i=0; i<4; i++)
        {
            Figur figur = new Figur(i+1, PositionStarthaus, 40, PositionZielhaus); 
            Figuren[i] = figur; // Position Starthaus muss überarbeitet werden, da die Figur nun direkt auf dem Feld sitzt
        }
    
    }

    abstract int FigurAuswaehlen(); 
   

    private int FigurBewegen(int Nummer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void ZugAusfuehren() {
    }
   
   
    
    
  
    
}
