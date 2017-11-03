class Pool{
       private int hoehe;
       private int laenge;
       private int breite;
       private int wasser;
       private String name;
       Pool(String name){  //Konstruktor
          this.name=name;
       }
       void input(){
          System.out.println("Bitte geben Sie folgenden Daten Ihres Pools ein:");
          System.out.println("Höhe des Pools:");
          hoehe=IOUtils.readInt();
          System.out.println("Länge des Pools:");
          laenge=IOUtils.readInt();
          System.out.println("Breite des Pools:");
          breite=IOUtils.readInt();
       }
       int wassermengenberechnung(){
          int wasser;
          wasser= laenge*breite*hoehe;
          return wasser;
       }
       void output(){
             System.out.println("Der Pool beinhaltet "+wassermengenberechnung()+ " Wasser.");
       }
}