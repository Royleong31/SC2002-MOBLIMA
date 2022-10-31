package model;

import java.util.ArrayList;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class SeatingPlan {
  private int rows;
  private int cols;
  private int aisle;

  public SeatingPlan(int rows, int cols, int aisle) {
    this.rows = rows;
    this.cols = cols;
    this.aisle = aisle;
  }

  public int remainingSeats(ArrayList<Seat> takenSeatsArr) {
    int totalSeats = rows * cols;
    int takenSeats = takenSeatsArr.size();
    return totalSeats - takenSeats;
  }

  public boolean isSeatAvailable(ArrayList<Seat> takenSeatsArr, Seat seat) {
    // use the arr of taken seats to check if the 
    return false;
  }
}
