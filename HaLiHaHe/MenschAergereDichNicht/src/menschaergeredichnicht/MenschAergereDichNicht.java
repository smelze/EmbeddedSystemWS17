/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menschaergeredichnicht;

/**
 *
 * @author Markus
 */
public class MenschAergereDichNicht {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       /* Figur FigurRot = new Figur(1, 1, 40);
        FigurRot.Beweglichkeit = true;
        FigurRot.PositionSetzen(4);
        System.out.println("Die Figur steht auf Position: " +FigurRot.PositionLesen() );*/ 
// TODO code application logic here
        //Mensch mensch = new Mensch("Svenja", 3);
        //mensch.FigurAuswaehlen();
        Spielbrett Holzi = new Spielbrett();
        Holzi.SpielbrettAnzeigen();
        
        Mensch spielerRot   = new Mensch("Rot", 1);
        Mensch spielerGelb  = new Mensch("Gelb", 2);
        Mensch spielerGruen = new Mensch("Gruen", 3);
        Mensch spielerBlau  = new Mensch("Blau", 4);
        
         
        Holzi.SpielbrettAnzeigen();
        
        
        
        
        
    }
    
}
