import org.junit.Test;
import static org.junit.Assert.*;

public class TestMoney {

    private Money money = new Money(0);

    @Test
    public void addMoney() {
        money.addMoney(200);
        assertEquals(200, money.getMoney());
    }

    @Test
    public void subMoney(){
        money.subMoney(200);
        assertEquals(-200, money.getMoney());
    }

}
