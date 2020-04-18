import org.junit.Test;
import static org.junit.Assert.*;

public class TestChanceSquare {

    private ChanceSquare chanceSquare = new ChanceSquare(null,200);
    private Player player = new Player(1, "Player1");
    private Board board = new Board(8, 200, 1, 100, 10000, 1);

    @Test
    public void TestChanceCard0(){
        chanceSquare.ChanceCard0(player, board);
        assertEquals(10200, player.getMoney().getMoney());
    }

    @Test
    public void TestChanceCard1(){
        chanceSquare.ChanceCard1(player, board);
        Piece ourPiece = getPiece(player, board);
        assertEquals(24, ourPiece.getCurrentPosition());
    }

    @Test
    public void TestChanceCard2(){
        chanceSquare.ChanceCard2(player, board);
        Piece ourPiece = getPiece(player, board);
        assertEquals(11, ourPiece.getCurrentPosition());
    }

    @Test
    public void TestChanceCard3(){
        chanceSquare.ChanceCard3(player, board);
        Piece ourPiece = getPiece(player, board);
        if(Math.abs(12-ourPiece.getCurrentPosition())>Math.abs(28-ourPiece.getCurrentPosition())){
            assertEquals(28, ourPiece.getCurrentPosition());
        }
        else {
            assertEquals(12, ourPiece.getCurrentPosition());
        }
    }

    @Test
    public void TestChanceCard4(){
        chanceSquare.ChanceCard4(player, board);
        Piece ourPiece = getPiece(player, board);
        if(Math.abs(25-ourPiece.getCurrentPosition())>Math.abs(35-ourPiece.getCurrentPosition())){
            assertEquals(35, ourPiece.getCurrentPosition());
        }
        else {
            assertEquals(25, ourPiece.getCurrentPosition());
        }
    }

    @Test
    public void TestChanceCard5(){
        chanceSquare.ChanceCard5(player, board);
        assertEquals(10050, player.getMoney().getMoney());
    }

    @Test
    public void TestChanceCard6(){
        chanceSquare.ChanceCard6(player, board);
        assertEquals(1, player.getNumberOfGetOutOfJail());
    }

    @Test
    public void TestChanceCard7(){
        Piece ourPiece1 = getPiece(player, board);
        ourPiece1.setPosition(7);
        int position = ourPiece1.getCurrentPosition();
        chanceSquare.ChanceCard7(player, board);
        Piece ourPiece = getPiece(player, board);
        assertEquals(position-3, ourPiece.getCurrentPosition());
    }

    @Test
    public void TestChanceCard8(){
        chanceSquare.ChanceCard8(player, board);
        Piece ourPiece = getPiece(player, board);
        assertEquals(10, ourPiece.getCurrentPosition());
    }

    @Test
    public void TestChanceCard9(){
        chanceSquare.ChanceCard9(player, board);
        int numberOfHouses = 0;
        int numberOfHotels = 0;
        PropertySquare properties[] = player.getProperties();
        for(int i = 0; i < properties.length; i++){
            if(properties[i] instanceof  ColorSquare){
                if(((ColorSquare)properties[i]).getNumberOfHouses() < 5)
                    numberOfHouses = numberOfHouses + ((ColorSquare)properties[i]).getNumberOfHouses();
                else if(((ColorSquare)properties[i]).getNumberOfHouses() == 5);
                numberOfHotels++;
            }
        }
        assertEquals(10000 - (numberOfHouses * 25) - (numberOfHotels * 100),player.getMoney().getMoney());

    }

    @Test
    public void TestChanceCard10(){
        chanceSquare.ChanceCard10(player, board);
        assertEquals(9985, player.getMoney().getMoney());
    }

    @Test
    public void TestChanceCard11(){
        chanceSquare.ChanceCard11(player, board);
        Piece ourPiece = getPiece(player, board);
        System.out.println(ourPiece.getCurrentPosition());
        assertEquals(5, ourPiece.getCurrentPosition());
    }

    @Test
    public void TestChanceCard12(){
        chanceSquare.ChanceCard12(player, board);
        Piece ourPiece = getPiece(player, board);
        assertEquals(39, ourPiece.getCurrentPosition());
    }

    @Test
    public void TestChanceCard13(){
        chanceSquare.ChanceCard13(player, board);
        Piece[] pieces = board.getPieces();
        for(int i = 0; i<pieces.length; i++) {
            if (pieces[i].getID() == player.getID()) {
                assertEquals(10000+(50*(pieces.length - 1)), pieces[i].getMoney().getMoney());
            }
            else {
                assertEquals(9950, pieces[i].getMoney().getMoney());
            }
        }
    }

    @Test
    public void TestChanceCard14(){
        chanceSquare.ChanceCard14(player, board);
        Piece[] pieces = board.getPieces();
        for(int i = 0; i<pieces.length; i++) {
            if (pieces[i].getID() == player.getID()) {
                assertEquals(10000-(50*(pieces.length - 1)), pieces[i].getMoney().getMoney());
            }
            else {
                assertEquals(10050, pieces[i].getMoney().getMoney());
            }
        }
    }

    @Test
    public void TestChanceCard15(){
        chanceSquare.ChanceCard15(player, board);
        assertEquals(10150, player.getMoney().getMoney());
    }

    @Test
    public void TestChanceCard16(){
        chanceSquare.ChanceCard16(player, board);
        assertEquals(10100, player.getMoney().getMoney());
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
