/* Developed By: Arya & Guo Xun
 * Date: Nov 11, 2021
 * Version     : 1.4 */

public class ONT {
  private Player currPlayer; // current Player
  private Card groundCard; // groundCard (card not in posession by any playerat the moment)
  private Piece[][] grid; // grid ( 13 x 13 )
  private Card[] deck; // deck of cards
  private Player[] players; // players ( 0 & 1 )
  
  // Constant Variables //
  
  final int    NOT_MOVE_POSSIBLE   = -2;  // (ARYA) For this.checkMoves() and Move(), showing that a move is not permissible
  final int    NOT_OPPONENT_PIECE  = -3;  // (ARYA) For this.checkMoves() and Move(), showing move isn't permissbile as it overlaps friendly piece
  final int    NOT_WIN             =  1;  // (ARYA) For this.checkWinner(), showing that the game must continue
  
  // Constant Global Variables //
  
  final static int        ID_PLAYER0          =   0;  // (ARYA) Player 0's id
  final static int        ID_PLAYER1          =   1;  // (ARYA) Player 1's id
  final static String     COLOR_PLAYER0       = "W";  // (ARYA) Player 0's color: White, for ONT()
  final static String     COLOR_PLAYER1       = "B";  // (ARYA) Player 1's color: Black, for ONT()
  final static int        GRID_DIMENSIONS     =  13;  // (ARYA) Default Grid Dimensions
  final static int        NOT_PIECE_EXIST     =  -1;  // (ARYA) For this.findPiece() & this.checkMoves(), showing that a piece has been killed
  final static int        MOVE_POSSIBLE       =   0;  // (ARYA) For this.checkMoves(), allowing a move to be done
  final static int        MASTER_KILLED       =   2;  // (ARYA) For this.checkWinner(), signifying end of game (opponent master is dead)
  final static int        MASTER_SHRINE_TAKEN =   3;  // (ARYA) For this.checkWinner(), signifying end of game (opponent master shrine is invaded)
  final static int        PIECE_KILLED        =   1;  // (TGX)  For this.Move(), shows that a piece is killed
  final static int        PIECE_MOVED         =   2;  // (TGX)  For this.Move(), shows that a piece has moved
  
  // Private (Helper) Methods //
  
  /* (ARYA) Puts the Players' Piece objects on the grid */
  private void initializeGrid() { 
    this.grid[GRID_DIMENSIONS-1] = players[0].GetPieces(); // Player 1's pieces at the top row, Player 0's at the bottom
    this.grid[0] = players[1].GetPieces();
    Piece[] tempMasters = {this.grid[GRID_DIMENSIONS-1][0], this.grid[0][0]}; // Storing the masters in a temporary Piece array
    for (int i = 0; i < (GRID_DIMENSIONS / 2); i++) { // Looping through the first half and moving every Piece to the pos before it (1 to 0, 2 to 1, ...)
      this.grid[GRID_DIMENSIONS-1][i] = this.grid[GRID_DIMENSIONS-1][i+1];
      this.grid[0][i] = this.grid[0][i+1];
    }
    this.grid[GRID_DIMENSIONS-1][(GRID_DIMENSIONS / 2)] = tempMasters[0];  //Placing the masters in the middle
    this.grid[0][(GRID_DIMENSIONS / 2)] = tempMasters[1];
  }
  
  
  /* (ARYA) Checks to see if the given move can be performed for the given piece (according to the current player)
   * @param piecePos  - The position of the piece in question
   * @param move      - The move to consider (1 move from a card of moves)
   * @return          - Returns 0 if the move is possible
   *                    Returns -1 if the piece does not exist
   *                    Returns -2 if the move puts the piece out of the grid (not possible)
   *                    Returns -3 if the move puts the piece on a friendly piece */
  private int checkMoves(int[] piecePos, int[] move) {
    if ( (piecePos[0] < 0 || piecePos[1] < 0) || this.grid[piecePos[0]][piecePos[1]] == null ) { // Ensuring piece exists
      return NOT_PIECE_EXIST;
    }
    int[] movedPos = new int[2];
    if (this.currPlayer.GetId() == ID_PLAYER0) { // Moves are calculated according to the grid positions
      movedPos[0] = piecePos[0] - move[0]; // Moving up = Moving to lower indexes & Down = to higher indexes
      movedPos[1] = piecePos[1] + move[1];
    } else { // Reversed moves for player 1
      movedPos[0] = piecePos[0] + move[0]; 
      movedPos[1] = piecePos[1] - move[1]; 
    }
    if (((movedPos[0]) >= 0 && movedPos[0] < GRID_DIMENSIONS) && (movedPos[1] >= 0 && movedPos[1] < GRID_DIMENSIONS)) { // Checking if move is possible (within grid dimensions)
      Piece currPiece = this.grid[movedPos[0]][movedPos[1]]; // Storing possible piece at the moved position
      if (currPiece != null && String.valueOf(currPiece.GetPid().charAt(0)).equals(this.currPlayer.GetColor())) { // Checking if the move lands us on a friendly piece
        return this.NOT_OPPONENT_PIECE;
      } else {
        return MOVE_POSSIBLE;
      }
    } else {
      return this.NOT_MOVE_POSSIBLE;
    }
  }
  
