package view;

import java.util.ArrayList;
import enums.ShowStatus;
import enums.SortCriteria;
import enums.TicketType;
import model.Booking;
import model.Movie;
import model.Screening;
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
  // show statuses that the movie goer can view
  private final ArrayList<ShowStatus> allowedShowStatus = Utils.asArrayList(ShowStatus.PREVIEW, ShowStatus.NOW_SHOWING);

  /**
   * Displays all reviews that have been made by this user
   * @param movieGoerAccount
   */
  private void submitReview(MovieGoerAccount movieGoerAccount) {
    // get the movie from super.getMovie()
    // get the user's review and rating and creates a review
    try {
      Movie movie = super.getMovie(SortCriteria.TITLE, null);
      String comments = super.getUserInput("Please enter your comments: ");
      // TODO: Add validation
      Float rating = super.getUserFloatInput("Please enter your rating (1-5): ");
      super.getReviewManager().addReview(movie, comments, rating, movieGoerAccount);
    } catch (Exception e) {
      System.out.println("Something went wrong while booking your tickets");
      System.out.println(e.getMessage());
    }
  }

  /**
   * Allows the user to submit a review for a movie
   * Uses super.getMovie() to get the movie that the user wants to review
   * Uses selectSeats() to get the seats that the user wants to review
   * which is then passed to BookingManager to make the booking
   * @param movieGoerAccount
   */
  private void makeBooking(MovieGoerAccount movieGoerAccount) {
    try {
      Movie movie = super.getMovie(SortCriteria.TITLE, this.allowedShowStatus);
      Screening screening = super.getScreening(movie);
      ArrayList<Seat> seats = this.selectSeats(screening);
      Integer userChoice = super.getUserChoiceFromCount("Choose a ticket type: " + 
                  "\n '1' for Normal, " +
                  "\n '2' for Student, " +
                  "\n '3' for Senior, " +
                  "\n '4' for Cards", TicketType.values().length);
      TicketType ticketType = TicketType.values()[userChoice - 1];

      super.getBookingManager().makeBooking(movieGoerAccount, screening, seats, ticketType, super.getSystemManager());
    } catch (Exception e) {
      System.out.println("Something went wrong while booking your tickets");
      System.out.println(e.getMessage());
    }
  }

  /**
   * Allows the user to select seats for a booking
   * uses super.getScreening() to get the screening that the user wants to book
   */
  private ArrayList<Seat> selectSeats(Screening screening) {
    // TODO: Display available seats. Should be [ ] if available, [•] if booked
    for (int i=0; i<screening.getSeats().size(); i++) {
      System.out.println("Seat Number: " + screening.getSeats().get(i).toString());
    }

    ArrayList<Seat> seats = new ArrayList<Seat>();
    System.out.println("Enter the seat IDs that you want to book (Enter 'done' when you are done): ");

    while (true) {
      // show seelcted seat IDs
      String seatId = super.getUserInput("Seat ID: ");
      if (seatId == "done") break;

      Seat seat = screening.getSeatFromId(seatId);
      if (seat == null) {
        System.out.println("Invalid seat number");
        continue;
      }
      seats.add(seat);
    }

    return seats;
  }

  private void displayAllMovies() {
    ArrayList<Movie> movies = super.getMovieManager().getMovies(this.allowedShowStatus);
    super.displayMovies(movies);
  }

  /**
   * Displays all bookings that have been made by this user
   * @param movieGoerAccount
   */
  private void viewBookingHistory(MovieGoerAccount movieGoerAccount) {
    try {
      ArrayList<Booking> bookings = super.getBookingManager().getBookingsByUser(movieGoerAccount);
      for (Booking booking : bookings) {
        System.out.println(booking);
      }
    } catch (Exception e) {
      System.out.println("Something went wrong while getting your booking history");
      System.out.println(e.getMessage());
    }
  }

  private void displayTopMovies() {
    ArrayList<Movie> movies = super.getMovieManager().getMovies(super.getSystemManager().getSortingCriteria(), this.allowedShowStatus);
    movies = new ArrayList<Movie>(movies.subList(0, 4));
    super.displayMovies(movies);
  }

  @Override
  public void display(Account account) {
    // should never trigger as it can only reach MovieGoerConsole if the logged in user is a MovieGoerAccount
    if (!(account instanceof MovieGoerAccount)) {
      System.out.println("Something went wrong in the login process");
      this.exitProgram();
    }
    
    Integer userSelection = this.getUserChoiceFromCount("Enter '1' to submit review, \n'2' to make booking, \n'3' to view booking history, \n'4' to display all available movies, \n'5' to display top movies, \n'6' to quit", 5);

    MovieGoerAccount movieGoerAccount = (MovieGoerAccount) account;

    switch (userSelection) {
      case 1:
        this.submitReview(movieGoerAccount);
        break;
    
      case 2:
        this.makeBooking(movieGoerAccount);
        break;
    
      case 3:
        this.viewBookingHistory(movieGoerAccount);
        break;

      case 4:
        this.displayAllMovies();
        break;

      case 5:
        this.displayTopMovies();
        break;
    
      case 6:
        this.exitProgram();
        break;
    
      default:
        // Should never reach here as error checking is done in this.getUserChoice()
        throw new Error("An unexpected error occured");
    }
  }
}
