package model;

import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.Serializable;

import enums.SeatType;

/**
 * Screening class
 * Contains details of the screening and get/set functions
 *
 @author Roy Leong, Song Chen
 @version 1.1
 @since 2022-10-30
*/
public class Screening implements Serializable{
  /**
   * Movie the screening will show
   */
  private Movie movie;

  /**
   * Cinema the screening will take place at
   */
  private Cinema cinema;

  /**
   * Showtime of the screening
   */
  private DateTime showTime;

  /**
   * Arraylist of seats for the screening
   */
  private final ArrayList<Seat> seats = new ArrayList<Seat>(); // list of seats can't change after initialisation

  /**
   * Constructor for Screening, gets seating plan from the cinema it will be showing at
   * 
   * @param movie movie the screening will show
   * @param cinema cinema the screening will take place at
   * @param showTime showtime of the screening
   */
  public Screening(Movie movie, Cinema cinema, DateTime showTime) {
    this.movie = movie;
    this.cinema = cinema;
    this.showTime = showTime;

    SeatingPlan seatingPlan = cinema.getSeatingPlan();
    int rows = seatingPlan.getRows();
    int cols = seatingPlan.getColumns();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        SeatType seatType = SeatType.NORMAL;
        // Some arbitrary way to determine seat type

        // first row
        if (i == 0)  
          seatType = SeatType.GOLD;
        
        // second row
        if (i == 1)
          seatType = SeatType.PLATINUM;
        
        // last row and last column
        if (j == rows - 1 && j == cols - 1)
          seatType = SeatType.JUBILEE;

        // row and seat numbers can't start from 0
        Seat seat = new Seat(i+1, j+1, false, seatType);
        seats.add(seat);
      }
    }
  }

  /**
   * Checks if a seat is available by its ID
   * 
   * @param seatId id of the seat to check
   * @return true if seat is not taken, false if otherwise
   * @throws Exception if seat id does not exist
   */
  public boolean isSeatAvailableById(String seatId) throws Exception {
    return !this.getSeatById(seatId).isTaken();
  }

  /**
   * Getter method to get seat by its ID
   * 
   * @param seatId id of the seat
   * @return seat object corresponding to the provided seat id
   * @throws Exception if seat for the provided seat id is not found
   */
  public Seat getSeatById(String seatId) throws Exception {
    for (Seat seat : this.seats) {
      if (seat.getId().equals(seatId)) {
        return seat;
      }
    }

    throw new Exception("Seat not found");
  }

  /**
   * Getter method to get the movie title of the movie shown in the screening
   * 
   * @return movie title of the movie shown in the screening
   */
  public String getMovieTitle() {
    return this.movie.getTitle();
  }

  /**
   * Getter method to get the cinema id of the cinema the screening will be shown in
   * 
   * @return cinema id for the cinema the screening will be at
   */
  public String getCinemaId() {
    return this.cinema.getId();
  }

  /**
   * Getter method to get the cinema object of the cinema the screening will be shown in
   * 
   * @return cinema object for the cinema the screening will be at
   */
  public Cinema getCinema() {
    return this.cinema;
  }

  /**
   * Getter method to get the show time of the screening
   * 
   * @return show time of the screening
   */
   public DateTime getShowtime() {
    return this.showTime;
   }

   /**
   * Getter method to get the seats for the screening
   * 
   * @return arraylist of seats for the screening
   */
  public ArrayList<Seat> getSeats() {
    return this.seats;
  }

  /**
   * Getter method to get the movie of the screening
   * 
   * @return movie object of the movie showing for the screening
   */
  public Movie getMovie() {
    return this.movie;
  }

  /**
   * Setter method to set the show time of the screening
   * 
   * @param newShowtime show time of the screening to set
   */
  public void setShowTime(DateTime newShowtime) {
    this.showTime = newShowtime;
  }

}
