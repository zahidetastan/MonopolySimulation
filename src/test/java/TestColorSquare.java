import org.junit.Test;
import static org.junit.Assert.*;

public class TestColorSquare {

    private ColorSquare property = new ColorSquare(null, "utility", 0,0, 0, 0, 0, 0, 0, 0, 0);

    @Test
    public void setHasOwner(){
        property.setHasOwner(false);
        assertEquals(false, property.getHasOwner());
    }

    @Test
    public void getColor(){
        assertEquals("utility", property.getColor());
    }

    @Test
    public void getHasOwner(){
        assertEquals(false, property.getHasOwner());
    }
}
