package controller;
import java.util.ArrayList;

import model.Cinema;
import model.Movie;
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

  public void addScreening(Movie movie, Cinema cinema, String dateTime) {
    screeningsArr.add(new Screening(movie, cinema, dateTime));
  }

  /**
   * 
   * Update the showtime of a screening
   * Need to call the setDateTime method on screening object
   * @param screening
   * @param newDateTime
   */
  public void updateShowtime(Screening screening, String newShowTime) {
    screening.setShowTime(newShowTime);
  }

  public void deleteScreening(Screening screening, BookingManager bManager) {
    bManager.deleteBooking(screening);
    this.screeningsArr.removeIf(s -> s.equals(screening));
  }

  public ArrayList<Screening> getScreenings(String movieTitle, String cinemaCode, String date) {
    // TODO: Fix this warning
    ArrayList<Screening> screenings = (ArrayList<Screening>) screeningsArr.clone();

    screenings.removeIf(screening -> (((!screening.getMovieTitle().equals(movieTitle)) && (movieTitle != null)) ||
                                      ((!screening.getCinemaId().equals(cinemaCode)) && (cinemaCode != null)) ||
                                      ((!screening.getShowtime().equals(date)) && (date != null))
                                      ));

    // TODO: Sort by cinema code and date
    return screenings;
  }

  public ArrayList<Screening> getScreeningsByMovie(String movieTitle) {
    return this.getScreenings(movieTitle, null, null);
  }

  public ArrayList<Screening> getScreeningsByCinemaCode(String cinemaCode) {
    return this.getScreenings(null, cinemaCode, null);
  }

  public ArrayList<Screening> getScreeningsByDate(String date) {
    return this.getScreenings(null, null, date);
  }
}