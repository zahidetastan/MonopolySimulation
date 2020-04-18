// Goksel Tokur 150116049 - Merve Ayer 150119828 - Zahide Gur Tastan 150119827 - Ertugrul Sagdic 150116061
public class Money {
    private int money;

    public Money(int money) {
        this.money = money;
    }

    // Add balance
    public void addMoney(int amount) {
        money += amount;
    }

    // Subtract balance
    public void subMoney(int amount) {
        money -= amount;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money){
        this.money = money;
    }
}