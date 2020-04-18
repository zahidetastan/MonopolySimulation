// Goksel Tokur 150116049 - Merve Ayer 150119828 - Zahide Gur Tastan 150119827 - Ertugrul Sagdic 150116061
public class Piece extends Player{

	private int position = 0;
	private String name;

	public Piece(){

	}

	// Player's piece
	public Piece(int id, String name){
		//Calls super class' constructor
		super(id, name);
		//assigns the value of name to this class' variable
		this.name = name + " piece";
    }

	//sets the position of piece
	public void setPosition(int p){
        this.position = p;
    }

	//get function of position of piece
	 public int getCurrentPosition(){
	        return position;
	}

	//get function of variable name
	public String getName(){
		return name;
	}

}