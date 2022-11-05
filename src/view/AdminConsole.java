package view;

import java.util.ArrayList;
import java.util.Scanner;
import enums.*;
import controller.SalesManager;
import controller.CineplexManager;
import model.Movie;
import model.Account.Account;
import model.Account.AdminAccount;
import model.Booking;
import model.Screening;
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
  public void addMovie(Movie movie) {  //Is this supposed to have a movie parameter here?
    Scanner sc = new Scanner(System.in); //change to super method
    System.out.print("\nEnter Movie Title: ");
    String title = sc.nextLine();
    System.out.print("\nEnter Showing Status: "+
                      "\n1 - COMING_SOON"+
                      "\n2 - PREVIEW"+
                      "\n3 - NOW_SHOWING"+
                      "\n4 - END_OF_SHOWING\n");
    ShowStatus showingStatus = ShowStatus.values()[sc.nextInt() - 1];
    System.out.print("\nEnter Movie Synopsis: ");
    String synopsis = sc.nextLine();
    System.out.print("\nEnter Movie Director: ");
    String director = sc.nextLine();
    System.out.print("\nEnter Movie Cast (\'-1\' to end): ");
    ArrayList<String> cast = new ArrayList<String>();
    String castMember = sc.nextLine();
    while(castMember != "-1" && cast.size() > 0){
      if(castMember != "-1"){
        cast.add(castMember);
      }  
      castMember = sc.nextLine();
    }
    System.out.print("\nEnter Movie Advisory: "+
                      "\n1 - P13"+
                      "\n2 - P16"+
                      "\n3 - p18"+
                      "\n4 - NC16"+
                      "\n5 - M18\n");
    Advisory advisory = Advisory.values()[sc.nextInt() - 1];
    System.out.print("\nEnter Movie Genre: "+
                      "\n1 - ACTION"+
                      "\n2 - ADVENTURE"+
                      "\n3 - COMEDY"+
                      "\n4 - DRAMA"+
                      "\n5 - FANTASY"+
                      "\n6 - HORROR"+
                      "\n7 - ROMANCE"+
                      "\n8 - THRILLER"+
                      "\n9 - WESTERN\n");
    Genre genre = Genre.values()[sc.nextInt() - 1];
    Movie mov = new Movie(title, showingStatus, synopsis, director, cast, advisory, genre);
    super.getMovieManager().addMovie(mov);
  }

  /**
   * Ask the user for the movie's title to remove it from the list
   * call the removeMovie method in the MovieManager class to remove it
   * Catches and displays exceptions
   * 
   * @param movie
   */
  public void deleteMovie(Movie movie) {
    super.getMovieManager().deleteMovie(movie);
  }
  
  /**
   * Asks the user for the type of sales report they want (overall, by cineplex, by movie)
   * Call the SalesManager's methods to get the appropriate report
   */
  public void getSalesReport() {
    String reportType = this.getUserChoice("\nSelect the type of sales report: "+
                                                "\nEnter '1' for Total Sales, "+
                                                "\n'2' for Sales by Movie, "+
                                                "\n'3' for Sales by Cineplex", Utils.asArrayList("1", "2", "3"));

    Scanner sc = new Scanner(System.in);
    ArrayList<Booking> bookingArr = super.getBookingManager().getBookings();
    // System.out.print("\nSelect the type of sales report:"+
    //                     "\n1 - Total Sales"+
    //                     "\n2 - Sales by Movie"+
    //                     "\n3 - Sales by Cineplex");
    //int reportType = sc.nextInt();
    switch(reportType){
      case "1":
        System.out.print("\nTotal Sales: " + SalesManager.getTotalSales(bookingArr));
        break;
      case "2":
        System.out.print("\nEnter Movie: ");
        String movieInput = sc.nextLine();
        System.out.print("\n" + movieInput + "'s Sales: " + SalesManager.getSalesByMovie(bookingArr, movieInput));
        break;
      case "3":
        System.out.print("\nEnter Cineplex: ");
        String cineplexInput = sc.nextLine();
        System.out.print("\n" + cineplexInput + "'s Sales: " + SalesManager.getSalesByCineplex(bookingArr, cineplexInput));
        break;
      default:
        break;
    }
  }

  /**
   * Asks the user for the type of bookings that want to view (filtereed by movie, cineplex etc)
   * Calls the getBookings method in the BookingsManager class to get the appropriate bookings
   */
  public void getBookings() {
    ArrayList<Booking> bookings = super.getBookingManager().getBookings();
    // Scanner sc = new Scanner(System.in);
    // System.out.print("\n Select movie to filter by (- for all):");
    // String filterMovie = sc.nextLine();
    // System.out.print("\n Select movie to cineplex by (- for all):");
    // String filterCineplex = sc.nextLine();
    // ArrayList<Booking> filteredBookings;
    for (Booking bk : bookings){
      // if(bk.get)
      System.out.println(bk.getId());
    }


  }

  /**
   * Ask the user for the filtering criteria to get the list of movies (filtereed by movie, cineplex etc)
   * Calls the getMovies method in the MovieManager class to get the appropriate movies
   * Displays the list of movies
   */
  public void getMoviesByRank() { //display top 5 movie by sales and top 5 by rating, admin can choose which of these or both to display to user
    
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
    Movie mov = super.getMovieManager().getMovies(movieInput); //Is there a way to get movie in by name?
    System.out.print("\nMovie Details: "+
                        "\n1 - Title: " + mov.getTitle()+
                        "\n2 - Show Status: " + mov.getShowStatus()+
                        "\n3 - Synopsis" + mov.getSynopsis()+
                        "\n4 - Director: " + mov.getDirector()+
                        "\n5 - Cast: \n" + mov.getCast()+
                        "\n6 - Advisory: " + mov.getAdvisory()+
                        "\n7 - Genre: " + mov.getGenre());
    int userInput = sc.nextInt();
    while(userInput != -1){
      switch(userInput){
        case 1:
          System.out.print("\nEnter Title: ");
          String title = sc.nextLine();
          mov.setMovieDetails(title, mov.getShowStatus(), mov.getSynopsis(), mov.getDirector(), mov.getCast(), mov.getAdvisory(), mov.getGenre());
          break;
        case 2:
          System.out.print("\nEnter Show Status: ");
          ShowStatus showingStatus = ShowStatus.values()[sc.nextInt() - 1];
          mov.setMovieDetails(mov.getTitle(), showingStatus, mov.getSynopsis(), mov.getDirector(), mov.getCast(), mov.getAdvisory(), mov.getGenre());
          break;
        case 3:
          System.out.print("\nEnter Synopsis: ");
          String synopsis = sc.nextLine();
          mov.setMovieDetails(mov.getTitle(), mov.getShowStatus(), synopsis, mov.getDirector(), mov.getCast(), mov.getAdvisory(), mov.getGenre());
          break;
        case 4:
          System.out.print("\nEnter Director: ");
          String director = sc.nextLine();
          mov.setMovieDetails(mov.getTitle(), mov.getShowStatus(), mov.getSynopsis(), director, mov.getCast(), mov.getAdvisory(), mov.getGenre());
          break;
        //...
      }
    }

    super.getMovieManager().updateMovie(movieInput);
  }

  /**
   * Allow the user to delete a movie
   */
  public void deleteMovie() {
    Scanner sc = new Scanner(System.in);  
    System.out.print("\nEnter title of Movie to delete: ");
    String movieInput = sc.nextLine();
    super.getMovieManager().deleteMovie(movieInput);
    sc.close();
  }

  /**
   * Allow the user to input showtime details and then add it to the Screening
   * 
   */
  public void createShowtime() {
    Scanner sc = new Scanner(System.in); 
    System.out.print("\nEnter movie title of screening: ");
    String title = sc.nextLine();
    System.out.print("\nEnter cinema: ");
    String cinema = sc.nextLine();
    //...
  }

  /**
   * Allow the user to update a showtime
   */
  public void updateShowtime() { 
    Scanner sc = new Scanner(System.in); 
    System.out.print("\nEnter movie title of Movie: ");
    String title = sc.nextLine();
    Movie mov = super.getMovieManager().getMovie(title);
    Screening screen = super.getScreening(mov);
    //...
  }

  /**
   * Allow the user to delete a showtime
   */
  public void deleteShowtime() {
    
  }

  public void display(Account account) {
    // should never trigger as it can only reach AdminConsole if the logged in user is a AdminAccount
    if (!(account instanceof AdminAccount)) {
      System.out.println("Something went wrong in the login process");
      this.exitProgram();
    }
  }

}
