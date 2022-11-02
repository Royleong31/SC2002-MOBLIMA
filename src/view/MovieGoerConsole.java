package view;

import java.util.ArrayList;

import controller.ReviewManager;
import model.Booking;
import model.Seat;
import model.Account.Account;
import model.Account.MovieGoerAccount;
import utils.Utils;

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
  private final ReviewManager reviewManager = new ReviewManager();

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

  @Override
  public void display(Account account) {
    String userSelection = this.getUserChoice("Enter '1' to submit review, \n'2' to make booking, \n'3' to view booking history, \n'4' to quit", Utils.asArrayList("1", "2", "3", "4"));
    if (account instanceof MovieGoerAccount) {
      throw new Exception("Account is not a MovieGoerAccount");
    }
    MovieGoerAccount movieGoerAccount = (MovieGoerAccount) account;

    switch (userSelection) {
      case "1":
        this.submitReview(movieGoerAccount);
        break;
    
      case "2":
        this.makeBooking(movieGoerAccount);
        break;
    
      case "3":
        this.viewBookingHistory(movieGoerAccount);
        break;
    
      case "4":
        this.exitProgram();
        break;
    
      default:
        // Should never reach here as error checking is done in this.getUserChoice()
        throw new Error("An unexpected error occured");
    }
  }
}
