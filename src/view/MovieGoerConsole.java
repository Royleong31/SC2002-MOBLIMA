package view;

import java.util.ArrayList;
import java.util.Scanner;

import enums.ShowStatus;
import enums.SortCriteria;
import enums.TicketType;
import model.Booking;
import model.Movie;
import model.Review;
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
class MovieGoerConsole extends ParentConsole {
  /**
   * Displays all reviews that have been made by this user
   * @param movieGoerAccount
   */
  private void submitReview(MovieGoerAccount movieGoerAccount) {
    // get the movie from super.getMovie()
    // get the user's review and rating and creates a review
    try {
      Movie movie = super.getMovie(SortCriteria.TITLE, null);

      Scanner scannerObj = new Scanner(System.in);
      System.out.println("Please enter your comments: ");
      String comments = scannerObj.nextLine();
      System.out.println("Please enter your rating (1-5): ");
      float rating = scannerObj.nextFloat();
      scannerObj.close();

      Review review = new Review(movie, comments, rating, movieGoerAccount);
      super.getReviewManager().addReview(review);
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
      Movie movie = super.getMovie(SortCriteria.TITLE, Utils.asArrayList(ShowStatus.NOW_SHOWING, ShowStatus.PREVIEW));
      Screening screening = super.getScreening(movie);
      ArrayList<Seat> seats = this.selectSeats(screening);
      // TODO: Add variable ticket types
      String userChoice = super.getUserChoiceFromCount("Choose a ticket type\n ", TicketType.values().length);
      TicketType ticketType = TicketType.values()[Integer.parseInt(userChoice)];

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
    // TODO: Dislay available seats. Should be [] if available, [•] if booked
    for (int i=0; i<screening.getSeats().size(); i++) {
      System.out.println("Seat Number: " + screening.getSeats().get(i).toString());
    }

    ArrayList<Seat> seats = new ArrayList<Seat>();
    System.out.println("Enter the seat IDs that you want to book (Enter 'done' when you are done): ");

    while (true) {
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
    ArrayList<Movie> movies = super.getMovieManager().getMovies(Utils.asArrayList(ShowStatus.PREVIEW, ShowStatus.NOW_SHOWING));
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
    ArrayList<Movie> movies = super.getMovieManager().getMovies(super.getSystemManager().getSortingCriteria()).subList(0, 4); // get top 5 movies
    super.displayMovies(movies);
  }

  @Override
  private void display(Account account) {
    // should never trigger as it can only reach MovieGoerConsole if the logged in user is a MovieGoerAccount
    if (!(account instanceof MovieGoerAccount)) {
      System.out.println("Something went wrong in the login process");
      this.exitProgram();
    }
    
    String userSelection = this.getUserChoiceFromCount("Enter '1' to submit review, \n'2' to make booking, \n'3' to view booking history, \n'4' to display all available movies, \n'5' to quit", 5);

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
        this.displayAllMovies();
        break;
    
      case "5":
        this.exitProgram();
        break;
    
      default:
        // Should never reach here as error checking is done in this.getUserChoice()
        throw new Error("An unexpected error occured");
    }
  }
}
