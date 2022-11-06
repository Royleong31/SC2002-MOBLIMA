package view;

import java.util.ArrayList;
import java.util.Scanner;
import enums.*;
import controller.CineplexManager;
import model.Movie;
import model.Account.Account;
import model.Account.AdminAccount;
import model.Booking;
import model.Cinema;
import model.Screening;
import utils.SalesUtils;
import utils.Utils;




/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class AdminConsole extends ParentConsole {


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
  public void addMovie() { 
    String title = super.getUserInput("Enter the title of the movie");
    String synopsis = super.getUserInput("Enter the synopsis of the movie");
    String director = super.getUserInput("Enter the director of the movie");

    ShowStatus showStatus = null;
    Advisory advisoryRating = null;
    Genre genre = null;
    MovieType movieType = null;

    ArrayList<String> castArr = new ArrayList<String>();
    String castCount = super.getUserInput("Enter the number of cast members");
    for (int i=0; i<Number(castCount); i++) {
      String cast = super.getUserInput("Enter the cast member");
      castArr.add(cast);
    }

    Movie movie = new Movie(title, showStatus, synopsis, director, castArr, advisoryRating, genre, movieType);
    super.getMovieManager().addMovie(movie);
  }

  /**
   * Ask the user for the movie's title to remove it from the list
   * call the removeMovie method in the MovieManager class to remove it
   * Catches and displays exceptions
   * 
   * @param movie
   */
  public void removeMovie() {
    String movieTitle = super.getUserInput("Enter the title of the movie");
    super.getMovieManager().removeMovie(movieTitle);
  }
  
  /**
   * Asks the user for the type of sales report they want (overall, by cineplex, by movie)
   * Call the SalesUtils's methods to get the appropriate report
   */
  public void getSalesReport() {
    String reportType = this.getUserChoice("\nSelect the type of sales report: "+
                                                "\nEnter '1' for Total Sales, "+
                                                "\n'2' for Sales by Movie, "+
                                                "\n'3' for Sales by Cineplex", Utils.asArrayList("1", "2", "3"));

    ArrayList<Booking> bookingArr = super.getBookingManager().getBookings();
    
    switch(reportType){
      case "1":
        System.out.print("\nTotal Sales: $" + SalesUtils.getTotalSales(bookingArr));
        break;
      case "2":
        String movieTitle = super.getUserInput("Enter Movie Title: ");
        System.out.println(movieTitle + "'s Sales: $" + SalesUtils.getSalesByMovie(bookingArr, movieTitle));
        break;
      case "3":
        String cineplexName = super.getUserInput("Enter Cineplex Name: ");
        System.out.print(cineplexName + "'s Sales: $" + SalesUtils.getSalesByCineplex(bookingArr, cineplexName));
        break;
      default:
        break;
    }
  }

  /**
   * Ask the user for the filtering criteria to get the list of movies (filtereed by movie, cineplex etc)
   * Calls the getMovies method in the MovieManager class to get the appropriate movies
   * Displays the list of movies
   */
  public void getMoviesByRank() { //display top 5 movie by sales and top 5 by rating, admin can choose which of these or both to display to user
    String filterType = super.getUserInput("Enter the type of filter: \n'1' for Top 5 by Sales, \n'2' for Top 5 by Rating");
    SortCriteria sortCriteria = filterType.equals("1") ? SortCriteria.SALES : SortCriteria.RATING;
    ArrayList<Movie> movies = super.getMovieManager().getMovies(sortCriteria).subList(0, 4); // get top 5 movies
    super.displayMovies(movies);
    
  }

  /**
   * Display the current movie state
   * Ask the user for the attributes of the movie that they want to update
   * Update only the fields that the user changed
   * Call the movieManager's updateMovie method to update the movie
   */
  public void updateMovie() {
    Scanner sc = new Scanner(System.in);
    System.out.print("\nEnter movie to update: ");
    String movieInput = sc.nextLine();
    Movie movie = super.getMovieManager().getMovieByName(movieInput);

    String userChoice = super.getUserChoice("\nMovie Details: "+
                        "\n1 - Title: " + movie.getTitle()+
                        "\n2 - Show Status: " + movie.getShowingStatus().toString() +
                        "\n3 - Synopsis" + movie.getSynopsis()+
                        "\n4 - Director: " + movie.getDirector()+
                        "\n5 - Cast: \n" + movie.getCast()+ // TODO: Show the array of cast members
                        "\n6 - Advisory: " + movie.getAdvisoryRating().toString() +
                        "\n7 - Genre: " + movie.getGenre().toString() +
                        "\n-1 - Exit", Utils.asArrayList("1", "2", "3", "4", "5", "6", "7", "-1"));

    while(userChoice != "-1"){
      switch(userChoice){
        case "1":
          String title = super.getUserInput("Enter the title of the movie");
          super.getMovieManager().updateMovie(movie, title, null, null, null, null, null, null);
          break;

        case "2":
          String showStatus = super.getUserInput("Enter the Show status of the movie");
          ShowStatus showingStatus = ShowStatus.values()[sc.nextInt() - 1];
          super.getMovieManager().updateMovie(movie, null, null, null, null, null, null, showStatus);
          break;

        case "3":
          String synopsis = super.getUserInput("Enter the synopsis of the movie");
          super.getMovieManager().updateMovie(movie, null, synopsis, null, null, null, null, null);
          break;

        case "4":
          String director = super.getUserInput("Enter the director of the movie");
          super.getMovieManager().updateMovie(movie, null, null, director, null, null, null, null);
          break;

        case "4":
          String director = super.getUserInput("Enter the advisory of the movie");
          super.getMovieManager().updateMovie(movie, null, null, null, null, advisory, null, null);
          break;

        case "4":
          String director = super.getUserInput("Enter the director of the movie");
          super.getMovieManager().updateMovie(movie, null, null, director, null, null, null, null);
          break;

        case "4":
          String director = super.getUserInput("Enter the director of the movie");
          super.getMovieManager().updateMovie(movie, null, null, director, null, null, null, null);
          break;
        //...
      }
    }

    super.getMovieManager().updateMovie(movieInput);
  }

  /**
   * Allow the user to input showtime details and then add it to the Screening
   * 
   */
  public void createScreening() {
    String movieName = super.getUserInput("Enter the movie name");
    String cinemaId = super.getUserInput("Enter the cinema ID");
    String newShowTime = super.getUserInput("Enter the new showtime details in dd.MM.yyyy.HH.mm format");

    Movie movie = super.getMovieManager().getMovieByName(movieName);
    Cinema cinema = Cinema.getCinemaById(cinemaId);
  
    super.getScreeningManager().addScreening(movie, cinema, newShowTime);
  }

  /**
   * Allow the user to update a showtime
   */
  public void updateShowtime() { 
    String movieName = super.getUserInput("Enter the movie name");
    String newShowTime = super.getUserInput("Enter the new showtime details in dd.MM.yyyy.HH.mm format"); 
    Movie movie = super.getMovieManager().getMovieByName(movieName);

    Screening screening = super.getScreening(movie);
    super.getScreeningManager().updateShowtime(screening, newShowTime);
  }

  /**
   * Allow the user to delete a showtime
   */
  public void deleteShowtime() {
    String movieName = super.getUserInput("Enter the movie name");
    Movie movie = super.getMovieManager().getMovieByName(movieName);
    Screening screening = super.getScreening(movie);

    super.getScreeningManager().deleteScreening(screening, super.getBookingManager());
  }

  @Override
  public void display(Account account) {
    // should never trigger as it can only reach AdminConsole if the logged in user is a AdminAccount
    if (!(account instanceof AdminAccount)) {
      System.out.println("Something went wrong in the login process");
      this.exitProgram();
    }

    String userSelection = this.getUserChoice("Enter '1' to add movie, \n'2' to update movie, \n'3' to delete movie, \n'4' to quit", Utils.asArrayList("1", "2", "3", "4"));

    AdminAccount movieGoerAccount = (AdminAccount) account;

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
        this.exitProgram();
        break;
    
      default:
        // Should never reach here as error checking is done in this.getUserChoice()
        throw new Error("An unexpected error occured");
    }
  }

}
