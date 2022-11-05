package controller;

import java.util.ArrayList;
import java.util.HashMap;

import utils.DateTimeUtils;
import enums.CinemaType;
import enums.SeatType;
import enums.FilterType;

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
public class SystemManager {
  private ArrayList<String> holidaysArr;
  private HashMap<Enum, Float> cinemaMultMap;
  private HashMap<Enum, Float> seatMultMap;
  private ArrayList<FilterType> filtersApplied;

  public SystemManager(HashMap<Enum, Float> cinemaMultMap, HashMap<Enum, Float> seatMultMap) {
    this.cinemaMultMap = cinemaMultMap;
    this.seatMultMap = seatMultMap;
    this.filtersApplied = new ArrayList<FilterType>();
    filtersApplied.add(FilterType.SALES);
    filtersApplied.add(FilterType.RATING);
  }

  public ArrayList<String> getHolidays() {
    return holidaysArr;
  }

  public float getCinemaMultiplier(CinemaType ct) throws Exception {
    if (!cinemaMultMap.containsKey(ct)) {
      throw new Exception("Cinema type do not exists.");
    }
    return cinemaMultMap.get(ct);
  }

  public void setCinemaMultiplier(CinemaType ct, float price) {
    cinemaMultMap.put(ct, price);
  }

  public void deleteCinemaMultipler(CinemaType ct) throws Exception {
    if (cinemaMultMap.containsKey(ct)) {
      cinemaMultMap.remove(ct);
    }
    throw new Exception("Cinema type do not exists.");
  }

  public float getSeatMultiplier(SeatType st) throws Exception {
    if (!seatMultMap.containsKey(st)) {
      throw new Exception("Seat type do not exists.");
    }
    return seatMultMap.get(st);
  }

  public void setSeatMultiplier(SeatType st, float multiplier) {
    seatMultMap.put(st, multiplier);
  }

  public void deleteSeatMultiplier(SeatType st) throws Exception {
    if (seatMultMap.containsKey(st)) {
      seatMultMap.remove(st);
    }
    throw new Exception("Seat type do not exists.");
  }

  public void addFilter(FilterType ft) throws Exception {
    if (filtersApplied.contains(ft)) {
      throw new Exception("Filter is already applied");
    }
    filtersApplied.add(ft);
  }

  public void deleteFilter(FilterType ft) throws Exception {
    if (filtersApplied.contains(ft)) {
      filtersApplied.remove(ft);
    }
    throw new Exception("Filter is not applied");
  }

  public void deleteHoliday(int year, int month, int day) throws Exception {
    String dateString = day + "." + month + "." + year;
    if (!holidaysArr.contains(dateString)) {
      throw new Exception("Holiday do not exist in database.");
    }
    holidaysArr.removeIf(value -> dateString.equals(value));
  }

  public void addHoliday(int year, int month, int day) throws Exception {
    String dateString = day + "." + month + "." + year;
    if (holidaysArr.contains(dateString)) {
      throw new Exception("Holiday already exists in database.");
    }
    holidaysArr.add(dateString);
  }

  public boolean isHoliday(String dateString) throws Exception {
    String reconstructedDateString = DateTimeUtils.dateTimeToDate(dateString) + "." + DateTimeUtils.dateTimeToMonth(dateString) + "." + DateTimeUtils.dateTimeToYear(dateString);
    return holidaysArr.contains(reconstructedDateString);
  }

}