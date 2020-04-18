# Monopoly Simulation

The objective of the project is the implementation of the monopoly board game Java based object-oriented software design approach.

## Use Case
Actors of the game are simulated players. In other words, the software is run as a
computer simulation watched by an observer. Game simulation will be controlled by an
observer. Observer decide how many players will play and then start a game. Simulated
players are going to start playing the game.

* 1. The user determines the number of players who will play the game via terminal input.
Two to eight players can play the game.
* 2. Observer starts a game.
* 3. Every player tosses the dice and who tosses the biggest value is plays the game firstly.
* 4. Player’s piece is moves along the face value.
* 5. When the player moves and located new squares, player have some things to do. It
depends on what types of square are player’s landed.
  * 5.1. If the player lands on Color Square,
    * a. If the Color Square hasn’t owned yet, Player may purchased that square by paying square’s price to bank.
    * b.If the Color Square already owned by other player. Player must pay the rent value to owner of square.
  * 5.2 If the player lands on Chance Square, draw a card from the chance deck.
  * 5.3 If the player lands on Community Chest Square, draw a card from the community chest.
* 6. The system shows turn and cycle counter.
* 7. The system shows name, location information, money of the player which takes a turn.
* 8. Player toss the dice, after tossing dices the system will output face values and sum of faces.
* 9. The player will go to the new location.
* 10. The system shows the name and the type of the location, name of the owner if exists, type and number of properties in the square, the amount of rent or fee if any and
current balance of the user.
* 11. At the end of each cycle the system shows the list of all simulated users’ information and properties ordered by their balance.

## Detailed Information
Take a look at documents for more information about project.
