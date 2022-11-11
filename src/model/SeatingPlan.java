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

  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return cols;
  }
  
  public int getAisle() {
    return this.aisle;
  }

  public int remainingSeats(ArrayList<Seat> takenSeatsArr){
    return Math.max((rows*cols - takenSeatsArr.size()),0);
  }
}