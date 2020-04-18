// Goksel Tokur 150116049 - Merve Ayer 150119828 - Zahide Gur Tastan 150119827 - Ertugrul Sagdic 150116061
public class RailRoadSquare extends PropertySquare{
    private int rent;

    public RailRoadSquare(String name, String color, int cost, int rent, int mortgage){
        super(name, color, cost, mortgage);
        this.rent = rent;
    }


    @Override
    public void doIt(Player player, Board board){
        String nameArr[] = player.getName().split(" ");
        String nameOfPlayer = nameArr[0];

        // if( ) does not have an owner
        if(hasOwner == false){
            if(board.dice.getFace() > 8 && player.getMoney().getMoney() >= cost){
                player.getMoney().subMoney(cost);
                player.addProperty(this);
                owner = player;
                hasOwner = true;
                System.out.println(this.getName() +" is bought by " + nameOfPlayer);
            }else{
                // DO NOTHING
            }
        }else if (owner.getBankruptcy() == false){// pay the rental fee
            if(owner.howManyColorSeries(color) == 1){
                player.getMoney().subMoney(rent);
                owner.getMoney().addMoney(rent);
                System.out.println("@@@"+nameOfPlayer+" pay rent to "+owner.getName()+" $"+rent);
            }
            else if(owner.howManyColorSeries(color) == 2){
                player.getMoney().subMoney(rent * 2);
                owner.getMoney().addMoney(rent * 2);
                System.out.println("@@@"+nameOfPlayer+" pay rent to "+owner.getName()+" $"+rent*2);
            }
            else if(owner.howManyColorSeries(color) == 3){
                player.getMoney().subMoney(rent * 3);
                owner.getMoney().addMoney(rent * 3);
                System.out.println("@@@"+nameOfPlayer+" pay rent to "+owner.getName()+" $"+rent*3);
            }
            else if(owner.howManyColorSeries(color) == 4){
                player.getMoney().subMoney(rent * 4);
                owner.getMoney().addMoney(rent * 4);
                System.out.println("@@@"+nameOfPlayer+" pay rent to "+owner.getName()+" $"+rent*4);
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
