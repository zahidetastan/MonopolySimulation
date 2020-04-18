import org.junit.Test;
import static org.junit.Assert.*;

public class TestGoToJailSquare {

    private Player player = new Player(1, null);
    private Board board = new Board(8, 0, 0, 0, 0, 0);

    @Test
    public void doIt(){
        Piece[] pieces = board.getPieces();
        Piece ourPiece = new Piece(1, null);
        for(int i = 0; i<pieces.length; i++){
            if(pieces[i].getID() == player.getID()){
                ourPiece = pieces[i];
            }
        }
        ourPiece.setSuspend(true);
        assertEquals(true, ourPiece.isSuspend());
        ourPiece.setSuspendCounter(3);
        assertEquals(3, ourPiece.getSuspendCounter());
        ourPiece.setPosition(10);
        assertEquals(10, ourPiece.getCurrentPosition());
    }
}
