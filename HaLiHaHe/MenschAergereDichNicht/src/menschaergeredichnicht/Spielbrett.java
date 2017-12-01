package menschaergeredichnicht;

public class Spielbrett {

    private char[] Lauffeld = new char[40];
    private char[] StartHaeuser = new char[16];
    private char[] ZielHaeuser = new char[16];
    public Spielbrett()
    {
        for(int i = 0; i<40;i++)
        {
            Lauffeld[i] = '_';
        }
        for(int i = 0; i<16;i++)
        {
            StartHaeuser[i] = 'x';
            ZielHaeuser[i] = '_';
        }
    }
    public void PositionLesenAlle() 
        {
            // *pointer; 
            // Figur.PositionLesen() ;
        
        }
        // 
    public void SpielbrettAnzeigen()
    {
        System.out.println("##########Mensch aergere dich nicht!##############");
        System.out.println("##		   ["+Lauffeld[38]+"]["+Lauffeld[39]+"]["+Lauffeld[0]+"]Spieler1{"+StartHaeuser[0]+StartHaeuser[1]+StartHaeuser[2]+StartHaeuser[3]+"}      ##");
        System.out.println("##		   ["+Lauffeld[37]+"]("+ZielHaeuser[0]+")["+Lauffeld[1]+"]			##");
        System.out.println("##		   ["+Lauffeld[36]+"]("+ZielHaeuser[1]+")["+Lauffeld[2]+"]			##");
        System.out.println("##   Spieler4{"+StartHaeuser[12]+StartHaeuser[13]+StartHaeuser[14]+StartHaeuser[15]+"}["+Lauffeld[35]+"]("+ZielHaeuser[2]+")["+Lauffeld[3]+"]                    ##");
        System.out.println("##     ["+Lauffeld[30]+"]["+Lauffeld[31]+"]["+Lauffeld[32]+"]["+Lauffeld[33]+"]["+Lauffeld[34]+"]("+ZielHaeuser[3]+")["+Lauffeld[4]+"]["+Lauffeld[5]+"]["+Lauffeld[6]+"]["+Lauffeld[7]+"]["+Lauffeld[8]+"]        ##");
        System.out.println("##     ["+Lauffeld[29]+"]("+ZielHaeuser[12]+")("+ZielHaeuser[13]+")("+ZielHaeuser[14]+")("+ZielHaeuser[15]+")   ("+ZielHaeuser[7]+")("+ZielHaeuser[6]+")("+ZielHaeuser[5]+")("+ZielHaeuser[4]+")["+Lauffeld[9]+"]        ##");
        System.out.println("##     ["+Lauffeld[28]+"]["+Lauffeld[27]+"]["+Lauffeld[26]+"]["+Lauffeld[25]+"]["+Lauffeld[24]+"]("+ZielHaeuser[11]+")["+Lauffeld[14]+"]["+Lauffeld[13]+"]["+Lauffeld[12]+"]["+Lauffeld[11]+"]["+Lauffeld[10]+"]        ##");
        System.out.println("##		   ["+Lauffeld[23]+"]("+ZielHaeuser[10]+")["+Lauffeld[15]+"]Spieler2{"+StartHaeuser[4]+StartHaeuser[5]+StartHaeuser[6]+StartHaeuser[7]+"}      ##");
        System.out.println("##		   ["+Lauffeld[22]+"]("+ZielHaeuser[9]+")["+Lauffeld[16]+"]                    ##");
        System.out.println("##		   ["+Lauffeld[21]+"]("+ZielHaeuser[8]+")["+Lauffeld[17]+"]                    ##");
        System.out.println("##   Spieler3{"+StartHaeuser[8]+StartHaeuser[9]+StartHaeuser[10]+StartHaeuser[11]+"}["+Lauffeld[20]+"]["+Lauffeld[19]+"]["+Lauffeld[18]+"]                    ##");
        System.out.println("##################################################");
    }
}
