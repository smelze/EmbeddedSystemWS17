package menschaergeredichnicht;

import java.util.Scanner;

public class Mensch extends Spieler {

    public Mensch(String name, int spielernummer) {
        super(name, spielernummer);
    }

    @Override  int FigurAuswaehlen() {
        System.out.print("Bitte waehlen Sie die Nummer der Figur aus, die Sie bewegen möchten:");
        
          Scanner scanner = new Scanner(System.in);
          int figurNummer =  scanner.nextInt();
         if (figurNummer<1 || figurNummer>4)
         {
             return FigurAuswaehlen();
         }
         else
         {             
            return figurNummer;
         }
         
    }
}
