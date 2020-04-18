// Goksel Tokur 150116049 - Merve Ayer 150119828 - Zahide Gur Tastan 150119827 - Ertugrul Sagdic 150116061
import java.awt.*;

public class Player{
    private int id;
    private String name;
    private boolean bankruptcy = false;
    private boolean suspend = false;
    private int suspendCounter = 0;
    private int numberOfGetOutOfJail = 0;

    //private ColorSquare properties[] = new ColorSquare[40];
    //private UtilitySquare utilities[] = new UtilitySquare[40];
    //private RailRoadSquare railroads[] = new RailRoadSquare[40];

    private PropertySquare properties[] = new PropertySquare[40];

    int startMoneyAmount = 10000;
    Money money = new Money(startMoneyAmount);

    public Player(){
    }

    public Player(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Money getMoney(){
        return money;
    }

    public int getID(){
    	return id;
    }

    public int toss(Dice dice){
        return dice.getFace();
    }

    public String getName(){
        return name;
    }

    public void setBankruptcy(boolean boo){
        this.bankruptcy = boo;
        System.out.println(name + " bankrupt");
        for(int i = 0; i<40 ; i++){
            if (properties[i] != null) {
                System.out.println(properties[i].getName() + " is confiscated by bank");
                removeProperty(properties[i].getName());
            }
        }
    }

    public boolean getBankruptcy(){
        return bankruptcy;
    }

    public boolean isSuspend(){
        return suspend;
    }

    public void setSuspend(boolean suspend){
        this.suspend = suspend;
    }

    public int getSuspendCounter(){
        return suspendCounter;
    }

    public void setSuspendCounter(int suspendCounter){
        this.suspendCounter = suspendCounter;
    }

    public int getNumberOfGetOutOfJail() { return numberOfGetOutOfJail;  }

    public void setNumberOfGetOutOfJail(int numberOfGetOutOfJail) { this.numberOfGetOutOfJail = numberOfGetOutOfJail; }



    public PropertySquare[] getProperties(){
        return properties;
    }

    // Add property to the player's properties array
    public void addProperty(PropertySquare property){
        for(int i = 0; i<40; i++){
            if (properties[i] == null){
                properties[i] = property;
                break;
            }
        }
    }

    // Function to remove the element
    public void removeProperty (String nameOfProperty) {
        for(int i = 0; i<40; i++){
            if( properties[i] != null && (properties[i].getName()).equals(nameOfProperty)){
                if( properties[i] instanceof ColorSquare ){
                    ((ColorSquare)properties[i]).setNumberOfHouses(0);
                }
                properties[i].removeOwner();
                properties[i].setHasOwner(false);
                properties[i] = null;

                break;
            }
        }
    }

    public int howManyColorSeries(String color){
        int count = 0;
        for(int i = 0; i < properties.length; i++){
            if (properties[i] != null && (properties[i].getColor()).equals(color)) {
                count++;
            }
        }
        return count;
    }


}