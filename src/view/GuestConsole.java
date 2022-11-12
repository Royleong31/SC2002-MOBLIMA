package view;

import java.util.ArrayList;
import enums.*;
import controller.BookingManager;
import controller.CineplexManager;
import controller.LoginManager;
import controller.MovieManager;
import controller.ReviewManager;
import controller.ScreeningManager;
import controller.SystemManager;
import model.Movie;
import model.Account.Account;
import model.Account.AdminAccount;
import model.Booking;
import model.Cinema;
import model.Cineplex;
import model.Screening;
import utils.SalesUtils;
import utils.Utils;

public class GuestConsole extends ParentConsole {
  private final ArrayList<ShowStatus> allowedShowStatus = Utils.asArrayList(ShowStatus.PREVIEW,
      ShowStatus.NOW_SHOWING);

  public GuestConsole(LoginManager lm, BookingManager bm, CineplexManager cm, MovieManager mm, ReviewManager rm,
      ScreeningManager sm, SystemManager sysm) {
    super(lm, bm, cm, mm, rm, sm, sysm);
  }

  private void displayAllMovies() {
    ArrayList<Movie> movies = super.getMovieManager().getMovies(this.allowedShowStatus);
    super.displayMovies(movies);
  }

  private void displayTopMovies() {
    ArrayList<Movie> movies = super.getMovieManager().getMovies(super.getSystemManager().getSortingCriteria(),
        this.allowedShowStatus);
    movies = movies.size() < 5 ? movies : new ArrayList<Movie>(movies.subList(0, 4));
    super.displayMovies(movies);
  }

  private void viewScreenings() {
    try {
      Movie movie = super.getMovie(SortCriteria.TITLE, this.allowedShowStatus);
      ArrayList<Screening> screenings = super.getScreeningManager().getScreeningsByMovie(movie.getTitle());

      System.out.println("Screenings for " + movie.getTitle());
      this.displayScreenings(screenings);
    } catch (Exception e) {
      System.out.println("No screenings available.");
    }
  }

  @Override
  public void display(Account account) {
    while (true) {
      Integer userInput = super.getSelectInput(Utils.asArrayList("View all movies",
          "View top movies",
          "View movie screenings",
          "Back to main menu"), "Select an option");

      switch (userInput) {
        case 1:
          this.displayAllMovies();
          break;

        case 2:
          this.displayTopMovies();
          break;

        case 3:
          this.viewScreenings();
          break;

        case 4:
          return;

        default:
          // Should never reach here as error checking is done in this.getUserChoice()
          // Should never reach here as error checking is done in this.getUserChoice()
          System.out.println("An unexpected error occured");
          return;
      }
    }
  }

}
