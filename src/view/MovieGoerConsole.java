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
public class MovieGoerConsole extends Console {
  private ReviewManager reviewManager = new ReviewManager();

  public void submitReview(MovieGoerAccount movieGoerAccount) {}

  public void makeBooking(MovieGoerAccount movieGoerAccount) {}

  private ArrayList<Seat> selectSeats() {
    return null;
  }

  public void showMyBookings() {

  }
}
