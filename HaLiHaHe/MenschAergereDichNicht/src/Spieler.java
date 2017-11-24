public abstract class Spieler {

    public string Name;

    public int[] StartHaus;

    public int[] ZielHaus;

    public int PositionZielhaus;

    public int PositionStarthaus;

    public Spieler(string Name) {
    }

    abstract int FigurAuswaehlen() ;
        
   

    private int FigurBewegen(int Nummer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void ZugAusfuehren() {
    }
}
