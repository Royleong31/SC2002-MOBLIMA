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
import utils.SalesUtils;
import controller.MovieManager;
import controller.ReviewManager;
import controller.BookingManager;
import controller.CineplexManager;
import controller.LoginManager;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * All methods here do not require authentication
 *
 @author Roy Leong, Augustine Lee
 @version 1.1
 @since 2022-10-30
*/
 public abstract class ParentConsole {
  private final static Scanner scannerObj = new Scanner(System.in);

  /**
   * Handles state and methods related to login
   */
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

  /**
   * Constructor for ParentConsole with parameters
   * @param lm
   * @param bm
   * @param cm
   * @param mm
   * @param rm
   * @param sm
   * @param sysm
   */
  ParentConsole(LoginManager lm, BookingManager bm, CineplexManager cm, MovieManager mm, ReviewManager rm, ScreeningManager sm, SystemManager sysm) {
    this.loginManager = lm;
    this.bookingManager = bm;
    this.cineplexManager = cm;
    this.movieManager = mm;
    this.reviewManager = rm;
    this.screeningManager = sm;
    this.systemManager = sysm;
  }

  /**
   * Constructor for ParentConsole without parameters
   */
  ParentConsole() {
    this.loginManager = null;
    this.bookingManager = null;
    this.cineplexManager = null;
    this.movieManager = null;
    this.reviewManager = null;
    this.screeningManager = null;
    this.systemManager = null;
  }

  /**
   * Retrieve cineplex manager
   * @return cineplex manager object
   */
  protected CineplexManager getCineplexManager() {
    return this.cineplexManager;
  }

  /**
   * Retrieve movie manager
   * @return movie manager object
   */
  protected MovieManager getMovieManager() {
    return this.movieManager;
  }

  /**
   * Retrieve review manager
   * @return review manager object
   */
  protected ReviewManager getReviewManager() {
    return this.reviewManager;
  }

  /**
   * Retrieve screening manager
   * @return screening manager object
   */
  protected ScreeningManager getScreeningManager() {
    return this.screeningManager;
  }

  /**
   * Retrieve system manager
   * @return system manager object
   */
  protected SystemManager getSystemManager() {
    return this.systemManager;
  }

  /**
   * Displays all movies in the system
   * @param movies
   */
  protected void displayMovies(ArrayList<Movie> movies) {
    System.out.println("Movies");
    if (movies.size() == 0) {
      System.out.println("No movies found");
      return;
    }

    for (int i = 0; i < movies.size(); i++) {
      String rating = movies.get(i).getReviews().size() < 2 ? "NA" : Double.toString(Math.round(movies.get(i).getOverallRating() * 10.0) / 10.0);
      //System.out.println(i+1 + ": " + movies.get(i).getTitle() + " Rating: " + rating + " Sales: " + SalesUtils.getSalesByMovie(this.getBookingManager().getBookings(), movies.get(i).getTitle()));
      System.out.println(i+1 + ": " + movies.get(i).getTitle() + " Rating: " + rating);
    }
  }

  /**
   * Displays all screenings for a movie
   * @param screenings
   */
  protected void displayScreenings(ArrayList<Screening> screenings) {
    if (screenings.size() == 0) {
      System.out.println("No screenings found");
      return;
    }

    for (int i = 0; i < screenings.size(); i++) {
      Screening screening = screenings.get(i);
      System.out.println(i+1 + ": Time: " + screening.getShowtime().getDateTimeString() + "|| Cinema Code: " + screening.getCinema().getId() + 
                          "|| Cinema Type: " + screening.getCinema().getCinemaType().toString() + "|| Cineplex Location: " + screening.getCinema().getCineplex().getLocation());
    }
  }

  /**
   * Helper method that can be used for child classes to get movies to do other things like submitting reviews
   * Displays all movies in the system
   * Allows the user to pick a movie
   * @param sortCriterias
   * @param showStatuses
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

  /**
   * Translate user text input to an integer value
   * @param message
   * @param count
   * @return
   */
  protected Integer getUserChoiceFromCount(String message, int count) {
    ArrayList<String> validInputs = new ArrayList<String>();
    for (int i=1; i<count+1; i++) {
      validInputs.add(Integer.toString(i));
    }
    // no need error checking as only integers are accepted as they were added above
    return Integer.parseInt(this.getUserChoice(message, validInputs));
  }
  
  /**
   * Print message and get user input
   * @param message
   * @return
   */
  protected String getUserInput(String message) {
    System.out.println(message);
    String userInput = scannerObj.nextLine();
    System.out.println("");
    return userInput;
  }
  
  /**
   * Print message and get an integer response from user
   * @param message
   * @return
   */
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

  /**
   * Get a user input from a list of string options
   * @param options
   * @param message
   * @return
   */
  protected Integer getSelectInput(ArrayList<String> options, String message) {
    System.out.println(message);
    for (int i=1; i<=options.size(); i++) {
      System.out.println("Enter " + i + " to " + options.get(i-1));
    }
    return getUserChoiceFromCount("", options.size());
  }

  /**
   * Get a user to select a genre
   * @return
   */
  protected Genre selectGenre() {
    ArrayList<String> options = new ArrayList<String>();
    for (Genre cur : Genre.values()) {
      options.add(cur.toString());
    }
    Integer userChoice = getSelectInput(options, "Select a genre");
    return Genre.values()[userChoice-1];
  }

  /**
   * Get a user to select an advisory rating
   * @return
   */
  protected Advisory selectAdvisory() {
    ArrayList<String> options = new ArrayList<String>();
    for (Advisory cur : Advisory.values()) {
      options.add(cur.toString());
    }
    Integer userChoice = getSelectInput(options, "Select an advisory rating");
    return Advisory.values()[userChoice-1];
  }

  /**
   * Get a user to select a show status
   * @return
   */
  protected ShowStatus selectShowStatus() {
    ArrayList<String> options = new ArrayList<String>();
    for (ShowStatus cur : ShowStatus.values()) {
      options.add(cur.toString());
    }
    Integer userChoice = getSelectInput(options, "Select a show status");
    return ShowStatus.values()[userChoice-1];
  }

  /**
   * Get a user to select a movie type (e.g. Blockbuster, 3D)
   * @return
   */
  protected MovieType selectMovieType() {
    ArrayList<String> options = new ArrayList<String>();
    for (MovieType cur : MovieType.values()) {
      options.add(cur.toString());
    }
    Integer userChoice = getSelectInput(options, "Select a movie type");
    return MovieType.values()[userChoice-1];
  }

  /**
   * Get a user to select a cinema type
   * @return
   */
  protected CinemaType selectCinemaType() {
    ArrayList<String> options = new ArrayList<String>();
    for (CinemaType cur : CinemaType.values()) {
      options.add(cur.toString());
    }
    Integer userChoice = getSelectInput(options, "Select a cinema type");
    return CinemaType.values()[userChoice-1];
  }

  /**
   * Get a user to select a seat type
   * @return
   */
  protected SeatType selectSeatType() {
    ArrayList<String> options = new ArrayList<String>();
    for (SeatType cur : SeatType.values()) {
      options.add(cur.toString());
    }
    Integer userChoice = getSelectInput(options, "Select a seat type");
    return SeatType.values()[userChoice-1];
  }

  /**
   * Get a user to select a ticket type (e.g. Student, Cards)
   * @return
   */
  protected TicketType selectTicketType() {
    ArrayList<String> options = new ArrayList<String>();
    for (TicketType cur : TicketType.values()) {
      options.add(cur.toString());
    }
    Integer userChoice = getSelectInput(options, "Select a ticket type");
    return TicketType.values()[userChoice-1];
  }

  /**
   * Get the cast members of a movie
   * @return
   */
  protected ArrayList<String> getCastMembers() {
    ArrayList<String> castArr = new ArrayList<String>();
    Integer castCount = this.getUserIntegerInput("Enter the number of cast members"); 
    for (int i=1; i<=castCount; i++) {
      String cast = this.getUserInput("Enter the cast member " + i + "'s name: ");
      castArr.add(cast);
    }

    return castArr;
  }
  
  /**
   * Get an user input and ensure it is a float type
   * @param message
   * @return
   */
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
  protected void startProgram() {}

  /**
   * This is the function that is called whenever the program exits, for e.g. when the user chooses to quit the program
   */
  protected void exitProgram() {
    // TODO: save all state into storage
    ParentConsole.scannerObj.close();
    System.exit(0);
  }

  /**
   * Get booking manager
   * @return booking manager object
   */
  protected BookingManager getBookingManager() {
    return this.bookingManager;
  }

  /**
   * logout of account
   */
  protected void logout() {
    this.loginManager.logout();
  }

  /**
   * Displays the content for each console.
   * This is basically the 'main' method for each console
   * @param account
   */
  public abstract void display(Account account);
}
