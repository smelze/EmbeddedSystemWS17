class MeinAnwesen{
   public static void main(String[] args){
      House haupthaus = new House();
      House gartenhaus = new House();
      Pool schwimmpool = new Pool("Swimmingpool");
      Whirlpool whirlpool = new Whirlpool("Luxuswhirlpool");
      haupthaus.input();
      haupthaus.output();
      gartenhaus.input();
      gartenhaus.output();
      schwimmpool.input();
      schwimmpool.output();
      whirlpool.input();
      whirlpool.output();
   }
}
