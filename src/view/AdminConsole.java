package view;
import utils.SalesUtils;
import controller.CineplexManager;
import model.Movie;



/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class AdminConsole extends ParentConsole {
  // TODO: Do we need cineplex manager?
  /**
   * Handles state and methods related to cineplexes
   */
  private CineplexManager cineplexManager = new CineplexManager();

  public AdminConsole() {
    super();
  }

  /**
   * Displays the current system configuration
   * Asks the user to select a configuration to change
   * Catches and displays any exceptions
   */
  public void setSystemConfig() {

  }

  /**
   * Ask the user for input to add a movie such as the title, cast, etc
   * call the addMovie method in the MovieManager class to add it
   * Catches and displays exceptions
   * 
   * @param movie
   */
  public void addMovie(Movie movie) {

  }

  /**
   * Ask the user for the movie's title to remove it from the list
   * call the removeMovie method in the MovieManager class to remove it
   * Catches and displays exceptions
   * 
   * @param movie
   */
  public void deleteMove(Movie movie) {

  }
  
  /**
   * Asks the user for the type of sales report they want (overall, by cineplex, by movie)
   * Call the SalesManager's methods to get the appropriate report
   */
  public void getSalesReport() {

  }

  /**
   * Asks the user for the type of bookings that want to view (filtereed by movie, cineplex etc)
   * Calls the getBookings method in the BookingsManager class to get the appropriate bookings
   */
  public void getBookings() {

  }

  /**
   * Ask the user for the filtering criteria to get the list of movies (filtereed by movie, cineplex etc)
   * Calls the getMovies method in the MovieManager class to get the appropriate movies
   * Displays the list of movies
   */
  public void getMoviesByRank() {

  }

  /**
   * Display the current movie state
   * Ask the user for the attributes of the movie that they want to update
   * Update only the fields that the user changed
   * Call the movieManager's updateMovie method to update the movie
   */
  public void updateMovie() {
  }

  /**
   * Allow the user to delete a movie
   */
  public void deleteMovie() {

  }

  /**
   * Allow the user to input showtime details and then add it to the Screening
   * 
   */
  public void createShowtime() {

  }

  /**
   * Allow the user to update a showtime
   */
  public void updateShowtime() {}

  /**
   * Allow the user to delete a showtime
   */
  public void deleteShowtime() {

  }

}
