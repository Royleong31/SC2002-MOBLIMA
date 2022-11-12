package view;
import controller.ScreeningManager;
import enums.Advisory;
import enums.CinemaType;
import enums.Genre;
import enums.MovieType;
import enums.SeatType;
import enums.ShowStatus;
import enums.SortCriteria;
import enums.TicketType;
import controller.SystemManager;
import model.Movie;
import model.Screening;
import model.Account.Account;
import controller.MovieManager;
import controller.ReviewManager;
import controller.BookingManager;
import controller.CineplexManager;
import controller.LoginManager;
import utils.DataUtils;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

/**
 * All methods here do not require authentication
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
 public abstract class ParentConsole implements Serializable{
  private final static Scanner scannerObj = new Scanner(System.in);

  private final LoginManager loginManager;
  /**
   * Handles state and methods related to bookings
   */
  private final BookingManager bookingManager;

  /**
   * Handles state and methods related to cineplexes
   */
  private final CineplexManager cineplexManager;

  /**
   * Handles state and methods related to movies
   */
  private final MovieManager movieManager;

  /**
   * Handles state and methods related to reviews
   */
  private final ReviewManager reviewManager;

  /**
   * Handles state and methods related to screenings
   */
  private final ScreeningManager screeningManager;

  /**
   * Handles state and methods related to system configurations
   */
  private final SystemManager systemManager;

  ParentConsole(LoginManager lm, BookingManager bm, CineplexManager cm, MovieManager mm, ReviewManager rm, ScreeningManager sm, SystemManager sysm) {
    this.loginManager = lm;
    this.bookingManager = bm;
    this.cineplexManager = cm;
    this.movieManager = mm;
    this.reviewManager = rm;
    this.screeningManager = sm;
    this.systemManager = sysm;
  }

  ParentConsole() {
    this.loginManager = null;
    this.bookingManager = null;
    this.cineplexManager = null;
    this.movieManager = null;
    this.reviewManager = null;
    this.screeningManager = null;
    this.systemManager = null;
  }

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
  protected void displayMovies(ArrayList<Movie> movies) {
    System.out.println("Movies");
    if (movies.size() == 0) {
      System.out.println("No movies found");
      return;
    }

    for (int i = 0; i < movies.size(); i++) {
      String rating = movies.get(i).getReviews().size() < 2 ? "NA" : Double.toString(Math.round(movies.get(i).getOverallRating() * 10.0) / 10.0);
      System.out.println(i+1 + ": " + movies.get(i).getTitle() + " Rating: " + rating);
    }
  }

  /**
   * Displays all movies in the system
   */
  protected void displayScreenings(ArrayList<Screening> screenings) {
    if (screenings.size() == 0) {
      System.out.println("No screenings found");
      return;
    }

    for (int i = 0; i < screenings.size(); i++) {
      Screening screening = screenings.get(i);
      System.out.println(i+1 + ": Time: " + screening.getShowtime() + " Cinema Code: " + screening.getCinema().getId());
    }
  }

  /**
   * Helper method that can be used for child classes to get movies to do other things like submitting reviews
   * Displays all movies in the system
   * Allows the user to pick a movie
   * @return the movie that the user picked
   */
  protected Movie getMovie(SortCriteria sortCriterias, ArrayList<ShowStatus> showStatuses) throws Exception {
    ArrayList<Movie> movies = this.movieManager.getMovies(sortCriterias, showStatuses);
    if (movies.size() == 0) {
      throw new Exception("No movies found");
    }
    this.displayMovies(movies);
    Integer userChoice = this.getUserChoiceFromCount("Choose a movie", movies.size());

    // Convert back to 0 index
    return movies.get(userChoice - 1);
  }
  
  /**
   * Helper method that returns all the screenings for a movie that are currently showing 
   * Can be overriden in AdminConsole to show all screenings including those that are not currently showing
   * @param movie the movie that the user wants to select a screening from
   * @return the screening that the user picked
   */
  protected Screening getScreening(Movie movie) throws Exception {
    // Currently only gets all screenings for 1 movie
    ArrayList<Screening> screenings = this.screeningManager.getScreeningsByMovie(movie.getTitle());
    
    if (screenings.size() == 0) 
    throw new Exception("No screenings found for movie " + movie.getTitle());
    
    System.out.println("Screenings for " + movie.getTitle());
    this.displayScreenings(screenings);

    Integer userChoice = this.getUserChoiceFromCount("Choose a screening", screenings.size());
    return screenings.get(userChoice - 1);
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

  protected Integer getUserChoiceFromCount(String message, int count) {
    ArrayList<String> validInputs = new ArrayList<String>();
    for (int i=1; i<count+1; i++) {
      validInputs.add(Integer.toString(i));
    }
    // no need error checking as only integers are accepted as they were added above
    return Integer.parseInt(this.getUserChoice(message, validInputs));
  }
  
  protected String getUserInput(String message) {
    System.out.println(message);
    String userInput = scannerObj.nextLine();
    System.out.println("");
    return userInput;
  }
  
  protected Integer getUserIntegerInput(String message) {
    while (true) {
      try { 
        String userInput = this.getUserInput(message);
        return Integer.parseInt(userInput);
      } catch(NumberFormatException e) {
        System.out.println("Invalid input. Please try again");
      }
    }
  }

  protected Integer getSelectInput(ArrayList<String> options, String message) {
    System.out.println(message);
    for (int i=1; i<=options.size(); i++) {
      System.out.println("Enter " + i + " to " + options.get(i-1));
    }
    return getUserChoiceFromCount("", options.size());
  }

  protected Genre selectGenre() {
    ArrayList<String> options = new ArrayList<String>();
    for (Genre cur : Genre.values()) {
      options.add(cur.toString());
    }
    Integer userChoice = getSelectInput(options, "Select a genre");
    return Genre.values()[userChoice-1];
  }

  protected Advisory selectAdvisory() {
    ArrayList<String> options = new ArrayList<String>();
    for (Advisory cur : Advisory.values()) {
      options.add(cur.toString());
    }
    Integer userChoice = getSelectInput(options, "Select an advisory rating");
    return Advisory.values()[userChoice-1];
  }

  protected ShowStatus selectShowStatus() {
    ArrayList<String> options = new ArrayList<String>();
    for (ShowStatus cur : ShowStatus.values()) {
      options.add(cur.toString());
    }
    Integer userChoice = getSelectInput(options, "Select a show status");
    return ShowStatus.values()[userChoice-1];
  }

  protected MovieType selectMovieType() {
    ArrayList<String> options = new ArrayList<String>();
    for (MovieType cur : MovieType.values()) {
      options.add(cur.toString());
    }
    Integer userChoice = getSelectInput(options, "Select a movie type");
    return MovieType.values()[userChoice-1];
  }

  protected CinemaType selectCinemaType() {
    ArrayList<String> options = new ArrayList<String>();
    for (CinemaType cur : CinemaType.values()) {
      options.add(cur.toString());
    }
    Integer userChoice = getSelectInput(options, "Select a cinema type");
    return CinemaType.values()[userChoice-1];
  }

  protected SeatType selectSeatType() {
    ArrayList<String> options = new ArrayList<String>();
    for (SeatType cur : SeatType.values()) {
      options.add(cur.toString());
    }
    Integer userChoice = getSelectInput(options, "Select a seat type");
    return SeatType.values()[userChoice-1];
  }

  protected TicketType selectTicketType() {
    ArrayList<String> options = new ArrayList<String>();
    for (TicketType cur : TicketType.values()) {
      options.add(cur.toString());
    }
    Integer userChoice = getSelectInput(options, "Select a ticket type");
    return TicketType.values()[userChoice-1];
  }

  protected ArrayList<String> getCastMembers() {
    ArrayList<String> castArr = new ArrayList<String>();
    Integer castCount = this.getUserIntegerInput("Enter the number of cast members"); 
    for (int i=1; i<=castCount; i++) {
      String cast = this.getUserInput("Enter the cast member " + i + "'s name: ");
      castArr.add(cast);
    }

    return castArr;
  }
  
  protected Float getUserFloatInput(String message) {
    while (true) {
      try { 
        String userInput = this.getUserInput(message);
        return Float.parseFloat(userInput);
      } catch(NumberFormatException e) {
        System.out.println("Invalid input. Please try again");
      }
    }
  }

  // TODO:
  protected void startProgram() {
  }

  /**
   * This is the function that is called whenever the program exits, for e.g. when the user chooses to quit the program
   */
  protected void exitProgram() {
    // TODO: save all state into storage
    return;

    // ParentConsole.scannerObj.close();
    // System.exit(0);
  }

  protected BookingManager getBookingManager() {
    return this.bookingManager;
  }

  protected void logout() {
    this.loginManager.logout();
  }

  /**
   * Displays the content for each console.
   * This is basically the 'main' method for each console
   */
  public abstract boolean display(Account account);
}
