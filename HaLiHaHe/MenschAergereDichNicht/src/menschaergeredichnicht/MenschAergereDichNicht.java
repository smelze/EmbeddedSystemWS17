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
        Figur FigurRot = new Figur(1, 1, 40);
        FigurRot.Beweglichkeit = true;
        FigurRot.PositionSetzen(4);
        System.out.println("Die Figur steht auf Position: " +FigurRot.PositionLesen() ); 
// TODO code application logic here
    }
    
}
