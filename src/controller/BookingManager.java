package controller;

import java.util.ArrayList;

import model.Booking;
import model.Screening;
import model.Seat;
import model.Ticket;
import model.Account.MovieGoerAccount;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class BookingManager {
  private ArrayList<Booking> bookingsArr = new ArrayList<Booking>();

  public void makeBooking(MovieGoerAccount movieGoer, Screening screening, Seat seat, Ticket ticketType) {
  }

  public ArrayList<Seat> getAvailableSeats(Screening screening) {
  }

  // all movies
  public void displayMovies() {

  }

  public boolean isSelectionValid(Screening screening, Seat seat) {
    return false;
  }

  public ArrayList<Booking> getBookings() {
    return bookingsArr;
  }

  public ArrayList<Booking> getUserBookings(MovieGoerAccount movieGoer) {
  }
}