import org.junit.Test;
import static org.junit.Assert.*;

public class TestUtilitySquare {

    private UtilitySquare utility = new UtilitySquare(null, "utility", 0, 0);

    @Test
    public void setHasOwner(){
        utility.setHasOwner(false);
        assertEquals(false, utility.getHasOwner());
    }

    @Test
    public void getColor(){
        assertEquals("utility", utility.getColor());
    }

    @Test
    public void getHasOwner(){
        assertEquals(false, utility.getHasOwner());
    }
}
