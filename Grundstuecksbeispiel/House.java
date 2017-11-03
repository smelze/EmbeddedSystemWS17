//Attribute/Merkmale/Beschreibungsvariablen eines Hauses
//eventuell noch möglich: class House implements SachenBeschreibung{
class House{
       //diese Variablen sind global für die Klasse
       //sie müssen in den hier definierten Klassen nicht extra
       //übergeben werden, sie sind allgemein bekannt
       //private: von außen kann niemand auf die Variablen zugreifen
       private int breite, laenge, zimmeranzahl;
       private String name = new String();
       //Konstruktor
       House(){
           wasfuereinHausbinich();
       }
       //an diese Methode kommt von außen niemand ran
       //auf sie kann nur innerhalb der Klasse zugegriffen werden
       private void wasfuereinHausbinich(){
              System.out.println("Bitte geben Sie ein, wie Ihr Haus heisst:");
              name=IOUtils.readString();
       }
       void input(){
          System.out.println("Bitte geben Sie folgende Daten von "+name+" ein:");
          System.out.print("Anzahl der Zimmer:");
          zimmeranzahl=IOUtils.readInt();
          System.out.print("Länge des Grunstücks:");
          laenge=IOUtils.readInt();
          System.out.print("Breite des Grunstücks:");
          breite=IOUtils.readInt();
       }
       int grundstuecksgroessenberechnung(){
          int groesse;
          groesse= laenge*breite;
          return groesse;
       }
       void output(){
             System.out.println("Das "+name+"-Haus hat "+zimmeranzahl+" Zimmer und ist "+grundstuecksgroessenberechnung()+" qm gross.");
       }
}