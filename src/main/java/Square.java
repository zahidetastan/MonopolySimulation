// Goksel Tokur 150116049 - Merve Ayer 150119828 - Zahide Gur Tastan 150119827 - Ertugrul Sagdic 150116061
public abstract class Square {
	private String name;

	public Square(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract void doIt(Player player, Board board);
}