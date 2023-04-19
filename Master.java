/* Developed By: Mohammadarya Faghihy
 * Date: Nov 11, 2021
 * Version     : 1.4 */
public class Master extends Piece {
  
  // (ARYA) Constructor
  public Master(String pid) { 
    super(pid); // passing the isTaken boolean value to the Piece Superclass constructor
  }
  
  // Public Methods
  
  // Getter Methods
  
  /* (ARYA) (Overridden from Piece Superclass) Returns the type of the Piece (Master) */
  public String GetType() {
    return "M";
  }
  
  /* (ARYA) (Overridden from Piece Superclass) Takes 1 point off the master's current HP (needs 2 damages to be killed) */
  public void Damage() {
    this.SetHp( this.GetHp() - (STRT_HP / 2) );
  }
  
}