  /* (ARYA) Finds the piece with the given pid
   * @param pid       - The pid of the piece on question
   * @return          - Returns the location of the piece in the grid (if it exists & is not killed) 
   *                  - Returns {-1, -1} if the piece is killed (removed from the grid) */
  private int[] findPiece(String pid) {
    int[] result = { NOT_PIECE_EXIST, NOT_PIECE_EXIST }; // Storing the 2D grid position of the Piece=
    for (int i = 0; i < this.grid.length; i++) { // Traversing grid to find piece
      for (int j = 0; j < this.grid[i].length; j++) { 
        if ((this.grid[i][j] != null) && (this.grid[i][j].GetPid().equals(pid))) { // If the pid passed as an argument matches a non-null Pieces, 
          result[0] = i; // Then store its position
          result[1] = j; 
        }
      }
    }
    return result;
  }
  
  /* (ARYA) Distributes cards to player, and sets the ground card as well
   * @param indexes   - The index of the cards to distribute (randomized by randNums) */
  private void distCards(int[] indexes) {
    Card[] player0Hand = { this.deck[indexes[0]], this.deck[indexes[1]] }; // Distributing players' hands
    Card[] player1Hand = { this.deck[indexes[2]], this.deck[indexes[3]] };
    this.players[0].SetHand(player0Hand); // Setting the players' hands
    this.players[1].SetHand(player1Hand);
    this.groundCard = this.deck[indexes[4]]; // and setting groundcard
  }
  
  /* (TGX) Generates an array of 5 random numbers
   * @returns        An array of 5 random numbers*/
  private int[] randNums() {
    int[] randNum = new int[5];
    int Random;
    boolean repeat;
    for( int i = 0; i < randNum.length; i++ ){
      // Loops the checker //
      do{
        repeat = false;
        // Randomize between 0 - 15//
        Random = ( int )( Math.random() * 15 + 0 ) ;
        for( int j = 0; j < randNum.length; j++ ){
          // Checkes if the random number exist or not //
          if ( randNum[j] == Random ){
            // Continues the loop if the number already exist in the array //
            repeat = true;
          }
        }
      } while( repeat );
      // Stores the random number if it doesnt exist //
      randNum[i] = Random;
    }
    return randNum;
  }
  
