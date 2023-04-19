/* Developed By: Guo Xun (TGX)
 * Revised Date: Nov 17, 2021
 * Version: 1.6 */

public class Displayer {
  
  
  
  
  /* (TGX) Prints the grid out with the pieces */
  public void DisplayGrid( Piece[][] grid ) {
    
    // Dimensions of the grid //
    int Height = grid.length;
    int Width = grid[0].length;
    
    // Dynamic variable for the width and it's content // 
    System.out.println("=========================================================================================================");
    
    // Loops the height of the grid //
    for ( int H = 0; H < Height; H++ ) {
      
      for ( int W = 0; W < Width; W++ ) {
        
        // Checks if the grid is null and place a space if it's null //
        if( grid[H][W] == null ){
          System.out.printf("| %5s ", " ");
        }
        // Places the type of piece if it's not null //
        else {
          System.out.printf("| %s-%-3s ", grid[H][W].GetType(), grid[H][W].GetPid() );
        }
        
        
      }
      
      // Prints out the | for the last block and also moves the cursor to the next line //
      System.out.printf("|");
      System.out.println("");
      if( H < 12 ){
        System.out.println("---------------------------------------------------------------------------------------------------------");
      }
      
    }
    // Prints out the bottom outline of the box //
    System.out.println("=========================================================================================================");
    
  }
  
  /* (TGX) Reverses the display grid by priting it from end of the array 
   * @return             Prints out the display grids in reverse
   */
  public void ReverseDisplayGrid( Piece[][] grid ) {
    
    // Dimensions of the grid //
    int Height = grid.length;
    int Width = grid[0].length;
    
    System.out.println("=========================================================================================================");
    
    // Loops the height of the grid //
    for ( int H = Height - 1; H >= 0; H-- ) {
      
      for ( int W = Width - 1 ; W >= 0; W-- ) {
        
        // Checks if the grid is null and place a space if it's null //
        if( grid[H][W] == null ){
          System.out.printf("| %5s ", " ");
        }
        // Places the type of piece if it's not null //
        else {
          System.out.printf("| %s-%-3s ", grid[H][W].GetType(), grid[H][W].GetPid() );
        }
        
        
      }
      
      // Prints out the | for the last block and also moves the cursor to the next line //
      System.out.printf("|");
      System.out.println("");
      if( H > 0 ){
        System.out.println("---------------------------------------------------------------------------------------------------------");
      }
      
    }
    // Prints out the bottom outline of the box //
    System.out.println("=========================================================================================================");
    
  }
  
  /* (ARYA) Introduces card (name & movements) (Works for both players because of the grid flipping mechanism)
   * @param card      - The card object to display */
  public void DisplayCard(Card card) {
    // Note: Moves with a value of 0 are not shown
    String name = card.GetName().toUpperCase(); // Making the card name uppercase for display
    System.out.println("========== " + name + " ==========");
    int[][] moves = card.GetMoves(); // Storing the card's moves to display them
    // Note: If no move is performed in a direction (value 0), then it is not printed
    for (int i = 0; i < moves.length; i++) { 
      System.out.print("MOVE " + (i+1) + ": --> ");
      if (moves[i][0] > 0) { // Checking if move is positive (UP)
        System.out.print(moves[i][0] + " UP. ");
      } else if (moves[i][0] < 0) { // Or negative (DOWN)
        System.out.print((-1 * moves[i][0]) + " DOWN. ");
      }
      if (moves[i][1] > 0) { // Checking if move is positive (RIGHT)
        System.out.print(moves[i][1] + " RIGHT. ");
      } else if (moves[i][1] < 0) { // Or negative (LEFT)
        System.out.print((-1 * moves[i][1]) + " LEFT. ");
      }
      System.out.println(); // Space between every presented move of the card
    }
  }
  
  /* (ARYA) Introduces card (name & movements) (Works for both players because of the grid flipping mechanism)
   * @param playerId  - The id of the player whose cards are to be displayed 
   * @param hand      - The player's hands */
  public void DisplayPlayerCard(int playerId, Card[] hand){
    System.out.println("PLAYER " + playerId + " has the following cards available: ");
    for (int i = 0; i < hand.length; i++) { // Traversing the player's hand
      System.out.println((i+1) + " " + hand[i].GetName().toUpperCase()); // All names are presented in uppercase letters for consistency
    }
  }
  
  /* (ARYA) Shows Onitama's name in Ascii Art (DOOM font) */
  public void DisplayGameName() {
    System.out.println("=============================================");
    System.out.println(" _____ _   _ _____ _____ ___  ___  ___  ___ ");    
    System.out.println("|  _  | \\ | |_   _|_   _/ _ \\ |  \\/  | / _ \\");
    System.out.println("| | | |  \\| | | |   | |/ /_\\ \\| .  . |/ /_\\ \\");
    System.out.println("| | | | . ` | | |   | ||  _  || |\\/| ||  _  |");
    System.out.println("\\ \\_/ / |\\  |_| |_  | || | | || |  | || | | |");
    System.out.println(" \\___/\\_| \\_/\\___/  \\_/\\_| |_/\\_|  |_/\\_| |_/"); 
    System.out.println("=============================================");
  }
  
  /* (ARYA) Displays a guide on players' colors and piece types */
  public void DisplayGuide() {
    System.out.println("\n===========GUIDES===========");
    System.out.println("----------PLAYERS-----------");
    System.out.println("Player 0---------WHITE (\"" + ONT.COLOR_PLAYER0 + "\")");
    System.out.println("Player 0---------BLACK (\"" + ONT.COLOR_PLAYER1 + "\")");
    System.out.println("-----------PIECES-----------");
    System.out.println("Student ---------#1-12 (\"S\")");
    System.out.println("Master  ---------#0    (\"M\")");
    System.out.println("========END-OF-GUIDE========\n");
  }
  
  
}