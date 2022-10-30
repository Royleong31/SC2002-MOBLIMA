package view;
import controller.ScreeningManager;
import model.Movie;
import model.Screening;
import controller.MovieManager;
import controller.BookingManager;
import java.util.Scanner;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class Console {
  public MovieManager movieManager = new MovieManager();
  public ScreeningManager screeningManager = new ScreeningManager();
  public BookingManager bookingManager = new BookingManager();

  public void displayMovies() {

  }

  public Movie getMovie() {
    return null;
  }

  
  public Screening getScreening() {
    return null;
  }
}
