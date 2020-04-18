import org.junit.Test;
import static org.junit.Assert.*;

public class TestDice {

        private Dice dice = new Dice();


        @Test
        public void roll(){
            int value = dice.getFace();
            assertTrue("The value should be be", value >= 2 && value <= 12);
        }

}
