package view;
import controller.ScreeningManager;
import controller.SystemManager;
import model.Movie;
import model.Screening;
import controller.MovieManager;
import controller.BookingManager;

import java.util.ArrayList;
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
   * Handles state and methods related to movies
   */
  private MovieManager movieManager = new MovieManager();

  /**
   * Handles state and methods related to screenings
   */
  private ScreeningManager screeningManager = new ScreeningManager();

  /**
   * Handles state and methods related to bookings
   */
  private BookingManager bookingManager = new BookingManager();

  /**
   * Handles state and methods related to system configurations
   */
  private SystemManager systemManager = new SystemManager(Constants.DEFAULT_PRICE);


  /**
   * Displays all movies in the system
   */
  public void displayMovies() {

  }

  /**
   * Helper method that can be used for child classes to get movies to do other things like submitting reviews
   * Displays all movies in the system
   * Allows the user to pick a movie
   * @return the movie that the user picked
   */
  protected Movie getMovie() {
  }

  
  /**
   * Helper method that returns all the screenings for a movie that are currently showing 
   * Can be overriden in AdminConsole to show all screenings including those that are not currently showing
   * @param movie the movie that the user wants to select a screening from
   * @return the screening that the user picked
   */
  protected Screening getScreening(Movie movie) {
  }

  /**
   * This is to get the user's choice, for e.g. when the user is selecting whether to login or register
   * validInputs should contain only lowercase values
   * Case checking is done inside here
   * @param message
   * @param validInputs valid inputs that the user can enter(all lowercase), no repeated values
   * @return
   */
  protected String getUserChoice(String message, ArrayList<String> validInputs) {
    Scanner scannerObj = new Scanner(System.in);
    System.out.println(message);
    String userInput = scannerObj.nextLine().toLowerCase();

    while (!validInputs.contains(userInput)) {
      System.out.println("Invalid input. Please try again");
      userInput = scannerObj.nextLine().toLowerCase();
    }

    scannerObj.close();

    return userInput;
  }

  /**
   * This is the function that is called whenever the program exits, for e.g. when the user chooses to quit the program
   */
  protected void exitProgram() {
    // save all state into storage
    System.exit(0);
  }

  /**
   * Displays the content for each console.
   * This is basically the 'main' method for each console
   */
  public abstract void display();
}
