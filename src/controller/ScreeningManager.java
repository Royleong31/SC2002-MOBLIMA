package controller;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.Serializable;

import enums.ShowStatus;
import model.Cinema;
import model.Movie;
import model.Screening;
import model.DateTime;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class ScreeningManager implements Serializable{
  private ArrayList<Screening> screeningsArr = new ArrayList<Screening>();

  public Screening addScreening(Movie movie, Cinema cinema, int year, int month, int day, int hour, int minute) throws Exception {
    DateTime date = new DateTime(year, month, day, hour, minute);
    Screening screening = new Screening(movie, cinema, date);

    for (Screening cur: this.screeningsArr) {
      if (cur.equals(screening)) {
        throw new Exception("Screening already exists.");
      }
    }
    
    if (movie.getShowingStatus().equals(ShowStatus.END_OF_SHOWING)) {
      System.out.println("Movie is not showing");
      throw new Exception("Movie showing has ended");
    }
    screeningsArr.add(screening);
    return screening;
  }

  /**
   * 
   * Update the showtime of a screening
   * Need to call the setDateTime method on screening object
   * @param screening
   * @param newDateTime
   */
  public Screening updateShowtime(Screening screening, int year, int month, int day, int hour, int minute) {
    DateTime date = new DateTime(year, month, day, hour, minute);
    screening.setShowTime(date);
    return screening;
  }

  public void deleteScreening(Screening screening, BookingManager bManager) {
    bManager.deleteBooking(screening);
    this.screeningsArr.removeIf(s -> s.equals(screening));
  }

  public ArrayList<Screening> getScreenings(String movieTitle, String cinemaCode, DateTime date) {
    ArrayList<Screening> screenings = new ArrayList<Screening>();
    for (Screening screening : screeningsArr) {
      screenings.add(screening);
    }

    screenings.removeIf(screening -> (((!screening.getMovieTitle().equals(movieTitle)) && (movieTitle != null)) ||
                                      ((!screening.getCinemaId().equals(cinemaCode)) && (cinemaCode != null)) ||
                                      ((!screening.getShowtime().equals(date)) && (date != null))
                                      ));

    Comparator<Screening> comparator = (s1, s2) -> {
      try {
        return s1.getShowtime().getDateTimeObj().compareTo(s2.getShowtime().getDateTimeObj());
      } catch (Exception e) {
        e.printStackTrace();
      }
      return 0;
    };
    
    comparator.thenComparing(Screening::getCinemaId).thenComparing(Screening::getMovieTitle);
    screenings.sort(comparator);
    
    return screenings;
  }

  public ArrayList<Screening> getScreenings() {
    return screeningsArr;
  }

  public ArrayList<Screening> getScreeningsByMovie(String movieTitle) {
    return this.getScreenings(movieTitle, null, null);
  }

  public ArrayList<Screening> getScreeningsByCinemaCode(String cinemaCode) {
    return this.getScreenings(null, cinemaCode, null);
  }

  public ArrayList<Screening> getScreeningsByDate(DateTime date) {
    return this.getScreenings(null, null, date);
  }

  public void setScreenings(ArrayList<Screening> screeningsArr) {
    this.screeningsArr = new ArrayList<Screening>(screeningsArr);
  }

}