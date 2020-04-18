// Goksel Tokur 150116049 - Merve Ayer 150119828 - Zahide Gur Tastan 150119827 - Ertugrul Sagdic 150116061
public class Dice {

    private int face1;
    private int face2;
    private boolean duplicate = false;

    public void roll(){
        // Generates random number between 0-1 as double.
        // To generate random numbers as in dice between 1-6, we need to multiply it by
        // 6, and add 1.
        this.face1 = (int) (Math.random() * 6) + 1;
        this.face2 = (int) (Math.random() * 6) + 1;
        if(face1 == face2){
            duplicate = true;
        }else{
            duplicate = false;
        }
    }
    // Returns the generated random integer.
    public int getFace() {
        roll();
        return face1 + face2;
    }

    public boolean getDuplicate(){
        return duplicate;
    }


}