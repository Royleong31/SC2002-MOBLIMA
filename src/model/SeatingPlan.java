package model;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Seating Plan generated for the cinemas
 *
 @author Roy Leong, Ryan Ng
 @version 1.1
 @since 2022-10-30
*/
public class SeatingPlan implements Serializable{
  private int rows;
 
  /**
   * The number of columns of seats in the cinema's seating plan
   */
  private int cols;
 
  /**
   * The number of aisles, with aisles being passageways specifically between seats
   */
  private int aisle;

  /**
   * Constructor for the seating plan class
   * @param rows is the number of rows of seats
   * @param cols is the number of columns of seats
   * @param aisle is the number of aisles among the seats
   */
  public SeatingPlan(int rows, int cols, int aisle) {
    this.rows = rows;
    this.cols = cols;
    this.aisle = aisle;
  }

  /**
   * Gets the number of rows of seats.
   * @return rows is the number of rows of seats
   */
  public int getRows() {
    return rows;
  }

  /**
   * Gets the number of columns of seats.
   * @return cols is the number of columns of seats.
   */
  public int getColumns() {
    return cols;
  }
  
  /**
   * Gets the number of aisles among the seats.
   * @return this,aisle is the number of aisles among the seats
   */
  public int getAisle() {
    return this.aisle;
  }

  /**
   * Determines the number of remaining seats left by deducting the number of taken seats from the total number of seats
   * @param takenSeatsArr is the ArrayList of taken seats
   * @return Math.max((rows*cols - takenSeatsArr.size()),0) is the number of remaining seats
   */
  public int remainingSeats(ArrayList<Seat> takenSeatsArr){
    return Math.max((rows*cols - takenSeatsArr.size()),0);
  }
}
