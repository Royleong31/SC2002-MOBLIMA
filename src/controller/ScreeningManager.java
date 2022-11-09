package controller;
import java.util.ArrayList;
import java.util.Comparator;

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

  public Screening addScreening(Movie movie, Cinema cinema, String dateTime) {
    Screening screening = new Screening(movie, cinema, dateTime);
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
  public Screening updateShowtime(Screening screening, String newShowTime) {
    screening.setShowTime(newShowTime);
    return screening;
  }

  public void deleteScreening(Screening screening, BookingManager bManager) {
    bManager.deleteBooking(screening);
    this.screeningsArr.removeIf(s -> s.equals(screening));
  }

  public ArrayList<Screening> getScreenings(String movieTitle, String cinemaCode, String date) {
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
        return s1.getDateTimeObj().compareTo(s2.getDateTimeObj());
      } catch (Exception e) {
        e.printStackTrace();
      }
      return 0;
    };
    
    comparator.thenComparing(Screening::getCinemaId).thenComparing(Screening::getMovieTitle);
    screenings.sort(comparator);
    
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