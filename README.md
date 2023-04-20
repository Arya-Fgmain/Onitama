## So what's Onitama?  
It's a Java adaptation of the board game of the same name. Refer to this link here if you wanna check it out: https://www.arcanewonders.com/product/onitama/. 
This game was made by me in collaboration with Tan Guo Xun for our ICS4U CCT in late 2021.

## How is the game designed
The game was built on Object-Oriented Programming (OOP) principles. The UML diagram outlines the game's components and how they are connected.  

**Card**
Onitama has numerous cards for the players to choose from. Each card has:
* A given name, for example "Phoenix."  
* A list of 3-4 possible moves to choose from.
* A boolean representing whether this card has been played by a player or not

# The Piece Class
There are 13 pieces for every player, consisting of 1 master (resembling the King in chess) and 12 students. Each Piece has:
* A Piece ID (aka pid) which is used to identify its team (White/Black) and its number. Masters are always numbered 0. Students are numbered 1-12.  
An example pid would be "W0," which is the White player's master piece.  
* Health Points (hp), which dictate how many more times they can be striked before being removed from the board.

Piece also has 2 subclasses, which override the Damage() method from Piece to make the game more interesting:
**Student**: represents the Student piece, they require one strike to be removed (implemented in Student.Damage()).
**Master**: represents the Master pieces, they require two strikes to be removed (stronger than the students).

**Player**
Player Objects represent the players. Each player has:
* A player ID, which is either 0 or 1.
* A color, either black or white.
* A set of pieces, stored in a Piece object array.
* A hand of cards, stored in a Card object array.

**ONT**
This class is the game engine. It is responsible for:
* Initializing the board (grid), which is a 2D Array of Piece objects. 
* Initializing the Card objects which represent the main deck.
* Determining who's turn it is.
* Checking if the move requested by a player is allowed. An example would be when the player wants to move the piece in the bottom-left 
corner 1 block down. While this move might be available on one of the player's cards, it is illegal for that specific piece. 
* Implementing a requested move after checking if it is possible.
* Locating a piece on the board to determine if it has been taken or not.
* Determining if the game has ended or not.

**Displayer**
It is responsible for:
* Displaying the game banner and menu.
* Displaying a player's hand so that they can pick their next move.
* Printing the game grid in forward & reverse order.


