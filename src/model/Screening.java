package model;

import java.util.ArrayList;

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
  private ArrayList<Seat> seats = new ArrayList<Seat>();

  public Screening(Movie movie, Cinema cinema, String showTime) {
    this.movie = movie;
    this.cinema = cinema;
    this.showTime = showTime;
  }

  public void addSeat(Seat seat) {
    seats.add(seat);
  }

  public boolean isSeatAvailable(Seat targetSeat) {
    for (Seat seat : this.seats) {
      if (seat.equals(targetSeat)) {
        return false;
      }
    }
    return true;
  }

  public void updateSeat(Seat seat) {
    seat.setTaken();
    addSeat(seat);
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

  public ArrayList<Seat> getSeats() {
    return this.seats;
  }

  // TODO: Get seat from seat ID
  public Seat getSeatFromId(String id) {
    for (Seat seat: this.seats) {
      if (seat.getId() == id) {
        return seat;
      }
    }
    
    return null;
  }

  public Movie getMovie() {
    return this.movie;
  }

}
