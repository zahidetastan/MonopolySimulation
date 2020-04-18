// Goksel Tokur 150116049 - Merve Ayer 150119828 - Zahide Gur Tastan 150119827 - Ertugrul Sagdic 150116061
import java.util.Scanner;
import java.io.*;

import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class Game{
    private static final Logger logger = LogManager.getLogger(Game.class); // log4j logger
    Board board;

    public Game(){
        int[] configArr = new int[6];
        int numberOfPlayer = 0;
        int wage = 0;
        int taxSquareNumber = 0;
        int taxSquareMoney = 0;
        int startMoney=0;
        int numberOfGoToJailSquare = 0;

        BasicConfigurator.configure();


        try {
            FileInputStream fstream = new FileInputStream("config.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            int i = 0; 
            while ((strLine = br.readLine()) != null) {
                String[] tokens = strLine.split(" ");
                configArr[i] = Integer.valueOf(tokens[0]);
                i++;
            }
            numberOfPlayer = configArr[0];
            wage = configArr[1];
            taxSquareNumber = configArr[2];
            taxSquareMoney = configArr[3];
            startMoney = configArr[4];
            numberOfGoToJailSquare = configArr[5];

            in.close();
        } catch (Exception e) {
            //System.err.println("Error: " + e.getMessage());
            logger.error("Config txt error", e);

        }

        System.out.println("Game will start with " + numberOfPlayer +" players");
        try{
            Object obj = new JSONParser().parse(new FileReader("properties.json") );
            JSONObject jo = (JSONObject) obj;
            JSONArray ja = (JSONArray) jo.get("squares");
            JSONObject vine = (JSONObject) ja.get(0);
            //System.out.println(vine.get("name"));
        }catch (Exception e){
           // System.err.println("Error(JSON): " + e.getMessage());
            logger.error("JSON File error", e);
        }


        board = new Board(numberOfPlayer, wage, taxSquareNumber, taxSquareMoney, startMoney, numberOfGoToJailSquare);
    }
    
    public static void main(String[] args){        
        Game game = new Game();
        game.playGame();
    }


    public void playGame(){
        Dice dice = new Dice();
        int duplicateCounter = 0;

        while (!board.hasWinner()) {


            Piece player = board.whichPieceTurn();
            String nameArr[] = player.getName().split(" ");
            String nameOfPlayer = nameArr[0];


            //Scanner input1 = new Scanner (System.in);
            //int asd = input1.nextInt();
        
            if (player.getMoney().getMoney() >= 0 && !player.isSuspend()) {
                int face = player.toss(dice);
                boolean duplicate = dice.getDuplicate(); // dices duplicate control

                if(duplicate){
                    duplicateCounter++;
                    System.out.println(nameOfPlayer + " tossed double so will take one more turn.");
                }
                //WARNING goToJail squares position attention
                if(duplicateCounter==3){
                    System.out.println(nameOfPlayer+" tossed double 3 times and will go to jail.");
                    player.setSuspend(true);
                    player.setSuspendCounter(3);
                    player.setPosition(10);

                }else{
                    board.move(player, face);
                    if(player.getMoney().getMoney() < 0){ // MORTGAGE FUNCTION
                        for(int i = 0; i<40; i++){
                            if(((player.getProperties())[i]) != null ) {
                                //if ((player.getProperties())[i].getMortgage() >= Math.abs(player.getMoney().getMoney())) {

                                    System.out.println(nameOfPlayer + " changed "+ ((player.getProperties())[i]).getName() + " for mortgage.");
                                    player.getMoney().addMoney(((player.getProperties())[i]).getMortgage());
                                    player.removeProperty((player.getProperties())[i].getName());
                                    if(player.getMoney().getMoney() >= 0)
                                        break;
                                //}
                            }
                        }
                    }
                    System.out.println("Current Turn is: " + board.getCurrentTurn() + " Cycle Counter is:" + board.getCycle() + " " +
                            player.getName() + "'s Position: " + player.getCurrentPosition()
                            + " Balance: " + player.getMoney().getMoney() + "\n");
                    if(duplicate)
                        continue;// if current player toss duplicate, play again
                }

                if(player.getMoney().getMoney() < 0)
                    player.setBankruptcy(true);
            }else if(player.getMoney().getMoney() >= 0 && player.isSuspend()){// Player at the jail
                int face = player.toss(dice);
                boolean duplicate = dice.getDuplicate();

                //if suspended player toss double, set suspend counter to 0. Player will take turn next turn.
                if(duplicate){
                    System.out.println(nameOfPlayer + " tossed double, he/she will get out of jail next turn.\n");
                    player.setSuspend(false);
                    player.setSuspendCounter(0);
                }else{ // else decrease suspend counter, if it is zero suspend will be false
                    player.setSuspendCounter(player.getSuspendCounter()-1);
                    if(player.getSuspendCounter() == 0){
                        System.out.println(nameOfPlayer + " will get out of jail next turn.\n");
                        player.setSuspend(false);
                    }else{
                        System.out.println(nameOfPlayer + " - " + player.getSuspendCounter() + " turns left to get out of jail.\n");
                    }

                }
                if(player.getMoney().getMoney() < 0)
                    player.setBankruptcy(true);
            }else{
                player.setBankruptcy(true);
            }
            duplicateCounter = 0;
            board.nextTurn();

        }
        String[] winnerPlayer = board.getWinner().getName().split(" ");
        System.out.println("\nGame Winner is " + winnerPlayer[0] + " Balance: " + board.getWinner().getMoney().getMoney());


        // log4j test
        //logger.info("log4j");
        //logger.info("here");
    }
}