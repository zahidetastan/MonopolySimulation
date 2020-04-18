// Goksel Tokur 150116049 - Merve Ayer 150119828 - Zahide Gur Tastan 150119827 - Ertugrul Sagdic 150116061
public abstract class PropertySquare extends Square{

    protected int cost;
    protected String color;
    protected int mortgage;

    protected Player owner = null;
    protected boolean hasOwner = false;

    public PropertySquare(String name, String color, int cost, int mortgage) {
        super(name);
        this.color = color;
        this.cost = cost;
        this.mortgage = mortgage;
    }

    public abstract void doIt(Player player, Board board);

    public void removeOwner(){
        owner = null;
    }

    public void setHasOwner(Boolean boo){
        hasOwner = boo;
    }

    public Player getOwner(){
        return owner;
    }

    public String getColor(){
        return color;
    }

    public boolean getHasOwner(){
        return hasOwner;
    }

    public int getMortgage(){
        return mortgage;
    }
}
