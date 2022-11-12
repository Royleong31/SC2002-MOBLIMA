package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;

import constants.Constants;
import utils.DateTimeUtils;
import enums.CinemaType;
import enums.SeatType;
import enums.SortCriteria;
import model.DateTime;

enum SpecialDay {
  WEEKEND,
  HOLIDAY
}

/**
 * System Manager
 * Manages all system data (holidays, price multipliers and sorting criteria)
 *
 @author Roy Leong, Augustine Lee
 @version 1.1
 @since 2022-10-30
*/
public class SystemManager implements Serializable{
  /**
   * Collection of holidays in the system
   */
  private ArrayList<DateTime> holidaysArr = new ArrayList<DateTime>();

  /**
   * Hashmap that maps each cinema type to a price multiplier
   */
  private HashMap<CinemaType, Float> cinemaMultMap = Constants.DEFAULT_CINEMA_PRICE_MAP;

  /**
   * Hashmap that maps each seat type to a price multiplier
   */
  private HashMap<SeatType, Float> seatMultMap = Constants.DEFAULT_SEAT_PRICE_MAP;
  
  /**
   * Sorting criteria set for sorting top movies
   */
  private SortCriteria movieSortingCriteria = Constants.DEFAULT_SORT_CRITERIA;

  /**
   * Get all holiday dates stored in the system
   * 
   * @return arraylist of holidays
   */
  public ArrayList<DateTime> getHolidays() {
    return holidaysArr;
  }
  
  /**
   * Sets all holiday dates in the system
   * 
   */
  public void setHolidays(ArrayList<DateTime> holidaysArr) {
    this.holidaysArr = new ArrayList<DateTime>(holidaysArr);
  }

   /**
    * Get the price multiplier for a particular cinema type
    *
    * @param ct cinema type (enum)
    * @return price multiplier
    */
  public float getCinemaMultiplier(CinemaType ct) {
    return this.cinemaMultMap.get(ct);
  }

  /**
   * Set a new price multiplier for a particular cinema type
   * 
   * @param ct the target cinema type (enum)
   * @param mult the new price multiplier
   */
  public void setCinemaMultiplier(CinemaType ct, float mult) {
    this.cinemaMultMap.put(ct, mult);
  }
  
  /**
   * Get the current sorting criteria setting
   * 
   * @return current sorting criteria (enum)
   */
  public SortCriteria getSortingCriteria() {
    return this.movieSortingCriteria;
  }
  
  /**
   * Set a new sorting criteria to be the system's default sorting criteria for sorting top movies
   * 
   * @param sc new sorting criteria (enum)
   * @throws Exception if there is an invalid sorting criteria (not sales or rating) passed in
   */
  public void setSortingCriteria(SortCriteria sc) throws Exception {
    // These are the only 2 sorting criteria allowed
    if (sc.equals(SortCriteria.SALES) || sc.equals(SortCriteria.RATING)) {
      this.movieSortingCriteria = sc;
      return;
    } 
    throw new Exception("Invalid sorting criteria");
  }
  
  /**
   * Delete a particular cinema type's multiplier
   * 
   * @param ct cinema type (enum)
   * @throws Exception if the particular cinema type does not have a multiplier
   */
  public void deleteCinemaMultiplier(CinemaType ct) throws Exception {
    if (cinemaMultMap.containsKey(ct)) {
      cinemaMultMap.remove(ct);
    }
    throw new Exception("Cinema type do not exists.");
  }

  /**
   * Get the price multiplier for a seat type
   * 
   * @param st seat type (enum)
   * @return price multiplier for that seat type
   */
  public float getSeatMultiplier(SeatType st) {
    return seatMultMap.get(st);
  }

  /**
   * Set a new price multiplier for a particular seat type
   * 
   * @param st seat type(enum)
   * @param multiplier new price multiplier
   */
  public void setSeatMultiplier(SeatType st, float multiplier) {
    seatMultMap.put(st, multiplier);
  }
  
  /**
   * Delete a price multiplier for a seat type
   * 
   * @param st seat type (enum)
   * @throws Exception if seat type does not exist in hashmap
   */
  public void deleteSeatMultiplier(SeatType st) throws Exception {
    if (seatMultMap.containsKey(st)) {
      seatMultMap.remove(st);
    }
    throw new Exception("Seat type does not exists.");
  }

  /**
   * Delete a holiday date from system's collection of holidays
   * 
   * @param year
   * @param month
   * @param day
   * @throws Exception if holiday does not exist in the system collection
   */
  public void deleteHoliday(int year, int month, int day) throws Exception {
    DateTime dt = new DateTime(year, month, day);
    
    if (!this.isHoliday(dt)) {
      throw new Exception("Holiday does not exist in database.");
    }

    this.holidaysArr.removeIf(value -> dt.isDayEqual(value));
  }

  /**
   * Add new holiday to the system's collection of holiday
   * 
   * @param year
   * @param month
   * @param day
   * @param hour
   * @param minute
   * @throws Exception if holiday already exists in the system's collection
   */
  public void addHoliday(int year, int month, int day, int hour, int minute) throws Exception {
    DateTime date = new DateTime(year, month, day, hour, minute);
    if (this.isHoliday(date)) {
      throw new Exception("Holiday already exists in database.");
    }

    this.holidaysArr.add(date);
  }

  /**
   * Check if a particular date is a holiday
   * 
   * @param date
   * @return
   */
  public boolean isHoliday(DateTime date) {
    boolean containsDate = false;

    for (DateTime holiday : holidaysArr) {
      if (holiday.isDayEqual(date)) {
        containsDate = true;
      }
    }

    return containsDate;
  }
}