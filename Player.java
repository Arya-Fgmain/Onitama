/* Developed By: Mohammadarya Faghihy
 * Date: Nov 11, 2021
 * Version     : 1.6 */
public class Player {
  
  private int   id; // id of the Player, values used: ONT.ID_PLAYER0 and ID_PLAYER1
  private String color; // Color of the Player (White - Player0 / Black - Player1) Refer to ONT.java for more info 
  private Piece[] pieces; // pieces owned by the player
  private Card[]  hand; // Cards currently in posession of the player
  
  // Private Methods
  
  /* (ARYA) Create's the player's pieces and assigns pids to them */
  private void initializePieces() {
    String playerColor = this.GetColor(); // Assigning either White or Black according to the player's id
    
    this.pieces[0] = new Master(playerColor + 0); // Master is the first piece
    
    for (int i = 1; i < this.pieces.length; i++) {
      this.pieces[i] = new Student(playerColor + i); // Students range from 1-12
    }
  }
  
  // (ARYA) 1st Constructor
  public Player(int id, String color) {
    this.id = id; 
    this.pieces = new Piece[13];
    this.color = color;
    this.initializePieces(); 
  }
  
  // Public Methods
  
  // Setter Methods
  
  /* (ARYA) Changes the player's hand entirely
   * @param newHand   - The new card[] array to be assigned */
  public void SetHand(Card[] newHand) {
    this.hand = newHand; // Changes the hand of the player (Onitama card swap)
  }
  
  /* (TGX) Set a card in the player's hand to become the ground card
   * @param                groundCard The "ground card" or extra card that will be swapped with the hand
   *                       index The card's index in the player's hand */
  public void SetHand( Card groundCard, int index ) {
    this.hand[index] = groundCard;
  }
  
  // Getter Methods
  
  /* (ARYA) Returns the player's id
   * @return          - Returns either 0/1 based on the player */
  public int GetId() {
    return this.id;
  }
  
  /* (ARYA) Returns the player color (Black / White)
   * @return          - Returns the color */
  public String GetColor() {
    return this.color;
  }
  
  /* (ARYA) Returns the pieces of the player
   * @return          - The 13 pieces (master & students) in posession of the player */
  public Piece[] GetPieces() {
    return this.pieces;
  }
  
  /* (ARYA) Returns the player's hand (cards)
   * @return          - Returns the Card[] array containing the player's hand */
  public Card[] GetHand() {
    return this.hand;
  }
  
  /* (ARYA) Returns the card with the given index
   * @param index     - The index of the card in question
   * @return          - Returns the card object according to its index in the hand[] array */
  public Card GetHand(int index) {
    return this.hand[index];
  }
  
}