package menschaergeredichnicht;

public class Figur {

    private int Nummer;

    private int Position;
    
    private int AnzahlPositionen; 

    public boolean Beweglichkeit;
    
    public Figur(int nummer, int startPosition, int anzahlPositionen)
    {
        Nummer = nummer;
        //Position = startPosition;
        AnzahlPositionen = anzahlPositionen;
    }
    
    /*
        Aufzählung zum Abgleich des Status einer Figur.
        Bspw. kann nun abgeglichen werden:
        If (Figur.Status == isStarthaus)
        { 
           Beweglichkeit = 0;
        }
    */
    public enum Status  
    {
        isStartHaus,
        isLauffeld,
        isZielHaus;
    };
            
            
            
    public void PositionBewegen(int wuerfelZahl) {                
        if (Beweglichkeit)
        {
            Position = Position + wuerfelZahl;
            
            if (Position > AnzahlPositionen)
            { 
                Position = Position - AnzahlPositionen; //AnzahlPositionen wäre besser in der Klasse Spielfeld
            }
        }
        else
        {
            System.out.println("Die Figur "+ Nummer +"kann nicht um" + wuerfelZahl+" bewegt werden");
        }
    }
    public void FigurSetzen(int neuePosition)
    {
        Position = neuePosition;
    }
    public int PositionLesen() {
        return Position;
    }

  
}
