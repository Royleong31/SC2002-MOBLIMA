package view;
import controller.ScreeningManager;
import enums.CinemaType;
import enums.SeatType;
import enums.ShowStatus;
import enums.SortCriteria;
import controller.SystemManager;
import model.Movie;
import model.Screening;
import model.Account.Account;
import controller.MovieManager;
import controller.ReviewManager;
import controller.BookingManager;
import controller.CineplexManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import constants.Constants;

/**
 * All methods here do not require authentication
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public abstract class ParentConsole {
  /**
   * Handles state and methods related to bookings
   */
  private BookingManager bookingManager = new BookingManager();

  /**
   * Handles state and methods related to cineplexes
   */
  private CineplexManager cineplexManager = new CineplexManager();

  /**
   * Handles state and methods related to movies
   */
  private MovieManager movieManager = new MovieManager();

  /**
   * Handles state and methods related to reviews
   */
  private final ReviewManager reviewManager = new ReviewManager();

  /**
   * Handles state and methods related to screenings
   */
  private ScreeningManager screeningManager = new ScreeningManager();



  /**
   * Handles state and methods related to system configurations
   */
  private SystemManager systemManager = new SystemManager();

  // TODO: Authorisation check? So that only admins can access admin only stuff
  protected CineplexManager getCineplexManager() {
    return this.cineplexManager;
  }

  protected MovieManager getMovieManager() {
    return this.movieManager;
  }

  protected ReviewManager getReviewManager() {
    return this.reviewManager;
  }

  protected ScreeningManager getScreeningManager() {
    return this.screeningManager;
  }

  protected SystemManager getSystemManager() {
    return this.systemManager;
  }


  /**
   * Displays all movies in the system
   */
  public void displayMovies(ArrayList<Movie> movies) {
    System.out.println("Movies");

    for (int i = 0; i < movies.size(); i++) {
      System.out.println(i + ": " + movies.get(i).getTitle());
    }
  }

  /**
   * Displays all movies in the system
   */
  public void displayScreenings(ArrayList<Screening> screenings) {
    for (int i = 0; i < screenings.size(); i++) {
      Screening screening = screenings.get(i);
      System.out.println(i + ": Time: " + screening.getShowtime() + "\n Cinema Code: " + screening.getCinema().getId());
    }
  }

  /**
   * Helper method that can be used for child classes to get movies to do other things like submitting reviews
   * Displays all movies in the system
   * Allows the user to pick a movie
   * @return the movie that the user picked
   */
  // TODO: Add filtering and sorting criteria here so that it can be used by both MovieGoer and Admins
  // TODO: Add overloads for this matching the MovieManager getMovies function
  public Movie getMovie(SortCriteria sortCriterias, ArrayList<ShowStatus> showStatuses) {
    ArrayList<Movie> movies = this.movieManager.getMovies(sortCriterias, showStatuses);
    this.displayMovies(movies);
    String userChoice = this.getUserChoiceFromCount("Choose a movie", movies.size());

    // TODO: Handle exceptions when the index is out of range (should never happen as we are using the size of the array)
    return movies.get(Integer.parseInt(userChoice));
  }
  
  /**
   * Helper method that returns all the screenings for a movie that are currently showing 
   * Can be overriden in AdminConsole to show all screenings including those that are not currently showing
   * @param movie the movie that the user wants to select a screening from
   * @return the screening that the user picked
   */
  // TODO: Add overloaded functions for filtering by cinema code and date
  public Screening getScreening(Movie movie) {
    System.out.println("Screenings for " + movie.getTitle());
    // Currently only gets all screenings for 1 movie
    ArrayList<Screening> screenings = this.screeningManager.getScreeningsByMovie(movie.getTitle());
    this.displayScreenings(screenings);

    String userChoice = this.getUserChoiceFromCount("Choose a screening", screenings.size());
    // TODO: Handle exceptions when the index is out of range (should never happen as we are using the size of the array)
    return screenings.get(Integer.parseInt(userChoice));
  }

  /**
   * This is to get the user's choice, for e.g. when the user is selecting whether to login or register
   * validInputs should contain only lowercase values
   * Case checking is done inside here
   * @param message
   * @param validInputs valid inputs that the user can enter(all lowercase), no repeated values
   * @return lowercased user input
   */
  protected String getUserChoice(String message, ArrayList<String> validInputs) {
    while (true) {
      String userInput = this.getUserInput(message).toLowerCase();
      if (validInputs.contains(userInput)) {
        return userInput;
      } else {
        System.out.println("Invalid input. Please try again");
      }
    }
  }

  protected String getUserChoiceFromCount(String message, int count) {
    ArrayList<String> validInputs = new ArrayList<String>();
    for (int i=1; i<count+1; i++) {
      validInputs.add(Integer.toString(i));
    }
    return this.getUserChoice(message, validInputs);
  }
  
  protected String getUserInput(String message) {
    Scanner scannerObj = new Scanner(System.in);
    System.out.println(message);
    String userInput = scannerObj.nextLine();
    scannerObj.close();
    return userInput;
  }
  
  protected Integer getUserIntegerInput(String message) {
    Scanner scannerObj = new Scanner(System.in);
    System.out.println(message);
    Integer userInput = scannerObj.nextInt();
    scannerObj.close();
    return userInput;
  }

  /**
   * This is the function that is called whenever the program exits, for e.g. when the user chooses to quit the program
   */
  protected void exitProgram() {
    // TODO: save all state into storage
    System.exit(0);
  }

  protected BookingManager getBookingManager() {
    return this.bookingManager;
  }

  /**
   * Displays the content for each console.
   * This is basically the 'main' method for each console
   */
  public abstract void display(Account account);
}
