package controller;

import java.util.ArrayList;

import utils.SalesUtils;
import model.Booking;

/**
 * Sort the bookings
 * This class should only contain static methods
 */
public class SalesManager {

  public static float getTotalSales(ArrayList<Booking> bookingsArr) {
    return SalesUtils.getTotalSales(bookingsArr);
  }

  public static float getSalesByMovie(ArrayList<Booking> bookingsArr, String movieTitle) {
    return SalesUtils.getSalesByMovie(bookingsArr, movieTitle);
  }

  public static float getSalesByCineplex(ArrayList<Booking> bookingsArr, String cinemaCode) {
    return SalesUtils.getSalesByCineplex(bookingsArr, cinemaCode);
  }
}