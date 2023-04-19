/* Developed By: TGX
 * Date: Nov 22, 2021
 * Version     : 1.4 */

public class Card {
  
  private String name; // Name of the card
  private boolean isPlayed; // (ARYA) Checking if this card has just been used
  private int[][] moves; // Moves available for the Card
  
  
  public Card( String name, int[][] moves ){ // Passing the name String and moves[][] array
    this.name = name;
    this.moves = moves;
    this.isPlayed = false; // isPlayed is initially false for all cards (they have not been played yet)
  }
  
  // Setter Methods //
  
  /* (ARYA) Changes the isPlayed variable
   * @param val       - The new boolean value to assign */
  public void SetIsPlayed(boolean val) {
    this.isPlayed = val;
  }
  
  /* (ARYA) Tells whether card has been played or not
   * @return          - Returns the isPlayed private boolean */
  public boolean GetIsPlayed() {
    return this.isPlayed;
  }
  
  // Getter Methods //
  
  /* Returns the name of the card
   * @return          - Returns card name */
  public String GetName() {
    return this.name;
  }
  
  /* Returns the moves of the card
   * @return          - Returns card's move int[][] array */
  public int[][] GetMoves() {
    return this.moves; // Returns the moves of this Card
  }
  
  
}