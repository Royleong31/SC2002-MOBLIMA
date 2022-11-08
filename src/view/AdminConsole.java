package view;

import java.util.ArrayList;
import enums.*;
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
  public void updateSystemConfig() {
    SystemManager systemManager = super.getSystemManager();
    Integer userInput = super.getSelectInput(Utils.asArrayList("update cinema price multiplier", 
                                                               "to update seat price multiplier", 
                                                               "update user movie sorting criteria"), "Select type of configuration to update: ");
    
    switch (userInput) {
      case 1: {
        CinemaType cinemaType = super.selectCinemaType();
        // add validation for positive 2 decimal places number
        Float cinemaPriceMultiplier = super.getUserFloatInput("Enter the new price multiplier: ");
        systemManager.setCinemaMultiplier(cinemaType, cinemaPriceMultiplier);
        System.out.println("New Multiplier: " + cinemaType.toString() + ": " + systemManager.getCinemaMultiplier(cinemaType));
        break; 
      }

      case 2: {
        SeatType seatType = super.selectSeatType();
        // add validation for positive 2 decimal places number
        Float seatPriceMultiplier = super.getUserFloatInput("Enter the new price multiplier: ");
        systemManager.setSeatMultiplier(seatType, seatPriceMultiplier);
        System.out.println("New Multiplier: " + seatType.toString() + ": " + systemManager.getSeatMultiplier(seatType));
        break;
      }

      case 3: {
        try {
          Integer sortingCriteria = super.getSelectInput(Utils.asArrayList("for ratings", "for sales"), "Select the sorting criteria: ");
          systemManager.setSortingCriteria(sortingCriteria == 1 ? SortCriteria.RATING : SortCriteria.SALES);
          System.out.println("New sorting criteria: " + systemManager.getSortingCriteria().toString());
          // Won't have an exception as only allowed sorting criteria are passed in
        } catch (Exception e) {
          System.out.println("Something went wrong while updating the sorting criteria");
          System.out.println(e.getMessage());
        }
        break;
      }
      default:
        break;
    }
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
    ArrayList<String> cast = super.getCastMembers();
    ShowStatus showStatus = super.selectShowStatus();
    Advisory advisoryRating = super.selectAdvisory();
    Genre genre = super.selectGenre();
    MovieType movieType = super.selectMovieType();

    Movie movie = new Movie(title, showStatus, synopsis, director, cast, advisoryRating, genre, movieType);
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
    try {
      String movieTitle = super.getUserInput("Enter the title of the movie");
      super.getMovieManager().removeMovie(movieTitle);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
  /**
   * Asks the user for the type of sales report they want (overall, by cineplex, by movie)
   * Call the SalesUtils's methods to get the appropriate report
   */
  public void getSalesReport() {
    Integer userInput = super.getSelectInput(Utils.asArrayList("for total sales", 
                                                               "for sales by movie", 
                                                               "for sales by cineplex"), "Select the type of sales report:");
    ArrayList<Booking> bookingArr = super.getBookingManager().getBookings();
    
    switch(userInput){
      case 1:
        System.out.print("\nTotal Sales: $" + SalesUtils.getTotalSales(bookingArr));
        break;

      case 2:
        try {
          String movieTitle = super.getUserInput("Enter Movie Title: ");
          System.out.println(movieTitle + "'s Sales: $" + SalesUtils.getSalesByMovie(bookingArr, movieTitle));
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
        break;

      case 3:
        try {
          String cineplexLocation = super.getUserInput("Enter Cineplex Location: ");
          System.out.print(cineplexLocation + "'s Sales: $" + SalesUtils.getSalesByCineplex(bookingArr, cineplexLocation));
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
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
    Integer userInput = super.getSelectInput(Utils.asArrayList("for Top 5 by sales", "for top 5 by rating"), "Enter the type of filter");
    SortCriteria sortCriteria = userInput == 1 ? SortCriteria.SALES : SortCriteria.RATING;
    ArrayList<Movie> movies = super.getMovieManager().getMovies(sortCriteria); // get top 5 movies
    movies = new ArrayList<Movie>(movies.subList(0, 4));
    super.displayMovies(movies);
  }

  /**
   * Display the current movie state
   * Ask the user for the attributes of the movie that they want to update
   * Update only the fields that the user changed
   * Call the movieManager's updateMovie method to update the movie
   */
  public void updateMovie() {
    String movieInput = super.getUserInput("Enter movie to update: ");
    try {
      // An exception can be thrown from here if the movie is not found
      Movie movie = super.getMovieManager().getMovieByName(movieInput);

      Integer userInput = null;
      while(true){
        userInput = super.getSelectInput(Utils.asArrayList("Title: " + movie.getTitle(), 
                                                           "Synopsis: " + movie.getSynopsis(), 
                                                           "Director: " + movie.getDirector(), 
                                                           "Cast: " + movie.getCast(), 
                                                           "Advisory: " + movie.getAdvisoryRating().toString(), 
                                                           "Genre: " + movie.getGenre().toString(),
                                                           "Show Status: " + movie.getShowingStatus().toString(), 
                                                           "Movie Type: " + movie.getMovieType().toString(), 
                                                           "Exit"), "Select the type of sales report:");
        
        switch(userInput) {
          case 1:
            String title = super.getUserInput("Enter the title of the movie");
            super.getMovieManager().updateMovie(movie, title, null, null, null, null, null, null, null);
            break;

          case 2:
            String synopsis = super.getUserInput("Enter the synopsis of the movie");
            super.getMovieManager().updateMovie(movie, null, synopsis, null, null, null, null, null, null);
            break;

          case 3:
            String director = super.getUserInput("Enter the director of the movie");
            super.getMovieManager().updateMovie(movie, null, null, director, null, null, null, null, null);
            break;

          case 4:
            ArrayList<String> cast = super.getCastMembers();
            super.getMovieManager().updateMovie(movie, null, null, null, cast, null, null, null, null);
            break;

          case 5:
            Advisory advisory = super.selectAdvisory();
            super.getMovieManager().updateMovie(movie, null, null, null, null, advisory, null, null, null);
            break;

          case 6:
            Genre genre = super.selectGenre();
            super.getMovieManager().updateMovie(movie, null, null, null, null, null, genre, null, null);
            break;

          case 7:
            ShowStatus showStatus = super.selectShowStatus();
            super.getMovieManager().updateMovie(movie, null, null, null, null, null, null, showStatus, null);
            break;

          case 8:
            MovieType movieType = super.selectMovieType();
            super.getMovieManager().updateMovie(movie, null, null, null, null, null, null, null, movieType);
            break;

          case 9:
            return;

          default:
            System.out.println("Invalid input, please try again\n");
            break;
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Allow the user to input showtime details and then add it to the Screening
   * 
   */
  public void addScreening() {    
    try {
      String movieName = super.getUserInput("Enter the movie name");
      Movie movie = super.getMovieManager().getMovieByName(movieName);
      String cinemaId = super.getUserInput("Enter the cinema ID");
      Cinema cinema = this.getCineplexManager().getCinemaById(cinemaId);
      // TODO: Add validation
      String showTime = super.getUserInput("Enter the new showtime details in dd.MM.yyyy.HH.mm format");
      Screening screening = super.getScreeningManager().addScreening(movie, cinema, showTime);
      System.out.println("Screening of movie " + screening.getMovie().getTitle() + " at " + screening.getCinemaId() + " on " + screening.getShowtime() + " has been added");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Allow the user to update a showtime
   */
  public void updateShowtime() { 
    try {
      String movieName = super.getUserInput("Enter the movie name");
      Movie movie = super.getMovieManager().getMovieByName(movieName);
      Screening screening = super.getScreening(movie);
      String newShowTime = super.getUserInput("Enter the new showtime details in dd.MM.yyyy.HH.mm format"); 

      Screening screeningReturn = super.getScreeningManager().updateShowtime(screening, newShowTime);
      System.out.println("Screening of movie " + screeningReturn.getMovie().getTitle() + " at " + screeningReturn.getCinemaId() + " on " + screeningReturn.getShowtime() + " has been updated");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Allow the user to delete a showtime
   */
  public void deleteShowtime() {
    try {
      String movieName = super.getUserInput("Enter the movie name");
      Movie movie = super.getMovieManager().getMovieByName(movieName);
      Screening screening = super.getScreening(movie);

      super.getScreeningManager().deleteScreening(screening, super.getBookingManager());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void addCinema() {
    try {
      String cineplexLocation = super.getUserInput("Enter the cineplex location");
      Cineplex cineplex = super.getCineplexManager().getCineplexByLocation(cineplexLocation);
      int rows = super.getUserIntegerInput("Enter the number of rows");
      int columns = super.getUserIntegerInput("Enter the number of columns");
      int aisle = super.getUserIntegerInput("Enter the number of aisles");
      CinemaType cinemaType = super.selectCinemaType();
      Cinema cinema = super.getCineplexManager().addCinema(cineplex, rows, columns, aisle, cinemaType);
      System.out.println("Cinema " + cinema.getId() + " has been added at " + cineplex.getLocation());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void addCineplex() {
    try {
      String location = super.getUserInput("Enter the cineplex location");
      super.getCineplexManager().addCineplex(location);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void display(Account account) {
    // should never trigger as it can only reach AdminConsole if the logged in user is a AdminAccount
    if (!(account instanceof AdminAccount)) {
      System.out.println("Something went wrong in the login process");
      this.exitProgram();
    }

    Integer userInput = super.getSelectInput(Utils.asArrayList("to add movie", 
                                                               "to update movie", 
                                                               "to remove movie", 
                                                               "to add screening", 
                                                               "to update showtime", 
                                                               "to delete showtime", 
                                                               "to update system configurations", 
                                                               "to add cineplex", 
                                                               "to add cinema", 
                                                               "to quit"), "Select an option"); 


    // TODO: Use this for authorisation checks

    // TODO: Add setters for cinema and cineplex
    AdminAccount adminAccount = (AdminAccount) account;

    switch (userInput) {
      case 1:
        this.addMovie();
        break;
    
      case 2:
        this.updateMovie();
        break;
    
      case 3:
        this.removeMovie();
        break;

      case 4:
        this.addScreening();
        break;

      case 5:
        this.updateShowtime();
        break;
      
      case 6:
        this.deleteShowtime();
        break;
  
      case 7:
        this.updateSystemConfig();
        break;

      case 8:
        this.addCineplex();
        break;
      
      case 9:
        this.addCinema();
        break;
      
      case 10:
        super.exitProgram();
        break;
    
      default:
        // Should never reach here as error checking is done in this.getUserChoice()
         // Should never reach here as error checking is done in this.getUserChoice()
        System.out.println("An unexpected error occured");
        this.exitProgram();
    }
  }

}
