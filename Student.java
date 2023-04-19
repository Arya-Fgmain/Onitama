/* Developed By: Mohammadarya Faghihy
 * Date: Nov 11, 2021
 * Version     : 1.4 */
public class Student extends Piece {
  
  // (ARYA) Constructor 
  public Student(String pid) {
    super(pid); // passing the isTaken boolean value to the Piece Superclass constructor
  }
  
  // Public Methods
  
  // Getter Methods
  
  /* (ARYA) (Overridden from Piece Superclass) Returns the type of the Piece (Student) */
  public String GetType() {
    return "S"; 
  }
  
  /* (ARYA) (Overridden from Piece Superclass) Takes 2 points off the student's current HP (killed instantly) */
  public void Damage() {
    this.SetHp( this.GetHp() - STRT_HP );
  }
  
}