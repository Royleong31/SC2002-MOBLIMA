package controller;

import java.util.ArrayList;

import model.Booking;
import model.Movie;
import model.Screening;
import model.Seat;
import enums.ShowStatus;
import enums.TicketType;
import model.Ticket;
import model.Account.MovieGoerAccount;

/**
 * Handles the booking of tickets for a movie screening.
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class BookingManager {
  private ArrayList<Booking> bookingsArr = new ArrayList<Booking>();

  /**
   * Creates a booking from the user's choice of screening and seats.
   * Multiple tickets can be created from a single booking.
   * @param movieGoer
   * @param screening
   * @param seat
   * @param ticketType
   */
  public void makeBooking(MovieGoerAccount movieGoer, Screening screening, ArrayList<Seat> seats, TicketType ticketType) {
  }

  /**
   * Returns all available seats for a given screening.
   * Used to get the seats that the user can choose from, which is then shown to the user in the console.
   * @param movieGoer
   * @return
   */
  public ArrayList<Seat> getAvailableSeats(Screening screening) {

  }

  /**
   * Returns true if a seat is not taken, false otherwise
   * @param screening
   * @param seat
   * @return whether the seat in a screening is available
   */
  public boolean isSeatAvailable(Screening screening, Seat seat) {
    return false;
  }


  /**
   * Returns all bookings that have been made
   * @return
   */
  public ArrayList<Booking> getBookings() {
    return bookingsArr;
  }

  /**
   * Returns all bookings that have been made by a movie goer
   * @param movieGoer
   * @return
   */
  public ArrayList<Booking> getBookingsByUser(MovieGoerAccount movieGoer) {
    // TODO: Filter by the movieGoer
    return bookingsArr;
  }

  /**
   * 
   * @param bookingId
   * @return the booking that matches this booking id
   */
  public Booking getBookingById(int bookingId) {
    
  }
}