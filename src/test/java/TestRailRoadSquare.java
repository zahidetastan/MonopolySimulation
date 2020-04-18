import org.junit.Test;
import static org.junit.Assert.*;

public class TestRailRoadSquare {

    private Player player = new Player();
    private Board board = new Board();
    private RailRoadSquare railRoad = new RailRoadSquare(null, "railroad", 0,0, 0);

    @Test
    public void setHasOwner(){
        railRoad.setHasOwner(false);
        assertEquals(false, railRoad.getHasOwner());
    }

    @Test
    public void getColor(){
        assertEquals("railroad", railRoad.getColor());
    }

    @Test
    public void getHasOwner(){
        assertEquals(false, railRoad.getHasOwner());
    }
}
