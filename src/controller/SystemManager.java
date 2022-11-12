package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;

import constants.Constants;
import utils.DateTimeUtils;
import enums.CinemaType;
import enums.SeatType;
import enums.SortCriteria;

enum SpecialDay {
  WEEKEND,
  HOLIDAY
}

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong, Augustine Lee
 @version 1.1
 @since 2022-10-30
*/
public class SystemManager implements Serializable{
  private ArrayList<String> holidaysArr = new ArrayList<String>();
  private HashMap<CinemaType, Float> cinemaMultMap = Constants.DEFAULT_CINEMA_PRICE_MAP;
  private HashMap<SeatType, Float> seatMultMap = Constants.DEFAULT_SEAT_PRICE_MAP;
  private SortCriteria movieSortingCriteria = Constants.DEFAULT_SORT_CRITERIA;

  public ArrayList<String> getHolidays() {
    return holidaysArr;
  }

  public void setHolidays(ArrayList<String> holidaysArr) {
    this.holidaysArr = new ArrayList<String>(holidaysArr);
  }

  /*
   * @param CinemaType
   * @return Float The multiplier for the cinema type
   */
  public float getCinemaMultiplier(CinemaType ct) {
    return this.cinemaMultMap.get(ct);
  }

  // TODO: Add authorisation decorators for all admin methods. Do the same for other methods in other managers
  /*
   * @param CinemaType
   * @param Float The multiplier to set for the cinema type
   * @return void
   */
  public void setCinemaMultiplier(CinemaType ct, float mult) {
    this.cinemaMultMap.put(ct, mult);
  }
  
  public SortCriteria getSortingCriteria() {
    return this.movieSortingCriteria;
  }
  
  public void setSortingCriteria(SortCriteria sc) throws Exception {
    // These are the only 2 sorting criteria allowed
    if (sc.equals(SortCriteria.SALES) || sc.equals(SortCriteria.RATING)) {
      this.movieSortingCriteria = sc;
      return;
    } 
    throw new Exception("Invalid sorting criteria");
  }
  
  /*
  * @param CinemaType
  * @return void
  */
  // TODO: Can't we set it to 1 instead? Why do we need to remove it?
  public void deleteCinemaMultiplier(CinemaType ct) throws Exception {
    if (cinemaMultMap.containsKey(ct)) {
      cinemaMultMap.remove(ct);
    }
    throw new Exception("Cinema type do not exists.");
  }

  /*
   * @param SeatType
   * @return Float The multiplier for the seat type
   */
  public float getSeatMultiplier(SeatType st) {
    return seatMultMap.get(st);
  }

  /*
   * @param SeatType
   * @param Float The multiplier to set for the seat type
   * @return void
   */
  public void setSeatMultiplier(SeatType st, float multiplier) {
    seatMultMap.put(st, multiplier);
  }
  
  /*
  * @param SeatType
  * @return void
  */
  // TODO: Can't we set it to 1 instead? Why do we need to remove it?
  public void deleteSeatMultiplier(SeatType st) throws Exception {
    if (seatMultMap.containsKey(st)) {
      seatMultMap.remove(st);
    }
    throw new Exception("Seat type does not exists.");
  }

  /*
   * @param Int year
   * @param Int month
   * @param Int day
   * @return void
   */
  // !: Needs to be in 2-digit format
  public void deleteHoliday(String year, String month, String day) throws Exception {
    String dateString = day + "." + month + "." + year;
    if (!this.holidaysArr.contains(dateString)) {
      throw new Exception("Holiday does not exist in database.");
    }

    this.holidaysArr.removeIf(value -> dateString.equals(value));
  }

  /*
   * @param Int year
   * @param Int month
   * @param Int day
   * @return void
   */
  public void addHoliday(String year, String month, String day) throws Exception {
    String dateString = day + "." + month + "." + year;
    if (this.holidaysArr.contains(dateString)) {
      throw new Exception("Holiday already exists in database.");
    }
    this.holidaysArr.add(dateString);
  }

  public void addHoliday(String date) throws Exception {
    if (this.holidaysArr.contains(date)) {
      throw new Exception("Holiday already exists in database.");
    }
    this.holidaysArr.add(date);
  }

  /*
   * @param String dateString
   * @return Boolean whether the dateString is in the holidays array
   */
  public boolean isHoliday(String dateString) throws Exception {
    String reconstructedDateString = DateTimeUtils.dateTimeToDate(dateString) + "." + DateTimeUtils.dateTimeToMonth(dateString) + "." + DateTimeUtils.dateTimeToYear(dateString);
    return this.holidaysArr.contains(reconstructedDateString);
  }

}