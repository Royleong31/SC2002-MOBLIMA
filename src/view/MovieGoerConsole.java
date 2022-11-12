package view;

import java.util.ArrayList;

import controller.BookingManager;
import controller.CineplexManager;
import controller.LoginManager;
import controller.MovieManager;
import controller.ReviewManager;
import controller.ScreeningManager;
import controller.SystemManager;
import enums.Advisory;
import enums.CinemaType;
import enums.Genre;
import enums.MovieType;
import enums.ShowStatus;
import enums.SortCriteria;
import enums.TicketType;
import model.Booking;
import model.Cinema;
import model.Cineplex;
import model.Movie;
import model.Review;
import model.Screening;
import model.Seat;
import model.SeatingPlan;
import model.Ticket;
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
  public MovieGoerConsole(LoginManager lm, BookingManager bm, CineplexManager cm, MovieManager mm, ReviewManager rm, ScreeningManager sm, SystemManager sysm) {
    super(lm, bm, cm, mm, rm, sm, sysm);
  }

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
      Movie movie = super.getMovie(SortCriteria.TITLE, this.allowedShowStatus);
      String comments = super.getUserInput("Please enter your comments: ");
      // TODO: Add validation
      Float rating = super.getUserFloatInput("Please enter your rating (1-5): ");
      super.getReviewManager().addReview(movie, comments, rating, movieGoerAccount);
      System.out.println("Review submitted successfully!");
    } catch (Exception e) {
      System.out.println("Something went wrong while submitting your review");
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
      TicketType ticketType = super.selectTicketType();

      super.getBookingManager().makeBooking(movieGoerAccount, screening, seats, ticketType, super.getSystemManager());
      // inform price
    } catch (Exception e) {
      System.out.println("Something went wrong while booking your tickets");
      System.out.println(e.getMessage());
    }
  }

  /**
   * Allows the user to select seats for a booking
   * uses super.getScreening() to get the screening that the user wants to book
   */
  // !: Calculating the aisle is complicated because of the remainder of the division of rolumns by aisle, so this is just an approximation
  private ArrayList<Seat> selectSeats(Screening screening) {
    SeatingPlan seatingPlan = screening.getCinema().getSeatingPlan();
    int columns = seatingPlan.getColumns();
    int aisle = seatingPlan.getAisle();
    int divs = aisle > 0 ? Math.floorDiv(columns, aisle+1) : columns;
    System.out.println(divs);

    int aisleCount= 0;
    System.out.print("  ");
    for (int i=1; i<=columns; i++) {
        System.out.print(((aisle > 0 && i > 1 && (i-1) % divs == 0 && aisleCount++ < aisle) ? "      " : " ") + (i<10 ? " " : "") + Integer.toString(i) + "  ");
    }
    
    int aisleForThisRowCount = 0;
    for (int i=0; i<screening.getSeats().size(); i++) {
      Seat seat = screening.getSeats().get(i);
      if (seat.getColumn() == 1) {
        System.out.print("\n" + this.getCharForNumber(seat.getRow()) + " ");
      }
      
      if (seat.getColumn() > 1 && (seat.getColumn()-1) % divs == 0 && aisleForThisRowCount < aisle) {
        System.out.print("  |  ");
        aisleForThisRowCount++;
      } 

      if (i > 1 && seat.getRow() != screening.getSeats().get(i-1).getRow()) {
        aisleForThisRowCount = 0;
      }

      if (seat.isTaken()) 
        System.out.print(" [X] ");
      else
        System.out.print(" [ ] ");
    }

    ArrayList<Seat> seats = new ArrayList<Seat>();
    System.out.println("\nEnter the seat IDs(for e.g. A5, B3) that you want to book (Enter 'done' when you are done): ");

    while (true) {
      // show seelcted seat IDs
      String seatId = super.getUserInput("Seat ID: ");
      if (seatId.equals("done")) break;
      try {
        Seat seat = screening.getSeatById(seatId);
        seats.add(seat);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

    return seats;
  }

  private void displayAllMovies() {
    ArrayList<Movie> movies = super.getMovieManager().getMovies(this.allowedShowStatus);
    super.displayMovies(movies);
  }

    private String getCharForNumber(int i) {
    return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
  }

  /**
   * Displays all bookings that have been made by this user
   * @param movieGoerAccount
   */
  private void viewBookingHistory(MovieGoerAccount movieGoerAccount) {
    try {
      ArrayList<Booking> bookings = super.getBookingManager().getBookingsByUser(movieGoerAccount);
      if (bookings.size() == 0) {
        System.out.println("You have no bookings");
        return;
      }
      for (Booking booking : bookings) {
        System.out.println("ID: " + booking.getId() + "Cinema ID: " + booking.getCinemaId() + " $" + Float.toString(Math.round(100 * booking.getAmountPaid())/100));
        System.out.println("Tickets: ");
        for (Ticket ticket : booking.getTickets()) {
          System.out.println(ticket.getSeat().getId());
        }
        System.out.println("\n");
      }
    } catch (Exception e) {
      System.out.println("Something went wrong while getting your booking history");
      System.out.println(e.getMessage());
    }
  }

  private void displayTopMovies() {
    ArrayList<Movie> movies = super.getMovieManager().getMovies(super.getSystemManager().getSortingCriteria(), this.allowedShowStatus);
    movies = movies.size() < 5 ? movies : new ArrayList<Movie>(movies.subList(0, 4));
    super.displayMovies(movies);
  }

  @Override
  public boolean display(Account account) {
    // should never trigger as it can only reach MovieGoerConsole if the logged in user is a MovieGoerAccount
    if (!(account instanceof MovieGoerAccount)) {
      System.out.println("Something went wrong in the login process");
      this.exitProgram();
    }
    
    Integer userSelection = super.getSelectInput(Utils.asArrayList("to submit review", 
                                                                    "to make booking", 
                                                                    "to view booking history", 
                                                                    "to display all available movies", 
                                                                    "to display top movies", 
                                                                    "to logout",
                                                                    "to quit program"), "Enter your choice: ");

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
        super.logout();
        break;
    
      case 7:
        BookingManager bkm = super.getBookingManager();
        if(bkm != null){
          System.out.println("this NOT NULL");
        } else {
          System.out.println("this is NULL");
        }
        this.exitProgram();
        return true;
    
      default:
        // Should never reach here as error checking is done in this.getUserChoice()
        System.out.println("An unexpected error occured");
        this.exitProgram();
        return true;
    }
    return false;
  }
}

/** TODO:
 * DateTime
 * Reviews average rating
 */