package menschaergeredichnicht;

public class Figur {

    private int Nummer;

    private int Position;
    
    private int AnzahlPositionen;  //Anzahl der Felder auf Spielfeld, bei 4 Spielern=40 Felder
    
    private int PositionZielhaus;  //An dieser Position liegt das Zielhaus des Spielers
    
    private int istStartHaus = 1;    
    private int istZielHaus  = 2;    
    private int istLauffeld  = 3;
    
    public int status; 

    public boolean Beweglichkeit;
    
    public Figur(int nummer, int startPosition, int anzahlPositionen, int zielPosition)
    {
        Nummer = nummer;
        //Position = startPosition;
        AnzahlPositionen = anzahlPositionen;
        PositionZielhaus = zielPosition;
    }
               
            
    public void PositionBewegen(int wuerfelZahl) {                
        if (Beweglichkeit)
        {
            if ( (Position >= (PositionZielhaus - 5)) && (Position + wuerfelZahl >= PositionZielhaus) && (Position + wuerfelZahl - PositionZielhaus <=4) )  //Abfrage, ob Figur mit Würfel-Ergebnis ins Zielhaus kommen könnte
            {
                 for (int i=0; i<4; i++) //For-Schleife, damit alle Figuren des Spielers durchgeprueft werden
                {
                    if (Figuren[i].PositionLesen != (Position + wuerfelZahl - PositionZielhaus) && (Figuren[i].StatusLesen != 3) )
                    {
                        status = 3;
                        Position = Position + wuerfelZahl - PositionZielhaus;
                    }
                    else
                    {
                        System.out.println("Die Figur "+ Nummer +"kann nicht im Haus bewegt werden, da die Position bereits vergeben ist.");
                    }
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
    
    public int StatusLesen() {
        return status;
    }
  
}
