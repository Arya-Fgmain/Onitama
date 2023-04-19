/* Developed By: Mohammadarya Faghihy
 * Date: Nov 11, 2021
 * Version     : 1.5 */
public abstract class Piece {
  
  // private variables
  private String pid; // Piece id
  private int     hp; // Piece Object's Health Points
  
  // Constant Global Variables
  public final static int DEAD_HP = 0; // (ARYA) Health when piece is dead (used in ONT.Move())
  public final static int STRT_HP = 2; // (ARYA, START HP) Health when piece is instantiated (used in Piece())
  
  // (ARYA) Constructor
  public Piece(String pid) {
    this.hp = STRT_HP;
    this.pid = pid;
  }
  
  // Public Methods
  
  // Setter Methods
  
  /* (ARYA) Modifies the piece Object's Hp
   * @param newHp     - The new Hp to set for the piece Object */
  public void SetHp(int newHp) {
    this.hp = newHp;
  }
  
  // Getter Methods
  
  /* (ARYA) Returns the piece object's pid
   * @return          - Returns the pid */
  public String GetPid() { // Returns the id information for the piece
    return this.pid;
  }
  
  /* (ARYA) Returns the piece Object's Hp
   * @return          - The hp of the piece Object (0, 1 or 2) */
  public int GetHp() {
    return this.hp;
  }
  
  
  /* (ARYA) Abstract method for returing the type of the Piece ("M" --> Master, "S" --> Student) */
  public abstract String GetType();
  
  /* (ARYA) Abstract method for damaging the piece (decreasing its Hp) */
  public abstract void Damage();
  
}