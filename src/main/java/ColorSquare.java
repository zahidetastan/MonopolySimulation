// Goksel Tokur 150116049 - Merve Ayer 150119828 - Zahide Gur Tastan 150119827 - Ertugrul Sagdic 150116061
public class ColorSquare extends PropertySquare{
    private int rent;
    private int rent1;
    private int rent2;
    private int rent3;
    private int rent4;
    private int hotel;
    private int pricePerHouse;
    private int numberOfHouses = 0;


    public ColorSquare(String name, String color, int cost, int rent, int rent1, int rent2, int rent3, int rent4, int hotel, int pricePerHouse, int mortgage){
        super(name, color, cost, mortgage);
        this.rent = rent;
        this.rent1 = rent1;
        this.rent2 = rent2;
        this.rent3 = rent3;
        this.rent4 = rent4;
        this.hotel = hotel;
        this.pricePerHouse = pricePerHouse;
    }

    @Override
    public void doIt(Player player, Board board){
        // if( ) does not have an owner
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
        }else if (owner.getBankruptcy() == false){// pay the rental fee
            boolean colorSeries = false;
            if(color.equals("darkblue") || color.equals("brown")){
                if(owner.howManyColorSeries(color) == 2 )
                    colorSeries = true;
            }else{
                if(owner.howManyColorSeries(color) == 3)
                    colorSeries = true;
            }

            // Buy house function;
            if(player.getName().equals(owner.getName()) && colorSeries && player.getMoney().getMoney() > pricePerHouse && numberOfHouses < 5 ){
                houseBuyingRecursion(player, board);
            }

            if(!player.getName().equals(owner.getName())) {
                int payAmount = rent;
                if (!colorSeries && numberOfHouses == 0) {

                }else if(colorSeries && numberOfHouses == 0){
                    payAmount = payAmount*2;
                }else if(colorSeries && numberOfHouses == 1){
                    payAmount = rent1;
                }else if(colorSeries && numberOfHouses == 2){
                    payAmount = rent2;
                }else if(colorSeries && numberOfHouses == 3){
                    payAmount = rent3;
                }else if(colorSeries && numberOfHouses == 4){
                    payAmount = rent4;
                }else if(colorSeries && numberOfHouses == 5){
                    payAmount = hotel;
                }

                System.out.println("@@@"+player.getName() + " will pay rent to " + owner.getName()+" $"+payAmount);
                player.getMoney().subMoney(payAmount);
                owner.getMoney().addMoney(payAmount);
            }
            /*
            int payAmount = rent;
            if(colorSeries)
                payAmount = payAmount*2;

            System.out.println("@@@"+player.getName() + " will pay rent to " + owner.getName()+" $"+payAmount);
            player.getMoney().subMoney(payAmount);
            owner.getMoney().addMoney(payAmount);
            */
        }
        // else owner.addmoney player.submoney
    }

    public int getNumberOfHouses() {
        return numberOfHouses;
    }

    public void setNumberOfHouses(int numberOfHouses) {
        this.numberOfHouses = numberOfHouses;
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

    public void houseBuyingRecursion(Player player, Board board){
        int i = 5 - numberOfHouses;
        while(player.getMoney().getMoney() > pricePerHouse) {
            if (board.dice.getFace() > 8) {
                player.getMoney().subMoney(pricePerHouse);
                numberOfHouses++;
                String nameArr[] = player.getName().split(" ");
                String nameOfPlayer = nameArr[0];
                System.out.println(nameOfPlayer + " bought a " + ((numberOfHouses == 5) ? "hotel" : "house") + " for $" + pricePerHouse);
            }
            i--;
            if(numberOfHouses == 5 || i == 0)
                break;
        }
    }
}
