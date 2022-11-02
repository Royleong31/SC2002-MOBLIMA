package model;

import java.util.ArrayList;

/**
 * Seating Plan
 *
 @author Roy Leong, Ryan Ng
 @version 1.1
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

  public int remainingSeats(ArrayList<Seat> takenSeatsArr) throws Exception {
    int totalSeats = rows * cols;
    int takenSeats = takenSeatsArr.size();
    if(takenSeats > totalSeats) throw new Exception("Error: more takenSeats than totalSeats!");
    return totalSeats - takenSeats;
  }

  public boolean isSeatAvailable(ArrayList<Seat> takenSeatsArr, Seat seat) {
    for(Seat checkSeat: takenSeatsArr){
		if(checkSeat == seat) return false;
	}
    return true;
  }
}
