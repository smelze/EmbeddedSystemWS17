package menschaergeredichnicht;

import java.io.Console;

public class Mensch extends Spieler {

    public Mensch(String name, int spielernummer) {
        super(name, spielernummer);
    }

    @Override  int FigurAuswaehlen() {
        System.out.print("Bitte waehlen Sie die Nummer der Figur aus, die Sie bewegen möchten:");
        
         // Console.ReadLine()
         
         return 0; 
    }
}
