public abstract class Spieler {

    public String Name;

    public int[] StartHaus;

    public int[] ZielHaus;

    public int PositionZielhaus;

    public int PositionStarthaus;
    
    private int SpielerNummer ;    
    

    public Spieler(String name, int spielernummer) 
    {
        Name = name;
        SpielerNummer = spielernummer;
    }

    abstract int FigurAuswaehlen() ;
        
   

    private int FigurBewegen(int Nummer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void ZugAusfuehren() {
    }
   
    
    
  
    
}
