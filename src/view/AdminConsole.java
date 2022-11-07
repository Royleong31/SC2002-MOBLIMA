package view;

import java.util.ArrayList;
import java.util.Scanner;
import enums.*;
import controller.CineplexManager;
import controller.SystemManager;
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
  public void updateSystemConfig() {
    SystemManager systemManager = super.getSystemManager();
    String userInput = this.getUserChoiceFromCount("Enter '1' for updating cinema price multiplier, "+
                                                "\n'2' for updating seat price multiplier, "+
                                                "\n'3' for updating user movie sorting criteria, ", 3);
    
    switch (userInput) {
      case "1": {
        String cinemaInput = super.getUserChoiceFromCount("Enter '1' for Normal cinemas, "+
        "\n'2' for Gold Class cinemas, "+
        "\n'3' for Deluxe cinemas, "+
        "\n '4' for Premium cinemas", 4);
        // add validation for positive 2 decimal places number
        Number multiplierInput = super.getUserIntegerInput("Enter the new price: ");
        systemManager.setCinemaMultiplier(cinemaInput, multiplierInput);
        break; 
      }
      case "2": {
        String seatInput = super.getUserChoiceFromCount("Enter '1' for Normal seats, "+
        "\n'2' for Gold seats, "+
        "\n'3' for Platinum seats, "+
        "\n '4' for Jubilee seats", 4);
        // add validation for positive 2 decimal places number
        Number multiplierInput = super.getUserIntegerInput("Enter the new price: ");
        systemManager.setSeatMultiplier(seatInput, multiplierInput);
        break;
      }
      case "3":
        String sortingCriteria = super.getUserChoiceFromCount("Enter '1' for ratings" + "\n '2' for sales", 2);
        systemManager.setSortingCriteria(sortingCriteria == "1" ? SortCriteria.RATING : SortCriteria.SALES);
        break;
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

    ShowStatus showStatus = null;
    Advisory advisoryRating = null;
    Genre genre = null;
    MovieType movieType = null;

    ArrayList<String> castArr = new ArrayList<String>();
    Integer castCount = super.getUserIntegerInput("Enter the number of cast members");
    for (int i=0; i<castCount; i++) {
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
    String userInput = this.getUserChoiceFromCount("\nSelect the type of sales report: "+
                                                "\nEnter '1' for Total Sales, "+
                                                "\n'2' for Sales by Movie, "+
                                                "\n'3' for Sales by Cineplex", 3);

    ArrayList<Booking> bookingArr = super.getBookingManager().getBookings();
    
    switch(userInput){
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
          String synopsis = super.getUserInput("Enter the synopsis of the movie");
          super.getMovieManager().updateMovie(movie, null, synopsis, null, null, null, null, null);
          break;

        case "3":
          String director = super.getUserInput("Enter the director of the movie");
          super.getMovieManager().updateMovie(movie, null, null, director, null, null, null, null);
          break;

        case "4":
          ArrayList<String> castArr = new ArrayList<String>();
          String castCount = super.getUserInput("Enter the number of cast members");
          for (int i=0; i<Number(castCount); i++) {
            String cast = super.getUserInput("Enter the cast member");
            castArr.add(cast);
          }
          super.getMovieManager().updateMovie(movie, null, null, null, castArr, null, null, null);
          break;

        case "5":
          String advisory = super.getUserInput("Enter the advisory of the movie");
          super.getMovieManager().updateMovie(movie, null, null, null, null, advisory, null, null);
          break;

        case "6":
          String genre = super.getUserInput("Enter the genre of the movie");
          super.getMovieManager().updateMovie(movie, null, null, director, null, null, null, null);
          break;

        case "7":
          String showStatus = super.getUserInput("Enter the show status of the movie");
          super.getMovieManager().updateMovie(movie, null, null, director, null, null, null, null);
          break;
        
        default:
          break;
      }
    }
  }

  /**
   * Allow the user to input showtime details and then add it to the Screening
   * 
   */
  public void addScreening() {
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

    String userSelection = this.getUserChoiceFromCount("Enter '1' to add movie, \n'2' to update movie, \n'3' to delete movie, \n'4' to quit", 4);

    AdminAccount adminAccount = (AdminAccount) account;

    switch (userSelection) {
      case "1":
        this.addMovie();
        break;
    
      case "2":
        this.updateMovie();
        break;
    
      case "3":
        this.removeMovie();
        break;

      case "4":
        this.addScreening();
        break;

      case "5":
        this.updateShowtime();
        break;
      
      case "6":
        this.deleteShowtime();
        break;
    
      case "7":
        this.updateSystemConfig();
        break;

      case "8":
        super.exitProgram();
        break;
    
      default:
        // Should never reach here as error checking is done in this.getUserChoice()
        throw new Error("An unexpected error occured");
    }
  }

}
