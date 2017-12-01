package menschaergeredichnicht;

public class Figur {

    private int Nummer;

    private int Position;
    
    private int AnzahlPositionen;  //Anzahl der Felder auf Spielfeld, bei 4 Spielern=40 Felder
    
    private int PositionZielhaus;  //An dieser Position liegt das Zielhaus des Spielers

    public boolean Beweglichkeit;
    
    public Figur(int nummer, int startPosition, int anzahlPositionen, int zielPosition)
    {
        Nummer = nummer;
        //Position = startPosition;
        AnzahlPositionen = anzahlPositionen;
        PositionZielhaus = zielPosition;
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
            if ( (Position >= (PositionZielhaus - 5)) && (Position + wuerfelZahl >= PositionZielhaus) && (Position + wuerfelZahl - PositionZielhaus <=4) )  //Abfrage, ob Figur mit Würfel-Ergebnis ins Zielhaus kommen könnte
            {
                if (//überprüfung, ob die Position im Haus noch frei ist)
                    {
                    //Hier Status der Figur zu "IM ZIELHAUS" einfügen
                    Position = Position + wuerfelZahl - PositionZielhaus;
                    }
                else 
                {
                System.out.println("Die Figur "+ Nummer +"kann nicht im Haus bewegt werden, da die Position bereits vergeben ist.");    
                }
            }
            else 
            {
                Position = Position + wuerfelZahl;

                if (Position > AnzahlPositionen)
                { 
                    Position = Position - AnzahlPositionen; //AnzahlPositionen wäre besser in der Klasse Spielfeld
                }
            }
            
        }
        else
        {
            System.out.println("Die Figur "+ Nummer +"kann nicht um" + wuerfelZahl+" bewegt werden.");
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
