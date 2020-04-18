// Goksel Tokur 150116049 - Merve Ayer 150119828 - Zahide Gur Tastan 150119827 - Ertugrul Sagdic 150116061
public class GoToJailSquare extends Square{
     
    public GoToJailSquare(String name) {
		  super(name);
    }
    
    //WARNING this doit function get piece instead of player as reference
    @Override
    public void doIt(Player player, Board board){
        Piece[] pieces = board.getPieces();
        Piece ourPiece = new Piece();
        for(int i = 0; i<pieces.length; i++){
            if(pieces[i].getID() == player.getID()){
                ourPiece = pieces[i];
            }
        }
        int numberOfGetOutJail = player.getNumberOfGetOutOfJail();

        if(numberOfGetOutJail>0){
            player.setNumberOfGetOutOfJail(--numberOfGetOutJail);
            System.out.println(player.getName() + "used to Get Out Of Jail Free Card!");
        } else {
            ourPiece.setSuspend(true);
            ourPiece.setSuspendCounter(3);
            ourPiece.setPosition(10);
        }
    }
}