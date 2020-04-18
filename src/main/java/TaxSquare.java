// Goksel Tokur 150116049 - Merve Ayer 150119828 - Zahide Gur Tastan 150119827 - Ertugrul Sagdic 150116061
public class TaxSquare extends Square {
     private int taxAmount = 0;

     public TaxSquare(String name, int taxAmount) {
          super(name);
          this.taxAmount = taxAmount;

     }

     @Override
     public void doIt(Player player, Board board) {
          // Income tax ((percent))
          player.getMoney().subMoney((player.getMoney().getMoney()*taxAmount)/100);
          System.out.println(player.getName() + " paid $" +  (player.getMoney().getMoney()*taxAmount)/100 + " for income tax.");
     }
}