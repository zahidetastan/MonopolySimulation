// Goksel Tokur 150116049 - Merve Ayer 150119828 - Zahide Gur Tastan 150119827 - Ertugrul Sagdic 150116061
public class UtilitySquare extends PropertySquare{

    public UtilitySquare(String name, String color, int cost, int mortgage){
        super(name, color, cost, mortgage);
    }


    @Override
    public void doIt(Player player, Board board){
        // if( ) does not have an owner // 8 den buyuk mu degil mi buyukse al degilse devam et
        if(hasOwner == false){
            if(board.dice.getFace() > 8 && player.getMoney().getMoney() >= cost){
                player.getMoney().subMoney(cost);
                player.addProperty(this);
                owner = player;
                hasOwner = true;
                System.out.println(this.getName() +" is bought by " + player.getName());
            }else{
                // DO NOTHING
            }
        }else if(owner.getBankruptcy() == false){// pay the rental fee
            if(owner.howManyColorSeries(color) == 1){
                int face = player.toss(new Dice());
                int payAmount = face * 4;

                player.getMoney().subMoney(payAmount);
                owner.getMoney().addMoney(payAmount);
                System.out.println("@@@ "+player.getName()+" tossed "+ face);

                System.out.println("@@@"+player.getName()+" pay rent to "+owner.getName()+" $"+payAmount);
            }
            else if(owner.howManyColorSeries(color) == 2){
                int face = player.toss(new Dice());
                int payAmount = face * 10;

                player.getMoney().subMoney(payAmount);
                owner.getMoney().addMoney(payAmount);
                System.out.println("@@@ "+player.getName()+" tossed "+ face);
                System.out.println("@@@"+player.getName()+" pay rent to "+owner.getName()+" $"+payAmount);

            }

        }
        // else owner.addmoney player.submoney
    }

    @Override
    public void removeOwner(){
        owner = null;
    }

    @Override
    public void setHasOwner(Boolean boo){
        hasOwner = boo;
    }

    @Override
    public Player getOwner(){
        return owner;
    }

    @Override
    public String getColor(){
        return color;
    }

    @Override
    public boolean getHasOwner(){
        return hasOwner;
    }

}
