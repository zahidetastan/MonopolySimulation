import org.junit.Test;
import static org.junit.Assert.*;

public class TestCommunityChestSquare {

    private CommunityChestSquare communityChestSquare = new CommunityChestSquare(null,200);
    private Player player = new Player(1, null);
    private Board board = new Board(8, 200, 1, 100, 10000, 1);

    @Test
    public void TestCommunityChestCard0(){
        communityChestSquare.CommunityChestCard0(player, board);
        assertEquals(10200, player.getMoney().getMoney());
    }

    @Test
    public void TestCommunityChestCard1(){
        communityChestSquare.CommunityChestCard1(player, board);
        assertEquals(10200, player.getMoney().getMoney());
    }

    @Test
    public void TestCommunityChestCard2(){
        communityChestSquare.CommunityChestCard2(player, board);
        assertEquals(10100, player.getMoney().getMoney());
    }

    @Test
    public void TestCommunityChestCard3(){
        communityChestSquare.CommunityChestCard3(player, board);
        assertEquals(10050, player.getMoney().getMoney());
    }

    @Test
    public void TestCommunityChestCard4(){
        communityChestSquare.CommunityChestCard4(player, board);
        assertEquals(9950, player.getMoney().getMoney());
    }

    @Test
    public void TestCommunityChestCard5(){
        communityChestSquare.CommunityChestCard5(player, board);
        assertEquals(1, player.getNumberOfGetOutOfJail());
    }

    @Test
    public void TestCommunityChestCard6(){
        communityChestSquare.CommunityChestCard6(player, board);
        Piece ourPiece = getPiece(player, board);
        assertEquals(10, ourPiece.getCurrentPosition());
    }

    @Test
    public void TestCommunityChestCard7(){
        communityChestSquare.CommunityChestCard7(player, board);
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
    public void TestCommunityChestCard8(){
        communityChestSquare.CommunityChestCard8(player, board);
        assertEquals(10100, player.getMoney().getMoney());
    }

    @Test
    public void TestCommunityChestCard9(){
        communityChestSquare.CommunityChestCard9(player, board);
        assertEquals(10020, player.getMoney().getMoney());
    }

    @Test
    public void TestCommunityChestCard10(){
        communityChestSquare.CommunityChestCard10(player, board);
        assertEquals(10010, player.getMoney().getMoney());
    }

    @Test
    public void TestCommunityChestCard11(){
        communityChestSquare.CommunityChestCard11(player, board);
        assertEquals(10100, player.getMoney().getMoney());
    }

    @Test
    public void TestCommunityChestCard12(){
        communityChestSquare.CommunityChestCard12(player, board);
        assertEquals(9900, player.getMoney().getMoney());
    }

    @Test
    public void TestCommunityChestCard13(){
        communityChestSquare.CommunityChestCard13(player, board);
        assertEquals(9850, player.getMoney().getMoney());
    }

    @Test
    public void TestCommunityChestCard14(){
        communityChestSquare.CommunityChestCard14(player, board);
        assertEquals(10025, player.getMoney().getMoney());
    }

    @Test
    public void TestCommunityChestCard15(){
        communityChestSquare.CommunityChestCard15(player, board);
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
        assertEquals(10000 - (numberOfHouses * 40) - (numberOfHotels * 115),player.getMoney().getMoney());
    }

    @Test
    public void TestCommunityChestCard16(){
        communityChestSquare.CommunityChestCard16(player, board);
        assertEquals(10010, player.getMoney().getMoney());
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
