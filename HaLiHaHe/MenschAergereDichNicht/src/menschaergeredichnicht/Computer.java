package menschaergeredichnicht;

public class Computer extends Spieler {

    public Computer(String name, int spielernummer) {
        super(name, spielernummer);
    }

    @Override  int FigurAuswaehlen() {
        // Keine Random-Funktion --> Computer muss "gewissenhaft" aussuchen
        // System.out.print("Bitte waehlen Sie die Nummer der Figur aus, die Sie bewegen möchten:");
        
         // Console.ReadLine()
         
         return 0; 
    }
}
