package view;

import java.util.ArrayList;

import controller.ReviewManager;
import model.Booking;
import model.Seat;
import model.Account.MovieGoerAccount;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class MovieGoerConsole extends ParentConsole {
  /**
   * Handles state and methods related to reviews
   */
  private ReviewManager reviewManager = new ReviewManager();

  /**
   * Displays all reviews that have been made by this user
   * @param movieGoerAccount
   */
  public void submitReview(MovieGoerAccount movieGoerAccount) {
    // get the movie from super.getMovie()
    // get the user's review and rating and creates a review
  }

  /**
   * Allows the user to submit a review for a movie
   * Uses super.getMovie() to get the movie that the user wants to review
   * Uses selectSeats() to get the seats that the user wants to review
   * which is then passed to BookingManager to make the booking
   * @param movieGoerAccount
   */
  public void makeBooking(MovieGoerAccount movieGoerAccount) {}

  /**
   * Allows the user to select seats for a booking
   * uses super.getScreening() to get the screening that the user wants to book
   */
  private ArrayList<Seat> selectSeats(MovieGoerAccount movieGoerAccount) {
    return null;
  }

  /**
   * Displays all bookings that have been made by this user
   * @param movieGoerAccount
   */
  public void viewBookingHistory(MovieGoerAccount movieGoerAccount) {

  }
}
