import org.junit.Test;
import static org.junit.Assert.*;

public class TestTaxSquare {

    TaxSquare taxSquare = new TaxSquare("Tax", 10);
    Player player = new Player();
    Board board = new Board();

    @Test
    public void doIt(){
        taxSquare.doIt(player, board);
        assertEquals(9000, player.getMoney().getMoney());
    }
}