  // (ARYA --> CODE & GUO XUN --> CARDS) Default Constructor //
  public ONT() {
    this.grid = new Piece[GRID_DIMENSIONS][GRID_DIMENSIONS]; // creating the 13 x 13 grid
    this.deck = new Card[16]; // initializing the deck[] array (cards are added afterwards)
    this.players = new Player[2]; 
    this.players[0] = new Player(ID_PLAYER0, COLOR_PLAYER0);  // Assigning ids and colors to players
    this.players[1] = new Player(ID_PLAYER1, COLOR_PLAYER1); 
    // Assigning cards (16, including 3-moves and 4-moves) to deck
    // Note: moves are based on player 0's perspective (moving up the grid)
    int[][] move0 = { {-1, 0}, {1, 2}, {1, -2} }; // Move format {UP/DOWN, RIGHT/LEFT} DOWN and LEFT are signified by -ve signs
    this.deck[0]  = new Card("Giraffe", move0);
    int[][] move1 = { {2, -1}, {-2, 0}, {2, 1} };
    this.deck[1]  = new Card("Kirin", move1);
    int[][] move2 = { {1, 1}, {1, -1}, {0, -2}, {0, 2} };
    this.deck[2]  = new Card("Phoenix", move2);
    int[][] move3 = { {-1, -1}, {0, 2} ,{-1, 1} ,{0 ,-2} };
    this.deck[3]  = new Card("Turtle", move3);
    int[][] move4 = { {1, 1}, {0, 1}, {-1, 1} };
    this.deck[4]  = new Card("Fox", move4);
    int[][] move5 = { {-1, -1}, {1, 0}, {1, 1} };
    this.deck[5]  = new Card("Panda", move5);
    int[][] move6 = { {-1, -1}, {1, 0}, {0, 2} };
    this.deck[6]  = new Card("Sea Snake", move6);
    int[][] move7 = { {-1, -1}, {1, 0}, {0, 1} };
    this.deck[7]  = new Card("Mouse", move7);
    int[][] move8 = { {-1, -1}, {1, 0}, {1, 2} };
    this.deck[8]  = new Card("Tanuki", move8);
    int[][] move9 = { {0, -2}, {-1, -1}, {1, 1} };
    this.deck[9]  = new Card("Sable", move9);
    int[][] move10 = { {1, -1}, {0, -1}, {-1, -1} };
    this.deck[10] = new Card("Dog", move10);
    int[][] move11 = { {1, -1}, {1, 0}, {-1, 1} };
    this.deck[11] = new Card("Bear", move11);
    int[][] move12 = { {0, -2}, {1, 0}, {-1, -1} };
    this.deck[12] = new Card("Viper", move12);
    int[][] move13 = { {0, -1}, {1, 0}, {-1, 1} };
    this.deck[13] = new Card("Rat", move13);
    int[][] move14 = { {1, -2}, {1, 0}, {-1, 1} };
    this.deck[14] = new Card("Iguana", move14);
    int[][] move15 = { {1, -1}, {-1, 1}, {0, 2} };
    this.deck[15] = new Card("Otter", move15);
    this.distCards(this.randNums()); // (ARYA) Distributing random cards
    this.initializeGrid(); // (ARYA) Putting the players' peices in the grid
    this.SetCurrPlayer(0); // Setting the current player
  }
  
  // Setter Methods
  
  /* (ARYA) Sets the currPlayer according to the given id
   * @return          - Returns the Piece grid array */
  public void SetCurrPlayer( int id ) {
    this.currPlayer = players[id];
  }
  
  // Getter Methods
  
  /* (ARYA) Returns the current player
   * @param pid       - The pid of the piece on question
   * @return          - Returns the currPlayer player Object */
  public Player GetCurrPlayer() {
    return this.currPlayer;
  }
  
  /* (TGX) returns the extra card (used in swapping) */
  public Card GetGroundCard() {
    return groundCard;
  }
  
  /* (ARYA) Returns the grid
   * @return          - Returns the grid array */
  public Piece[][] GetGrid() {
    return this.grid;
  }
  
  /* (TGX) Swaps the current player */
  public void SwitchPlayer(){
    
    /* Checks the id of the current player to determine if it's either player 1 or 2
     * Swaps the current player accordingly*/
    if ( currPlayer.GetId() == ID_PLAYER0 ) {
      SetCurrPlayer( ID_PLAYER1 );
    }
    else {
      SetCurrPlayer(ID_PLAYER0 );
    }
    
    
  }
  
