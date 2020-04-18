import org.junit.Test;
import static org.junit.Assert.*;

public class TestPlayer {

    private Player player = new Player(1, "ert");
    private ColorSquare property = new ColorSquare(null, null, 0, 0, 0,0,0,0,0,0, 0);

    @Test
    public void getID(){
        assertEquals(1, player.getID());
    }

    @Test
    public void getName(){
        assertEquals("ert", player.getName());
    }

    @Test
    public void setBankruptcy(){
        player.setBankruptcy(false);
        assertEquals(false, player.getBankruptcy());

        player.setBankruptcy(true);
        assertEquals(true, player.getBankruptcy());
    }

    @Test
    public void addProperty(){
        player.addProperty(property);
        PropertySquare properties[] = player.getProperties();
        for(int i = 0; i<40; i++){
            if (properties[i] == property){
                assertEquals(property, properties[i]);
                break;
            }
        }
    }


}
