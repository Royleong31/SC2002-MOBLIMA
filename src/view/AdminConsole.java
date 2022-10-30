package view;
import controller.SalesManager;
import controller.SystemManager;
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
public class AdminConsole extends Console {
  private CineplexManager cineplexManager = new CineplexManager();
  private SystemManager systemManager;
  private SalesManager salesManager = new SalesManager();

  public AdminConsole(float basePrice) {
    systemManager = new SystemManager(basePrice);
  }

  public void addMovie(Movie movie) {

  }

  public void getSalesReport() {

  }

  public void getBookings() {

  }

}
