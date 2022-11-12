package utils;
import java.util.ArrayList;

import model.Booking;
import model.Ticket;

/**
 * Contains static functions to get total sales, sales for a movie and sales by a cineplex
 * @author Augustine Lee, Roy Leong
 * @version 1.0
 * @since 2022-11-02
 */
public class SalesUtils {
  /**
   * Get combined total sales for all cineplexes
   * @param bookingsArr the arraylist of all bookings
   * @return the combined total sales for all cineplexes
   */
  public static float getTotalSales(ArrayList<Booking> bookingsArr) {
    float totalSales = 0;
    for (Booking booking : bookingsArr) {
      totalSales = totalSales + booking.getAmountPaid();
    }
    return totalSales; /* returns 0 if there is no bookings so no need for exception handling */
  }

  /**
   * Get total sales of a movie
   * @param bookingsArr the arraylist of all bookings
   * @param movieTitle the selected movie title
   * @return the total sales of the movie
   */
  public static float getSalesByMovie(ArrayList<Booking> bookingsArr, String movieTitle) {
    float totalSales = 0;
    for (Booking booking : bookingsArr) {
      /*
      * Assumptions:
      * 1. All tickets for each booking have same screening
      * 2. There is at least one ticket for each booking
      */
      Ticket ticket = booking.getTickets().get(0); /* get first ticket object */
      String ticketMovieTitle = ticket.getScreening().getMovie().getTitle();
      if (ticketMovieTitle.equals(movieTitle)) {
        totalSales += booking.getAmountPaid();
      }
    }
    return totalSales; /* returns 0 if there is no bookings so no need for exception handling */
  }

  /**
   * Get total sales for a particular cineplex
   * @param bookingsArr the arraylist of all bookings
   * @param cineplexLocation the selected cineplex location
   * @return the total sales for the cineplex
   */
  public static float getSalesByCineplex(ArrayList<Booking> bookingsArr, String cineplexLocation) {
    float totalSales = 0;

    for (Booking booking : bookingsArr) {
      Ticket ticket = booking.getTickets().get(0);
      String curCineplexLocation = ticket.getScreening().getCinema().getCineplex().getLocation();

      if (curCineplexLocation.equals(cineplexLocation)) {
        totalSales += booking.getAmountPaid();
      }
    }

    return totalSales; /* returns 0 if there is no bookings so no need for exception handling */
  }
}