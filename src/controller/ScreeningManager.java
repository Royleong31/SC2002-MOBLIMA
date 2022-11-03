package controller;
import java.util.ArrayList;

import model.DateTime;
import model.Screening;
import model.Cinema.Cinema;

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

  /*
   * Needs to cascade and delete all tickets related to the screening
   * Do we need this? It's not listed in the brief
   */
  public void deleteScreening(Screening screening) {
    BookingManager bManager = new BookingManager();
    bManager.deleteTicketsFromBookings(screening);
  }

  // TODO: Overload this so that it works with diff optional params (Can we pass in the movie object
  // cinema object and date object instead of strings to overload)
  public ArrayList<Screening> getScreenings(String movieTitle, String cinemaCode, String date) {
    ArrayList<Screening> screenings = new ArrayList<Screening>();

    for (Screening screening : screeningsArr) {
      if (screening.getMovieTitle().equals(movieTitle) && screening.getCinemaId().equals(cinemaCode) && screening.getDateTime().equals(date)) {
        screenings.add(screening);
      }
    }

    return screenings;
  }

  /**
   * Use the cinema type and the system manager stuff to get the price
   * Use PriceUtils to calculate the price from the cinema type and date time
   * @param systemManager
   * @param screening
   * @return
   */
  public float getPrice(SystemManager systemManager, Screening screening) {
    // WIP
    /*Cinema cinema = screening.getCinema();
    cinema.*/
  }
}