// Goksel Tokur 150116049 - Merve Ayer 150119828 - Zahide Gur Tastan 150119827 - Ertugrul Sagdic 150116061
public class ChanceSquare extends Square {

    private int wage;

    public ChanceSquare(String name, int wage) {
        super(name);
        this.wage = wage;
    }

    @Override
    public void doIt(Player player, Board board) {
        int random = (int) (Math.random() * 17);


        switch(random){
            case 0 :
                ChanceCard0(player, board);
                break;
            case 1 :
                ChanceCard1(player, board);
                break;
            case 2 :
                ChanceCard2(player, board);
                break;
            case 3 :
                ChanceCard3(player, board);
                break;
            case 4 :
                ChanceCard4(player, board);
                break;
            case 5 :
                ChanceCard5(player, board);
                break;
            case 6 :
                ChanceCard6(player, board);
                break;
            case 7 :
                ChanceCard7(player, board);
                break;
            case 8 :
                ChanceCard8(player, board);
                break;
            case 9 :
                ChanceCard9(player, board);
                break;
            case 10 :
                ChanceCard10(player, board);
                break;
            case 11 :
                ChanceCard11(player, board);
                break;
            case 12 :
                ChanceCard12(player, board);
                break;
            case 13 :
                ChanceCard13(player, board);
                break;
            case 14 :
                ChanceCard14(player, board);
                break;
            case 15 :
                ChanceCard15(player, board);
                break;
            case 16 :
                ChanceCard16(player, board);
                break;
        }

    }

    //advance to go
    public void ChanceCard0(Player player, Board board){
        player.getMoney().addMoney(wage);
        System.out.println("Advance to go - collect $" + wage);
        getPiece(player, board).setPosition(0);
    }

    public void ChanceCard1(Player player, Board board){
        int currentPosition = getPiece(player, board).getCurrentPosition();
        if(currentPosition>24){
            player.getMoney().addMoney(wage);
        }
        getPiece(player, board).setPosition(24);
        board.getSquares()[24].doIt(player, board);


        System.out.println("Advance to Trafalgar Square -If you pass Go collect $" + wage);
    }

    public void ChanceCard2(Player player, Board board){
        int currentPosition = getPiece(player, board).getCurrentPosition();
        if(currentPosition>11){
            player.getMoney().addMoney(wage);
        }
        getPiece(player, board).setPosition(11);
        board.getSquares()[11].doIt(player, board);

        System.out.println("Advance to Pall Mall Square -If you pass Go collect $" + wage);
    }

    public void ChanceCard3(Player player, Board board){
        int currentPosition = getPiece(player, board).getCurrentPosition();
        if(Math.abs(12-currentPosition)>Math.abs(28-currentPosition)){
            getPiece(player, board).setPosition(28);
            board.getSquares()[28].doIt(player, board);
        }
        else {
            getPiece(player, board).setPosition(12);
            board.getSquares()[12].doIt(player, board);
        }

        System.out.println("Advance token to nearest Utility. -If unowned, you may buy it from the Bank.");
    }

    public void ChanceCard4(Player player, Board board){
        int currentPosition = getPiece(player, board).getCurrentPosition();
        if(Math.abs(25-currentPosition)>Math.abs(35-currentPosition)){
            getPiece(player, board).setPosition(35);
            board.getSquares()[35].doIt(player, board);
        }
        else {
            getPiece(player, board).setPosition(25);
            board.getSquares()[25].doIt(player, board);
        }

        System.out.println("Advance token to nearest RailRoad. -If unowned, you may buy it from the Bank.");
    }

    public void ChanceCard5(Player player, Board board){
        player.getMoney().addMoney(50);
        System.out.println("Bank pays you dividend –Collect $50");
    }

    //Get out of jail free
    public void ChanceCard6(Player player, Board board){
        System.out.println("Get Out of Jail Free Card");
        int numberOfJailCard = player.getNumberOfGetOutOfJail();
        player.setNumberOfGetOutOfJail(++numberOfJailCard);
    }

    public void ChanceCard7(Player player, Board board){
        int currentPosition = getPiece(player, board).getCurrentPosition();
        getPiece(player, board).setPosition(currentPosition-3);
        board.getSquares()[currentPosition-3].doIt(player, board);
        System.out.println("Go Back 3 Squares");

    }

    public void ChanceCard8(Player player, Board board){
        Piece ourPiece = getPiece(player,board);
        //30 is GoToJailSquare
        ourPiece.setPosition(30);
        board.getSquares()[30].doIt(player, board);
        System.out.println("Go to Jail – Go directly to jail – Do not pass Go – Do not collect $200");
    }

    public void ChanceCard9(Player player, Board board){
        int numberOfHouses = 0;
        int numberOfHotels = 0;
        PropertySquare properties[] = player.getProperties();
        for(int i = 0; i < properties.length; i++){
            if(properties[i] instanceof  ColorSquare){
                if(((ColorSquare)properties[i]).getNumberOfHouses() < 5)
                    numberOfHouses = numberOfHouses + ((ColorSquare)properties[i]).getNumberOfHouses();
                else if(((ColorSquare)properties[i]).getNumberOfHouses() == 5)
                    numberOfHotels++;
            }
        }
        player.getMoney().subMoney(numberOfHouses * 25);
        player.getMoney().subMoney(numberOfHotels * 100);
        System.out.println("Make general repairs on all your property–For each house pay $25–For each hotel $100");
    }

    public void ChanceCard10(Player player, Board board){
        player.getMoney().subMoney(15);
        System.out.println("Pay poor tax –Pay $50");
    }

    public void ChanceCard11(Player player, Board board){
        int currentPosition = getPiece(player, board).getCurrentPosition();
        if(currentPosition>5){
            player.getMoney().addMoney(wage);
        }
        getPiece(player, board).setPosition(5);
        board.getSquares()[5].doIt(player, board);

        System.out.println("Take a trip to Marlybone Station  Railroad–If you pass Go, collect $" + wage);
    }

    public void ChanceCard12(Player player, Board board){

        getPiece(player, board).setPosition(39);
        board.getSquares()[39].doIt(player, board);

        System.out.println("Take a walk on the Mayfair–Advance token to Mayfair");
    }

    public void ChanceCard13(Player player, Board board){
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

    public void ChanceCard14(Player player, Board board){
        Piece[] pieces = board.getPieces();
        Piece ourPiece = new Piece();
        for(int i = 0; i<pieces.length; i++) {
            if (pieces[i].getID() == player.getID()) {
                pieces[i].getMoney().subMoney(50*(pieces.length-1));
            }
            else{
                pieces[i].getMoney().addMoney(50);
            }
        }
        System.out.println("You have been elected Chairman of the Board–Pay each player $50");
    }

    public void ChanceCard15(Player player, Board board){
         player.getMoney().addMoney(150);

        System.out.println("Your building and loan matures—Collect $150");
    }

    public void ChanceCard16(Player player, Board board){
        player.getMoney().addMoney(100);

        System.out.println("You have won a crossword competition—Collect $100");
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
