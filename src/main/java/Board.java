// Goksel Tokur 150116049 - Merve Ayer 150119828 - Zahide Gur Tastan 150119827 - Ertugrul Sagdic 150116061
import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.lang.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Board {
    private Piece[] Pieces;
    private Square[] squares = new Square[40];
    private int cycle = 0;
    private int currentTurn = 0; // to identify whose turn?
    private int numberofPieces = 0;
    private int wage = 0;
    private int taxSquareMoney = 0;
    private int taxSquareNumber = 0;
    private int[] diceArr;
    Dice dice = new Dice();
    private static final Logger logger = LogManager.getLogger(Game.class); // log4j logger


    public Board(){

    }

    public Board(int numberofPieces, int wage, int taxSquareNumber, int taxSquareMoney, int startMoney, int numberOfGoToJailSquare) {
        Pieces = new Piece[numberofPieces];
        this.setNumberofPieces(numberofPieces);
        this.setWage(wage);
        this.setTaxSquareMoney(taxSquareMoney);
        this.setTaxSquareNumber(taxSquareNumber);

        BasicConfigurator.configure();

        // Toss dices to determine the turn of the players

        diceArr = new int[numberofPieces];
        int q = -2;
        for (int i = 0; i < numberofPieces; i++){
            q-=1;
            diceArr[i] = q;
        }

        for (int i = 0; i < numberofPieces; i++){
            diceArr[i] = dice.getFace();
            if(isThereDuplicate(diceArr))
                i = -1;
        }

        for (int i = 0; i < numberofPieces; i++)
            System.out.println("Player"+i+" tossed "+diceArr[i]);

        for (int i = 0; i < Pieces.length; i++) {
            int indexOflargest = getIndexOfLargest(diceArr);
            diceArr[indexOflargest] = -1;
            Pieces[i] = new Piece(indexOflargest, "Player" + indexOflargest);
            Pieces[i].getMoney().setMoney(startMoney);
        }

        // init squares
        squares[0] = new StartSquare("Start");
        try{
            Object obj = new JSONParser().parse(new FileReader("properties.json") );
            JSONObject jo = (JSONObject) obj;
            JSONArray ja = (JSONArray) jo.get("squares");

            for(int i = 0; i<ja.size(); i++){

                //System.out.println("----->"+ja.size()+"----->"+i);

                JSONObject jsonSquare = (JSONObject) ja.get(i); // 0 is the index of json
                if(((String)jsonSquare.get("color")).equals("railroad")){
                    squares[ ((Long)jsonSquare.get("location")).intValue() ] = new RailRoadSquare((String)jsonSquare.get("name"), (String)jsonSquare.get("color"), ((Long)jsonSquare.get("cost")).intValue(), ((Long)jsonSquare.get("rent")).intValue(), ((Long)jsonSquare.get("mortgage")).intValue());
                }
                else if(((String)jsonSquare.get("color")).equals("utility")){
                    squares[ ((Long)jsonSquare.get("location")).intValue() ] = new UtilitySquare((String)jsonSquare.get("name"), (String)jsonSquare.get("color"), ((Long)jsonSquare.get("cost")).intValue(), ((Long)jsonSquare.get("mortgage")).intValue());
                }
                else{
                    squares[ ((Long)jsonSquare.get("location")).intValue() ] = new ColorSquare((String)jsonSquare.get("name"), (String)jsonSquare.get("color"), ((Long)jsonSquare.get("cost")).intValue(),
                            ((Long)jsonSquare.get("rent")).intValue(), ((Long)jsonSquare.get("rent1")).intValue(), ((Long)jsonSquare.get("rent2")).intValue(), ((Long)jsonSquare.get("rent3")).intValue(),
                            ((Long)jsonSquare.get("rent4")).intValue(), ((Long)jsonSquare.get("hotel")).intValue(), ((Long)jsonSquare.get("pricePerHouse")).intValue(), ((Long)jsonSquare.get("mortgage")).intValue());
                }
                System.out.println(jsonSquare.get("name"));
            }
            //JSONObject jsonSquare = (JSONObject) ja.get(0); // 0 is the index of json
            //squares[1] = new PropertySquare((String)jsonSquare.get("name"), (String)jsonSquare.get("color"), ((Long)jsonSquare.get("cost")).intValue(), ((Long)jsonSquare.get("rent")).intValue());
        }catch (Exception e){
            //System.err.println("Error(JSON): " + e.getMessage());
            logger.error("Properties.json File error", e);

        }

        squares[10] = new JailSquare("Jail Square");
        squares[30] = new GoToJailSquare("Go To Jail");
        numberOfGoToJailSquare--;

        squares[4] = new TaxSquare("Tax Square", taxSquareMoney);
        taxSquareNumber--;
        squares[38] = new TaxSquare("Tax Square", taxSquareMoney);
        taxSquareNumber--;

        squares[7] = new ChanceSquare("Chance Square0",wage);
        squares[22] = new ChanceSquare("Chance Square1",wage);
        squares[36] = new ChanceSquare("Chance Square2",wage);
        squares[2] = new CommunityChestSquare("Community Card 0",wage);
        squares[17] = new CommunityChestSquare("Community Card 1",wage);
        squares[33] = new CommunityChestSquare("Community Card 2",wage);

        /*
        squares[1].doIt(Pieces[1], this );
        System.out.println(((PropertySquare)squares[1]).getOwner().getName());
        Pieces[1].setBankruptcy(true);
        System.out.println(((PropertySquare)squares[1]).getOwner());
        */

        for (int i = 0; i < squares.length; i++) {
            if (squares[i] == null){
                squares[i] = new TaxSquare("Tax Square", taxSquareMoney);
                taxSquareNumber--;
            }
        }

        for (int i = 0; i < squares.length; i++) {
            if (squares[i] == null){
                squares[i] = new GoToJailSquare("Go To Jail");
                numberOfGoToJailSquare--;
            }
        }

        // Randomly place the tax squares.
        for (int i=0; i<taxSquareNumber; i++){
           int rNumber= (int)(Math.random()*(39 - 2) + 2); // Math.random() * (max - min) + min;
           squares[rNumber] = new TaxSquare("Tax", taxSquareMoney);
        }

        // Randomly place go to jail square.
        for (int i=0; i<numberOfGoToJailSquare; i++){
            int rNumber= (int)(Math.random()*(39 - 2) + 2); // Math.random() * (max - min) + min;
            squares[rNumber] = new GoToJailSquare("Go To Jail");
        }


        // Empty squares for others
        for (int i = 0; i < squares.length; i++) {
            if (squares[i] == null){
                squares[i] = new FreeParkingSquare("Free Parking Square");
            }
        }
    }

    public int getTaxSquareNumber() {
        return taxSquareNumber;
    }

    public void setTaxSquareNumber(int taxSquareNumber) {
        this.taxSquareNumber = taxSquareNumber;
    }

    public int getTaxSquareMoney() {
        return taxSquareMoney;
    }

    public void setTaxSquareMoney(int taxSquareMoney) {
        this.taxSquareMoney = taxSquareMoney;
    }

    public Square move(Piece Piece, int face) {
        int sum = Piece.getCurrentPosition() + face;
        if(sum >= squares.length){
            // Piece get money from start point thanks to start square doit
            sum = sum % squares.length;
            Piece.getMoney().addMoney(wage);
        }
        Piece.setPosition(sum);
        System.out.println(Piece.getName()+ " currently at "+ squares[sum].getName() + " Square");
        if(squares[sum] instanceof ColorSquare)
            System.out.println(((ColorSquare)squares[sum]).getNumberOfHouses() == 5 ? ("Has 1 Hotel") : ("Has " + ((ColorSquare)squares[sum]).getNumberOfHouses() + " Houses"));
        squares[sum].doIt(Piece, this);
        return squares[sum];
    }

    public void nextTurn(){
        currentTurn += 1;

        // Sort the players with balance and print.
        if(currentTurn >= Pieces.length){
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.print("End of Cycle "+ cycle +" Order by Balance -> ");
            Piece[] pieces = Pieces;
            sort(pieces);
            int n = pieces.length;
            for (int i=0; i<n; ++i) {
                String[] name = pieces[i].getName().split(" ");
                System.out.print(name[0] + "'s Balance:" + pieces[i].getMoney().getMoney() + " // ");
            }
            System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------");


            currentTurn = 0;
            increaseCycle();
        }
    }

    public Piece whichPieceTurn(){
        return Pieces[currentTurn];
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public int getNumberofPieces() {
        return numberofPieces;
    }

    public void setNumberofPieces(int numberofPieces) {
        this.numberofPieces = numberofPieces;
    }

    public Piece[] getPieces() {
        return Pieces;
    }

    public void setPieces(Piece[] pieces) {
        this.Pieces = pieces;
    }

    public Square[] getSquares() {
        return squares;
    }

    public void setSquares(Square[] squares) {
        this.squares = squares;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public void setWage(int wage){
        this.wage = wage;
    }

    public int getWage() {return wage;}

    public int getCycle(){
        return cycle;
    }

    public void increaseCycle(){
        cycle += 1;
    }

    public int getIndexOfLargest(int[] array) {
        if (array == null || array.length == 0)
            return -1; // null or empty

        int largest = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[largest])
                largest = i;
        }
        return largest; // position of the first largest found
    }

    public boolean isThereDuplicate(int[] array) {
        boolean boo = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    boo = true;
                    int q = -2;
                    for (int w = 0; w < numberofPieces; w++) {
                        q -= 1;
                        diceArr[w] = q;
                    }
                }
            }
        }
        return boo;

    }

    public boolean hasWinner(){
        int notBankrupt = 0;
        for(Piece player: Pieces){
            if(!player.getBankruptcy()){
                notBankrupt++;
            }
        }
        return notBankrupt <= 1;
    }

    public Piece getWinner(){
        if(!hasWinner()){ return null; }
        for(Piece player: Pieces){
            if(!player.getBankruptcy()){ return player; }
        }
        return null;
    }

    void sort(Piece arr[])
    {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j].getMoney().getMoney() > arr[min_idx].getMoney().getMoney())
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            Piece temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }





}