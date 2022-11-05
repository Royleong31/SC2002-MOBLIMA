package model;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong, Augustine Lee
 @version 1.1
 @since 2022-10-30
*/
public class Seat {
  private int seatNumber;
  private boolean seatTaken;
  //private Ticket affiliatedTicket; // ?: Is this necessary?

  public Seat(int seatNumber, boolean seatTaken, Ticket affiliatedTicket) {
    this.seatNumber = seatNumber;
    this.seatTaken = seatTaken;
    //this.affiliatedTicket = affiliatedTicket;
  }

  public int getSeatNumber() {
    return seatNumber;
  }

  public boolean isSeatTaken() {
    return seatTaken;
  }
  
   public void setTaken() {
    this.seatTaken = true;
  }
}
