package view;

import java.util.ArrayList;
import enums.*;
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
    Integer userInput = this.getUserChoiceFromCount("Enter '1' for updating cinema price multiplier, "+
                                                "\n'2' for updating seat price multiplier, "+
                                                "\n'3' for updating user movie sorting criteria, ", 3);
    
    switch (userInput) {
      case 1: {
        Integer cinemaInput = super.getUserChoiceFromCount("Enter '1' for Normal cinemas, "+
        "\n'2' for Gold Class cinemas, "+
        "\n'3' for Deluxe cinemas, "+
        "\n '4' for Premium cinemas", 4);
        // add validation for positive 2 decimal places number
        Float multiplierInput = super.getUserFloatInput("Enter the new price: ");

        CinemaType cinemaType = CinemaType.values()[cinemaInput - 1];
        systemManager.setCinemaMultiplier(cinemaType, multiplierInput);
        break; 
      }
      case 2: {
        Integer seatInput = super.getUserChoiceFromCount("Enter '1' for Normal seats, "+
        "\n'2' for Gold seats, "+
        "\n'3' for Platinum seats, "+
        "\n '4' for Jubilee seats", 4);
        // add validation for positive 2 decimal places number
        Float multiplierInput = super.getUserFloatInput("Enter the new price: ");
        SeatType seatType = SeatType.values()[seatInput - 1];
        systemManager.setSeatMultiplier(seatType, multiplierInput);
        break;
      }
      case 3: {
        try {
          Integer sortingCriteria = super.getUserChoiceFromCount("Enter '1' for ratings" + "\n '2' for sales", 2);
          systemManager.setSortingCriteria(sortingCriteria == 1 ? SortCriteria.RATING : SortCriteria.SALES);
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

    ShowStatus showStatus = null;
    Advisory advisoryRating = null;
    Genre genre = null;
    MovieType movieType = null;

    ArrayList<String> castArr = new ArrayList<String>();
    Integer castCount = super.getUserIntegerInput("Enter the number of cast members");
    for (int i=0; i<castCount; i++) {
      String cast = super.getUserInput("Enter the cast member: ");
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
    Integer userInput = this.getUserChoiceFromCount("\nSelect the type of sales report: "+
                                                    "\nEnter '1' for Total Sales, "+
                                                    "\n'2' for Sales by Movie, "+
                                                    "\n'3' for Sales by Cineplex", 3);

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
          String cineplexName = super.getUserInput("Enter Cineplex Name: ");
          System.out.print(cineplexName + "'s Sales: $" + SalesUtils.getSalesByCineplex(bookingArr, cineplexName));
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
    String filterType = super.getUserInput("Enter the type of filter: \n'1' for Top 5 by Sales, \n'2' for Top 5 by Rating");
    SortCriteria sortCriteria = filterType.equals("1") ? SortCriteria.SALES : SortCriteria.RATING;
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
            Integer castCount = super.getUserIntegerInput("Enter the number of cast members");
            for (int i=0; i<castCount; i++) {
              String cast = super.getUserInput("Enter the cast member");
              castArr.add(cast);
            }
            super.getMovieManager().updateMovie(movie, null, null, null, castArr, null, null, null);
            break;

          case "5":
            Integer advisoryInput = super.getUserChoiceFromCount("Enter the advisory rating" + 
                                                                "\nEnter '1' for PG13, "+
                                                                "\n'2' for NC16, "+
                                                                "\n'3' for M18, "+
                                                                "\n '4' for R21", Advisory.values().length);

            Advisory advisory = Advisory.values()[advisoryInput - 1];
          // add validation for positive 2 decimal places number
            super.getMovieManager().updateMovie(movie, null, null, null, null, advisory, null, null);
            break;

          case "6":
            Integer genreInput = super.getUserChoiceFromCount("Enter the genre of the movie" +
                                                              "\nEnter '1' for Action, "+
                                                              "\n'2' for Adventure, "+
                                                              "\n'3' for Comedy, "+
                                                              "\n '4' for Drama, "+
                                                              "\n '5' for Fantasy, "+
                                                              "\n '6' for Horror, "+
                                                              "\n '7' for Romance, "+
                                                              "\n '8' for Thriller, "+
                                                              "\n '9' for Western", Genre.values().length);

            Genre genre = Genre.values()[genreInput - 1];
            super.getMovieManager().updateMovie(movie, null, null, null, null, null, genre, null);
            break;

          case "7":
            Integer statusInput = super.getUserChoiceFromCount("Enter the show status of the movie" +
                                                          "\nEnter '1' for Coming Soon, "+
                                                          "\n'2' for Preview, "+
                                                          "\n'3' for Now Showing, "+
                                                          "\n '4' for End of Showing, ",ShowStatus.values().length);

            ShowStatus showStatus = ShowStatus.values()[statusInput - 1];
            super.getMovieManager().updateMovie(movie, null, null, null, null, null, null, showStatus);
            break;
          
          default:
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
    String movieName = super.getUserInput("Enter the movie name");
    String cinemaId = super.getUserInput("Enter the cinema ID");
    String newShowTime = super.getUserInput("Enter the new showtime details in dd.MM.yyyy.HH.mm format");

    try {
      Movie movie = super.getMovieManager().getMovieByName(movieName);
      Cinema cinema = Cinema.getCinemaById(cinemaId);
      super.getScreeningManager().addScreening(movie, cinema, newShowTime);
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
      String newShowTime = super.getUserInput("Enter the new showtime details in dd.MM.yyyy.HH.mm format"); 
      Movie movie = super.getMovieManager().getMovieByName(movieName);

      Screening screening = super.getScreening(movie);
      super.getScreeningManager().updateShowtime(screening, newShowTime);
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

  @Override
  public void display(Account account) {
    // should never trigger as it can only reach AdminConsole if the logged in user is a AdminAccount
    if (!(account instanceof AdminAccount)) {
      System.out.println("Something went wrong in the login process");
      this.exitProgram();
    }

    Integer userSelection = this.getUserChoiceFromCount("Enter '1' to add movie, \n'2' to update movie, \n'3' to remove movie, \n'4' to add screening, \n'5' to update showtime, \n'6' to delete showtime, \n'7' to update system configurations, \n'8' to quit", 8);

    // TODO: Use this for authorisation checks
    AdminAccount adminAccount = (AdminAccount) account;

    switch (userSelection) {
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
