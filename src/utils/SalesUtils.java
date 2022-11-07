package utils;
import java.util.ArrayList;

import model.Booking;
import model.Ticket;

/**
 * Sort the bookings
 * This class should only contain static methods
 */
public class SalesUtils {

  public static float getTotalSales(ArrayList<Booking> bookingsArr) {
    float totalSales = 0;
    for (Booking booking : bookingsArr) {
      totalSales = totalSales + booking.getAmountPaid();
    }
    return totalSales; /* returns 0 if there is no bookings so no need for exception handling */
  }

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
      if (ticketMovieTitle == movieTitle) {
        totalSales += booking.getAmountPaid();
      }
    }
    return totalSales; /* returns 0 if there is no bookings so no need for exception handling */
  }

  public static float getSalesByCineplex(ArrayList<Booking> bookingsArr, String cineplexLocation) {
    float totalSales = 0;

    for (Booking booking : bookingsArr) {
      Ticket ticket = booking.getTickets().get(0);
      String curCineplexLocation = ticket.getScreening().getCinema().getCineplex().getLocation();

      if (curCineplexLocation == cineplexLocation) {
        totalSales += booking.getAmountPaid();
      }
    }

    return totalSales; /* returns 0 if there is no bookings so no need for exception handling */
  }
}