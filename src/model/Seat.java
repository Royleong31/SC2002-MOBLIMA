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
  private final int row;
  private final int column; // The column that the seat is in
  private boolean seatTaken;
  private final SeatType seatType;

  public Seat(int row, int column, boolean seatTaken, SeatType seatType) {
    this.row = row;
    this.column = column;
    this.seatTaken = seatTaken;
    this.seatType = seatType;
  }

  public int getColumn() {
    return this.column;
  }

  public int getRow() {
    return this.row;
  }

  public String getRowChar() {
    return this.getCharForNumber(this.row);
  }

    // convert the row number into a string, so the seat id can be A1, B2, etc.
  private String getCharForNumber(int i) {
    return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
  }

  public String getId() {
    return this.getCharForNumber(this.row) + Integer.toString(this.column);
  }

  public boolean isTaken() {
    return this.seatTaken;
  }
  
   public void setTaken(boolean isTaken) {
    this.seatTaken = isTaken;
  }

  public SeatType getSeatType() {
    return this.seatType;
  }

}
