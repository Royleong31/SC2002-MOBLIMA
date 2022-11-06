package controller;
import java.util.ArrayList;

import model.DateTime;
import model.Screening;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class ScreeningManager {
  private ArrayList<Screening> screeningsArr = new ArrayList<Screening>();

  public void addScreening(Screening screening) {
    screeningsArr.add(screening);
  }

  /**
   * 
   * Update the showtime of a screening
   * Need to call the setDateTime method on screening object
   * @param screening
   * @param newDateTime
   */
  public void updateShowtime(Screening screening, DateTime newDateTime) {
    screening.setDateTime(newDateTime);
  }

  public void deleteScreening(Screening screening, BookingManager bManager) {
    bManager.deleteTicketsFromBookings(screening);
  }

  public ArrayList<Screening> getScreenings(String movieTitle, String cinemaCode, String date) {
    ArrayList<Screening> screenings = (ArrayList<Screening>) screeningsArr.clone();

    screenings.removeIf(screening -> (((!screening.getMovieTitle().equals(movieTitle)) && (movieTitle != null)) ||
                                      ((!screening.getCinemaId().equals(cinemaCode)) && (cinemaCode != null)) ||
                                      ((!screening.getDateTime().equals(date)) && (date != null))
                                      ));

    return screenings;
  }

}