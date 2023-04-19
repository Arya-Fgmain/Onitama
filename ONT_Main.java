/* Developed By: Arya & Guo Xun
 * Date: Nov 11, 2021
 * Version     : 1.0 */

import java.util.Scanner; 

public class ONT_Main {
  public static void main (String[] args) {
    // Local Variables
    Scanner input = new Scanner(System.in);
    ONT ont = new ONT(); 
    Displayer disp = new Displayer();
    int[][] cardMoves;
    Piece[][] grid;
    Player currP; 
    Card userCard;
    int userOption, cardId, moveId, madeMove = ONT.NOT_PIECE_EXIST;
    
    disp.DisplayGameName();
    
    System.out.println("Welcome, senshi! What would you like to do today?\n1. Play Game\n2. Exit");
    System.out.printf("Choose your path senshi !\n");
    userOption = input.nextInt();
    while (userOption >= 3 || userOption <= 0) { // Error checking
      System.out.println("Invalid option! Try again, warrior!");
      userOption = input.nextInt();
    }
    
    if (userOption == 2) { // Exit
      System.out.println("Sayonara!");
    } else {
      System.out.printf("\nSo...you have chosen the Path of Blood, into the battle field!\n");
      disp.DisplayGuide();
      grid = ont.GetGrid();
      while (true) { // Game Loop
        
        currP = ont.GetCurrPlayer(); // Current Player changes everytime
        if (currP.GetId() == ONT.ID_PLAYER0) { // Displaying grid according to current player's view
          disp.DisplayGrid(grid);
        } else {
          disp.ReverseDisplayGrid(grid);
        }
        
        
        System.out.println();
        System.out.print("Player " + currP.GetId() + " turn (enter # of piece to move): ");
        userOption = input.nextInt();
        while (userOption > ONT.GRID_DIMENSIONS - 1 || userOption < 0) { // Error checking for piece number
          System.out.println("Invalid piece number! Onegaishimasu try again!");
          System.out.print("Player " + currP.GetId() + " turn (enter # of piece to move): ");
          userOption = input.nextInt();
        }
        disp.DisplayPlayerCard(currP.GetId(), currP.GetHand()); 
        System.out.print("Which card do you choose? (enter number): ");
        cardId = input.nextInt();
        
        while (cardId > 2 || cardId < 1) { // Error checking for card number
          System.out.println("Invalid card number! Onegaishimasu try again!");
          System.out.print("Which card do you choose? (enter number): ");
          cardId = input.nextInt();
        }
        userCard = currP.GetHand(cardId - 1); // 1 minus card id (since it starts from 1 onwards)
        disp.DisplayCard(userCard);
        cardMoves = userCard.GetMoves();
        System.out.print("Which move do you choose? (enter number): ");
        moveId = input.nextInt();        
        
        while (moveId > (cardMoves.length) || moveId < 1) { // Error checking for move id
          System.out.println("Invalid move number! Onegaishimasu try again!");
          System.out.print("Which move do you choose? (enter number): ");
          moveId = input.nextInt();
        }
        
        madeMove = ont.Move(currP.GetColor() + userOption, cardId - 1, moveId - 1); // moveId-1 since list starts from 1
        
        while( ( madeMove != ONT.PIECE_KILLED ) && ( madeMove != ONT.PIECE_MOVED ) && ( madeMove != ONT.MOVE_POSSIBLE ) ) {
          // Only possible moves are carried on
          System.out.println("This move is not possible here. Try another move.");
          System.out.print("Enter your chosen move (or no. 5 to skip):"); // Player can skip this round if he/she doesn't like this card
          moveId = input.nextInt();
          if( moveId == 5 ){
            break; // Skipping this player's move
          }
          madeMove = ont.Move(currP.GetColor() + userOption, cardId - 1, moveId - 1); // Trying the move if new move is chosen
        }
        
        
        
        ont.SwitchCard( cardId );
        
        ont.SwitchPlayer();
        
        if( ont.CheckWinner() == ONT.MASTER_KILLED ){ // Checking if the game has ended (Opponent master has been killed)
          System.out.printf("The victory belongs to player %s !", Integer.toString ( ont.GetCurrPlayer().GetId() ) );
          System.out.println("Good job senshi ! You have trod the Way of the Stone");
          break;
        }
        else if ( ont.CheckWinner() == ONT.MASTER_SHRINE_TAKEN ){ // Checking if the game has ended (Opponent master has been killed)
          System.out.printf("The victory belongs to player %s !", Integer.toString ( ont.GetCurrPlayer().GetId() ) );
          System.out.println("Good job senshi ! You have trod the Way of the Stream");
          break;
        }
        
        System.out.printf("\n It is currently player %s's turn to make a move \n \n", Integer.toString(ont.GetCurrPlayer().GetId() ));
        
        
      }
      input.close();
    }
  }
}