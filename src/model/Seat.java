package model;

import enums.SeatType;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong, Augustine Lee
 @version 1.1
 @since 2022-10-30
*/
public class Seat {
  private final String row;
  private final Integer seatNumber;
  private boolean seatTaken;
  private final SeatType seatType;
  //private Ticket affiliatedTicket; // ?: Is this necessary?

  public Seat(String row, Integer seatNumber, boolean seatTaken, SeatType seatType, Ticket affiliatedTicket) {
    this.row = row;
    this.seatNumber = seatNumber;
    this.seatTaken = seatTaken;
    this.seatType = seatType;
    //this.affiliatedTicket = affiliatedTicket;
  }

  public int getSeatNumber() {
    return seatNumber;
  }

  public String getRow() {
    return this.row;
  }

  public String getId() {
    return this.row.toString() + this.seatNumber.toString();
  }

  public boolean isSeatTaken() {
    return this.seatTaken;
  }
  
   public void setTaken() {
    this.seatTaken = true;
  }

  public SeatType getSeatType() {
    return this.seatType;
  }

}
