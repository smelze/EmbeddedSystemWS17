public class Figur {

    private int Nummer;

    private int Position;

    public boolean Beweglichkeit;
    
    public Figur(int nummer, int startPosition)
    {
        Nummer = nummer;
        Position = startPosition;
    }

    public void PositionSetzen(int wuerfelZahl) {
        if (Beweglichkeit)
        {
            Position = Position + wuerfelZahl;
        }
        else
        {
            System.out.println("Die Figur "+ Nummer +"kann nicht um" + wuerfelZahl+" bewegt werden");
        }
    }

    public int PositionLesen() {
        return Position;
    }

  
}
