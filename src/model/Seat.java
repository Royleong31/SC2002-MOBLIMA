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
  /**
   * Row number of seat
   */
  private final int row;

  /**
   * Column number of seat
   */
  private final int column;

  /**
   * Boolean value if seat is taken
   */
  private boolean seatTaken;

  /**
   * Type of seat (enum)
   */
  private final SeatType seatType;

  /**
   * Constructor for Seat class
   * @param row row number
   * @param column column number
   * @param seatTaken whether seat is taken
   * @param seatType the type of seat
   */
  public Seat(int row, int column, boolean seatTaken, SeatType seatType) {
    this.row = row;
    this.column = column;
    this.seatTaken = seatTaken;
    this.seatType = seatType;
  }

  /**
   * Get column number of seat
   * @return the column number of seat
   */
  public int getColumn() {
    return this.column;
  }

  /**
   * Get row number of seat
   * @return the row number of seat
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Get the row character (e.g. A, B) of the seat
   * @return the row in character
   */
  public String getRowChar() {
    return this.getCharForNumber(this.row);
  }

  /**
   * Convert the row number into a string, so the seat id can be A1, B2, etc.
   * @param i the row number
   * @return the corresponding character for row
   */
  private String getCharForNumber(int i) {
    return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
  }

  /**
   * Get ID of the seat which is combination of row character and column number (e.g. A1, B2)
   * @return ID of the seat
   */
  public String getId() {
    return this.getCharForNumber(this.row) + Integer.toString(this.column);
  }

  /**
   * Check if the seat is taken
   * @return whether the seat is taken
   */
  public boolean isTaken() {
    return this.seatTaken;
  }
  
  /**
   * Set the vacancy of the seat
   * @param isTaken whether the seat is taken
   */
  public void setTaken(boolean isTaken) {
    this.seatTaken = isTaken;
  }

  /**
   * Get the seat type of the seat
   * @return the type of seat
   */
  public SeatType getSeatType() {
    return this.seatType;
  }

}
