package model;

import java.util.ArrayList;
import java.util.Date;

import enums.SeatType;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class Screening {
  private Movie movie;
  private Cinema cinema;
  private String showTime;
  private final ArrayList<Seat> seats = new ArrayList<Seat>(); // list of seats can't change after initialisation

  public Screening(Movie movie, Cinema cinema, String showTime) {
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

  // Allow the user to get info abt seat by id
  public boolean isSeatAvailableById(String seatId) throws Exception {
    return !this.getSeatById(seatId).isTaken();
  }

  public Seat getSeatById(String seatId) throws Exception {
    for (Seat seat : this.seats) {
      if (seat.getId().equals(seatId)) {
        return seat;
      }
    }

    throw new Exception("Seat not found");
  }

  public String getMovieTitle() {
    return this.movie.getTitle();
  }

  public String getCinemaId() {
    return this.cinema.getId();
  }

  public Cinema getCinema() {
    return this.cinema;
  }

   public String getShowtime() {
    return this.showTime;
   }

  public void setShowTime(String newShowtime) {
    this.showTime = newShowtime;
  }

  public Date getDateTimeObj() throws ParseException {
    Date DateTime = new SimpleDateFormat("dd.MM.yyyy.HH.mm").parse(showTime);
    return DateTime;
  }

  public ArrayList<Seat> getSeats() {
    return this.seats;
  }

  public Movie getMovie() {
    return this.movie;
  }

}
