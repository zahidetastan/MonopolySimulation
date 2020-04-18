import org.junit.Test;
import static org.junit.Assert.*;

public class TestPiece {

    private Piece piece = new Piece();

    @Test
    public void setPosition(){
        piece.setPosition(5);
        assertEquals(5, piece.getCurrentPosition());
    }

}