  /* (TGX) Switches the card that was played by the player with the ground card */
  public void SwitchCard( int index ) {
    index--;
    // Stores the player's hand //
    Card playerHand = ( this.currPlayer ).GetHand( index );
    ( this.currPlayer ).SetHand( groundCard, index );
    this.groundCard = playerHand;
  }
  
  
  /* (ARYA) Checks if a player has won (and thereby game has ended)
   * @return          - Returns 1 if none of the below conditions are satisfied (game should continue)
   *                    Returns 2 if a Player's master has been murdered, 
   *                    Returns 3 if a Player's master has taken his/her opponent master's shrine */
  public int CheckWinner() {
    int[][] masterLocations = { this.findPiece("W0"), this.findPiece("B0") }; // Finding masters on grid
    if (masterLocations[0][0] == -1 ||masterLocations[1][0] == -1 ) { // Checking masters' existence
      return MASTER_KILLED; 
    } // Below: checking if a player has taken the opponent master's shrine
    else if ( (this.grid[0][GRID_DIMENSIONS / 2] != null && !this.grid[0][6].GetPid().equals("B0")) || (this.grid[GRID_DIMENSIONS-1][GRID_DIMENSIONS / 2] != null && !this.grid[GRID_DIMENSIONS-1][6].GetPid().equals("W0"))) {
      return MASTER_SHRINE_TAKEN;
    }
    return this.NOT_WIN;
  }
  
  
  /* (TGX) Move Method for the pieces
   * @param     pid - The piece ID
   *            cardId - The index of the card on the players hand
   *            moveId - The index of the card's move on it's array
   *
   * @return    -3 If piece is landing on a friendly
   *            -2 If piece will be landed out of the grid
   *            -1 Piece does not exist
   *             0 If the master has been damaged
   *             1 An opponent's piece has been taken
   *             2 If a basic move have been performed */
  
  public int Move(String pid, int cardId, int moveId) {
    int[] newLoc = new int[2];
    
    // Stores the current card in the player's hand //
    Card currCard = currPlayer.GetHand( cardId );
    
    // Stores the current SET of moves in the cards //
    int[] currMove = currCard.GetMoves()[moveId];
    // Find and stores the location of the piece //
    int[] pieceLoc = findPiece(pid);
    
    // Checking if the move is possible //
    if( pieceLoc[0] == NOT_PIECE_EXIST ) {
      return NOT_PIECE_EXIST;
    }
    else if ( checkMoves(pieceLoc, currMove) == this.NOT_MOVE_POSSIBLE ){
      return this.NOT_MOVE_POSSIBLE;
    }
    else if ( checkMoves(pieceLoc, currMove) == this.NOT_OPPONENT_PIECE ){
      return this.NOT_OPPONENT_PIECE;
    }
    
    // Stores the original piece information from the grid //
    Piece movePiece = this.grid[ pieceLoc[0] ][ pieceLoc[1] ];
    
    // Using the player to check and "flip" the grid accordingly //
    if( currPlayer.GetId() == ID_PLAYER0 ) {
      newLoc[0] = pieceLoc[0] - currMove[0];
      newLoc[1] = pieceLoc[1] + currMove[1];
    }
    else {
      newLoc[0] = pieceLoc[0] + currMove[0];
      newLoc[1] = pieceLoc[1] - currMove[1];
    }
    
    // Checks if the new location have a new piece and changes the damage/hp of the pieces //
    if( this.grid[ newLoc[0] ][ newLoc[1] ] != null ) {
      
      // Damages the piece //
      this.grid[ newLoc[0] ][ newLoc[1] ].Damage();
      // Checks piece hp to determine if it's been killed //
      if ( this.grid[ newLoc[0] ][ newLoc[1] ].GetHp() == Piece.DEAD_HP ) {
        this.grid[ newLoc[0] ][ newLoc[1] ] = movePiece;
        this.grid[ pieceLoc[0] ][ pieceLoc[1] ] = null;
        return PIECE_KILLED;
      }
      else if ( this.grid[ newLoc[0] ][ newLoc[1] ].GetHp() != Piece.DEAD_HP ) {
        return MOVE_POSSIBLE;
      }
      
    }
    
    // Moves the piece by replacing the old piece //
    else {
      this.grid[ newLoc[0] ][ newLoc[1] ] = movePiece;
      this.grid[ pieceLoc[0] ][ pieceLoc[1] ] = null;
      return PIECE_MOVED;
    }
    
    // This only gets returned if the checkers doesnt work. //
    return 4;
    
  }
  
}