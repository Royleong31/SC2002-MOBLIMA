package controller;

import java.util.ArrayList;
import java.util.Comparator;

import enums.ShowStatus;
import model.Cinema;
import model.Movie;
import model.Screening;
import model.DateTime;

/**
 * Screening Manager
 * Responsible for handling all addition/deletion/update/retrieval of movies
 *
 @author Roy Leong, Song Chen
 @version 1.1
 @since 2022-10-30
*/
public class ScreeningManager {
  /**
   * Collection of all screenings
   */
  private ArrayList<Screening> screeningsArr = new ArrayList<Screening>();

  /**
   * Add new screening
   * 
   * @param movie movie object
   * @param cinema cinema the screening will take place in
   * @param year year of screening
   * @param month month of screening
   * @param day day of screening
   * @param hour hour of screening
   * @param minute minute of screening
   * @return screening object that has been successfully added
   * @throws Exception if screening already exists or movie's showing status is end of showing
   */
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
   * Update the showtime of a screening
   * 
   * @param screening screening object to update
   * @param year year of screening
   * @param month month of screening
   * @param day day of screening
   * @param hour hour of screening
   * @param minute minute of screening
   * @return screening object that was updated
   */
  public Screening updateShowtime(Screening screening, int year, int month, int day, int hour, int minute) {
    DateTime date = new DateTime(year, month, day, hour, minute);
    screening.setShowTime(date);
    return screening;
  }

  /**
   * Deletes a screening from the arraylist of screenings in this object and all bookings associated with the screening
   * 
   * @param screening screening object to delete
   * @param bManager current booking manager object in charge of all bookings
   */
  public void deleteScreening(Screening screening, BookingManager bManager) {
    bManager.deleteBooking(screening);
    this.screeningsArr.removeIf(s -> s.equals(screening));
  }

  /**
   * Get an arraylist of screenings filtered by a specified movie title, cinema code and/or date
   * if a null value is provided for any of the aforementioned parameters, the method will not filter by that parameter
   * 
   * @param movieTitle movie title of screening
   * @param cinemaCode cinema code of screening
   * @param date date of the screening
   * @return filtered arraylist of screenings
   */
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

  /**
   * Get an arraylist of all screenings if no movie title, cinema code and date is provided
   * 
   * @return arraylist of all screenings
   * @see ScreeningManager#getScreenings(String, String, DateTime)
   */
  public ArrayList<Screening> getScreenings() {
    return screeningsArr;
  }

  /**
   * Get an arraylist of screenings based on the specified movie title
   * 
   * @param movieTitle movie title of screening
   * @return arraylist of screenings filtered by movie title
   * @see ScreeningManager#getScreenings(String, String, DateTime)
   */
  public ArrayList<Screening> getScreeningsByMovie(String movieTitle) {
    return this.getScreenings(movieTitle, null, null);
  }

  /**
   * Get an arraylist of screenings based on the specified cinema code
   * 
   * @param cinemaCode cinema code of screening
   * @return arraylist of screenings filtered by cinema code
   * @see ScreeningManager#getScreenings(String, String, DateTime)
   */
  public ArrayList<Screening> getScreeningsByCinemaCode(String cinemaCode) {
    return this.getScreenings(null, cinemaCode, null);
  }

  /**
   * Get an arraylist of screenings based on the specified date
   * 
   * @param date date of screening
   * @return arraylist of screenings filtered by date
   * @see ScreeningManager#getScreenings(String, String, DateTime)
   */
  public ArrayList<Screening> getScreeningsByDate(DateTime date) {
    return this.getScreenings(null, null, date);
  }
}