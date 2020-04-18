// Goksel Tokur 150116049 - Merve Ayer 150119828 - Zahide Gur Tastan 150119827 - Ertugrul Sagdic 150116061
public class CommunityChestSquare extends Square {

    private int wage;

    public CommunityChestSquare(String name, int wage){
        super(name);
        this.wage = wage;
    }

    @Override
    public void doIt(Player player, Board board){
        int random = (int) (Math.random() * 17);

        switch(random){
            case 0 :
                CommunityChestCard0(player, board);
                break;
            case 1 :
                CommunityChestCard1(player, board);
                break;
            case 2 :
                CommunityChestCard2(player, board);
                break;
            case 3 :
                CommunityChestCard3(player, board);
                break;
            case 4 :
                CommunityChestCard4(player, board);
                break;
            case 5 :
                CommunityChestCard5(player, board);
                break;
            case 6 :
                CommunityChestCard6(player, board);
                break;
            case 7 :
                CommunityChestCard7(player, board);
                break;
            case 8 :
                CommunityChestCard8(player, board);
                break;
            case 9 :
                CommunityChestCard9(player, board);
                break;
            case 10 :
                CommunityChestCard10(player, board);
                break;
            case 11 :
                CommunityChestCard11(player, board);
                break;
            case 12 :
                CommunityChestCard12(player, board);
                break;
            case 13 :
                CommunityChestCard13(player, board);
                break;
            case 14 :
                CommunityChestCard14(player, board);
                break;
            case 15 :
                CommunityChestCard15(player, board);
                break;
            case 16 :
                CommunityChestCard16(player, board);
                break;
        }
    }

    //Advance to go (Collect $200)
    public void CommunityChestCard0(Player player, Board board){
        player.getMoney().addMoney(wage);
        System.out.println("Advance to go - collect $" + wage);
    }

    public void CommunityChestCard1(Player player, Board board){
        player.getMoney().addMoney(200);
        System.out.println("Bank error in your favor - Collect $200");
    }

    public void CommunityChestCard2(Player player, Board board){
        player.getMoney().addMoney(100);
        System.out.println("You inherit $100");
    }

    public void CommunityChestCard3(Player player, Board board){
        player.getMoney().addMoney(50);
        System.out.println("From sale of stock you get $50");
    }

    public void CommunityChestCard4(Player player, Board board){
        player.getMoney().subMoney(50);
        System.out.println("Doctor's fee - Pay $50");
    }
    //Get out of jail free
    public void CommunityChestCard5(Player player, Board board){
        System.out.println("Get Out of Jail Free Card");
        int numberOfJailCard = player.getNumberOfGetOutOfJail();
        player.setNumberOfGetOutOfJail(++numberOfJailCard);
    }

    public void CommunityChestCard6(Player player, Board board){
        Piece ourPiece = getPiece(player,board);
        //30 is GoToJailSquare
        ourPiece.setPosition(30);
        board.getSquares()[30].doIt(player, board);
        System.out.println("Go to Jail – Go directly to jail – Do not pass Go – Do not collect $200");
    }

    public void CommunityChestCard7(Player player, Board board){
        Piece[] pieces = board.getPieces();
        for(int i = 0; i<pieces.length; i++) {
            if (pieces[i].getID() == player.getID()) {
                pieces[i].getMoney().addMoney(50*(pieces.length - 1));
            }
            else{
                pieces[i].getMoney().subMoney(50);
            }
        }
        System.out.println("Grand Opera Night—Collect $50 from every player for opening night seats");
    }

    public void CommunityChestCard8(Player player, Board board){
        player.getMoney().addMoney(100);
        System.out.println("Holiday Fund matures — Receive $100");
    }

    public void CommunityChestCard9(Player player, Board board){
        player.getMoney().addMoney(20);
        System.out.println("Income tax refund–Collect $20");
    }

    public void CommunityChestCard10(Player player, Board board){
        player.getMoney().addMoney(10);
        System.out.println("It is your birthday—Collect $10");
    }

    public void CommunityChestCard11(Player player, Board board){
        player.getMoney().addMoney(100);
        System.out.println("Life insurance matures–Collect $100");
    }

    public void CommunityChestCard12(Player player, Board board){
        player.getMoney().subMoney(100);
        System.out.println("Pay hospital fees of $100");
    }

    public void CommunityChestCard13(Player player, Board board){
        player.getMoney().subMoney(150);
        System.out.println("Pay school fees of $150");
    }

    public void CommunityChestCard14(Player player, Board board){
        player.getMoney().addMoney(25);
        System.out.println("Receive $25 consultancy fee");
    }
    //You are assessed for street repairs–$40 per house–$115 per hotel
    public void CommunityChestCard15(Player player, Board board){
        int numberOfHouses = 0;
        int numberOfHotels = 0;
        PropertySquare properties[] = player.getProperties();
        for(int i = 0; i < properties.length; i++){
            if(properties[i] instanceof  ColorSquare){
                if(((ColorSquare)properties[i]).getNumberOfHouses() < 5){
                    numberOfHouses = numberOfHouses + ((ColorSquare)properties[i]).getNumberOfHouses();
                }
                if(((ColorSquare)properties[i]).getNumberOfHouses() == 5){
                    numberOfHotels++;
                }
            }
        }
        System.out.println(player.getMoney().getMoney());
        System.out.println("hotels" + numberOfHotels);
        System.out.println("houses" + numberOfHouses);
        player.getMoney().subMoney(numberOfHouses * 40);
        player.getMoney().subMoney(numberOfHotels * 115);

        System.out.println("You are assessed for street repairs–$40 per house–$115 per hotel");
        System.out.println(player.getMoney().getMoney());
    }

    public void CommunityChestCard16(Player player, Board board){
        player.getMoney().addMoney(10);
        System.out.println("You have won second prize in a beauty contest–Collect $10");
    }

    public Piece getPiece(Player player, Board board){
        Piece[] pieces = board.getPieces();
        Piece ourPiece = new Piece();
        for(int i = 0; i<pieces.length; i++){
            if(pieces[i].getID() == player.getID()){
                ourPiece = pieces[i];
            }
        }
        return ourPiece;
    }

}
